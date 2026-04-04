package ood;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * LeetCode 631, hard, tags: graph, topological sort, array, matrix, design.
 * <p>
 * Design Excel with {@code set}, {@code get}, and {@code sum} (formula). Overlapping ranges and repeated cell
 * references count with multiplicity. Assigning a raw value or a new formula replaces any prior formula on that
 * cell and updates downstream dependents.
 * <p>
 * {@link Excel} is the eager solution: cached cell values plus a weighted reverse dependency graph and topological
 * propagation so {@link Excel#get(int, char)} is O(1). {@link ExcelLazy} evaluates formulas recursively on
 * {@code get} (memoized per call).
 */
public final class ExcelSum {

    private ExcelSum() {
    }

    /** Shared API for {@link Excel} and {@link ExcelLazy} (tests, demos). */
    public interface Spreadsheet {
        void set(int row, char column, int val);

        int get(int row, char column);

        int sum(int row, char column, String[] numbers);
    }

    private static long cellId(int row, int col) {
        return ((long) row << 32) | (col & 0xffffffffL);
    }

    /** Parses column label (single letter per constraints) to 0-based index. */
    private static int parseColumn(char column) {
        return column - 'A';
    }

    /** Parses column prefix of a cell reference (e.g. {@code A1} -> 'A'). */
    private static int parseColumnPrefix(String ref, int endExclusive) {
        int value = 0;
        for (int i = 0; i < endExclusive; i++) {
            value = value * 26 + ref.charAt(i) - 'A' + 1;
        }
        return value - 1;
    }

    private static int[] parseRef(String ref) {
        int idx = 0;
        while (idx < ref.length() && Character.isLetter(ref.charAt(idx))) {
            idx++;
        }
        int row = Integer.parseInt(ref.substring(idx)) - 1;
        int col = parseColumnPrefix(ref, idx);
        return new int[]{row, col};
    }

    /**
     * Eager propagation: weighted dependency graph, cached values, O(1) get.
     */
    public static final class Excel implements Spreadsheet {
        private final int[][] values;
        /** Formula cell -> (source cell id -> weight). */
        private final Map<Long, Map<Long, Integer>> formulas = new HashMap<>();
        /** Source cell id -> (dependent formula cell id -> weight). */
        private final Map<Long, Map<Long, Integer>> dependents = new HashMap<>();

        public Excel(int height, char width) {
            int cols = width - 'A' + 1;
            this.values = new int[height][cols];
        }

        @Override
        public void set(int row, char column, int val) {
            int r = row - 1;
            int c = parseColumn(column);
            long target = cellId(r, c);
            int oldValue = values[r][c];
            clearFormula(target);
            values[r][c] = val;
            propagate(target, val - oldValue);
        }

        @Override
        public int get(int row, char column) {
            int r = row - 1;
            int c = parseColumn(column);
            return values[r][c];
        }

        @Override
        public int sum(int row, char column, String[] numbers) {
            int r = row - 1;
            int c = parseColumn(column);
            long target = cellId(r, c);
            int oldValue = values[r][c];
            clearFormula(target);

            Map<Long, Integer> refs = parseFormula(numbers);
            formulas.put(target, refs);
            for (Map.Entry<Long, Integer> e : refs.entrySet()) {
                dependents.computeIfAbsent(e.getKey(), k -> new HashMap<>())
                        .merge(target, e.getValue(), Integer::sum);
            }

            int newValue = 0;
            for (Map.Entry<Long, Integer> e : refs.entrySet()) {
                int[] src = unpack(e.getKey());
                newValue += values[src[0]][src[1]] * e.getValue();
            }
            values[r][c] = newValue;
            propagate(target, newValue - oldValue);
            return newValue;
        }

        private void clearFormula(long target) {
            Map<Long, Integer> refs = formulas.remove(target);
            if (refs == null || refs.isEmpty()) {
                return;
            }
            for (Map.Entry<Long, Integer> e : refs.entrySet()) {
                long src = e.getKey();
                int weight = e.getValue();
                Map<Long, Integer> dep = dependents.get(src);
                if (dep == null) {
                    continue;
                }
                int next = dep.getOrDefault(target, 0) - weight;
                if (next == 0) {
                    dep.remove(target);
                } else {
                    dep.put(target, next);
                }
                if (dep.isEmpty()) {
                    dependents.remove(src);
                }
            }
        }

        private Set<Long> collectAffected(long start) {
            Set<Long> affected = new HashSet<>();
            Queue<Long> queue = new ArrayDeque<>();
            queue.add(start);
            while (!queue.isEmpty()) {
                long src = queue.poll();
                Map<Long, Integer> outs = dependents.get(src);
                if (outs == null) {
                    continue;
                }
                for (long dst : outs.keySet()) {
                    if (affected.add(dst)) {
                        queue.add(dst);
                    }
                }
            }
            return affected;
        }

        private void propagate(long start, int delta) {
            if (delta == 0 || !dependents.containsKey(start)) {
                return;
            }
            Set<Long> affected = collectAffected(start);
            if (affected.isEmpty()) {
                return;
            }

            Map<Long, Integer> indegree = new HashMap<>();
            for (long cell : affected) {
                indegree.put(cell, 0);
            }
            for (long src : affected) {
                Map<Long, Integer> outs = dependents.get(src);
                if (outs == null) {
                    continue;
                }
                for (long dst : outs.keySet()) {
                    if (indegree.containsKey(dst)) {
                        indegree.put(dst, indegree.get(dst) + 1);
                    }
                }
            }
            Map<Long, Integer> outsStart = dependents.get(start);
            if (outsStart != null) {
                for (long dst : outsStart.keySet()) {
                    if (indegree.containsKey(dst)) {
                        indegree.put(dst, indegree.get(dst) + 1);
                    }
                }
            }

            Map<Long, Integer> accumulated = new HashMap<>();
            accumulated.put(start, delta);
            Queue<Long> topo = new ArrayDeque<>();
            topo.add(start);

            while (!topo.isEmpty()) {
                long src = topo.poll();
                Map<Long, Integer> outs = dependents.get(src);
                if (outs == null) {
                    continue;
                }
                int accSrc = accumulated.getOrDefault(src, 0);
                for (Map.Entry<Long, Integer> edge : outs.entrySet()) {
                    long dst = edge.getKey();
                    int weight = edge.getValue();
                    if (!indegree.containsKey(dst)) {
                        continue;
                    }
                    int add = accSrc * weight;
                    accumulated.merge(dst, add, Integer::sum);
                    indegree.put(dst, indegree.get(dst) - 1);
                    if (indegree.get(dst) == 0) {
                        int[] coord = unpack(dst);
                        values[coord[0]][coord[1]] += accumulated.get(dst);
                        topo.add(dst);
                    }
                }
            }
        }

        private Map<Long, Integer> parseFormula(String[] numbers) {
            Map<Long, Integer> refs = new HashMap<>();
            for (String token : numbers) {
                if (!token.contains(":")) {
                    int[] cell = parseRef(token);
                    long id = cellId(cell[0], cell[1]);
                    refs.merge(id, 1, Integer::sum);
                    continue;
                }
                String[] parts = token.split(":", 2);
                int[] startCell = parseRef(parts[0]);
                int[] endCell = parseRef(parts[1]);
                for (int row = startCell[0]; row <= endCell[0]; row++) {
                    for (int col = startCell[1]; col <= endCell[1]; col++) {
                        long id = cellId(row, col);
                        refs.merge(id, 1, Integer::sum);
                    }
                }
            }
            return refs;
        }

        private int[] unpack(long id) {
            int row = (int) (id >>> 32);
            int col = (int) id;
            return new int[]{row, col};
        }
    }

    /**
     * Lazy evaluation: formulas stored as weighted source sets; {@link ExcelLazy#get(int, char)} recursively
     * evaluates with per-call memoization.
     */
    public static final class ExcelLazy implements Spreadsheet {
        private final int[][] values;
        private final Map<Long, Map<Long, Integer>> formulas = new HashMap<>();

        public ExcelLazy(int height, char width) {
            int cols = width - 'A' + 1;
            this.values = new int[height][cols];
        }

        @Override
        public void set(int row, char column, int val) {
            int r = row - 1;
            int c = parseColumn(column);
            formulas.remove(cellId(r, c));
            values[r][c] = val;
        }

        @Override
        public int get(int row, char column) {
            return evaluate(cellId(row - 1, parseColumn(column)), new HashMap<>());
        }

        @Override
        public int sum(int row, char column, String[] numbers) {
            int r = row - 1;
            int c = parseColumn(column);
            long target = cellId(r, c);
            formulas.put(target, parseFormula(numbers));
            return get(row, column);
        }

        private int evaluate(long cell, Map<Long, Integer> memo) {
            Integer cached = memo.get(cell);
            if (cached != null) {
                return cached;
            }
            Map<Long, Integer> refs = formulas.get(cell);
            if (refs == null) {
                int[] coord = unpack(cell);
                return values[coord[0]][coord[1]];
            }
            int total = 0;
            for (Map.Entry<Long, Integer> e : refs.entrySet()) {
                total += evaluate(e.getKey(), memo) * e.getValue();
            }
            memo.put(cell, total);
            return total;
        }

        private Map<Long, Integer> parseFormula(String[] numbers) {
            Map<Long, Integer> refs = new HashMap<>();
            for (String token : numbers) {
                if (!token.contains(":")) {
                    int[] cell = parseRef(token);
                    long id = cellId(cell[0], cell[1]);
                    refs.merge(id, 1, Integer::sum);
                    continue;
                }
                String[] parts = token.split(":", 2);
                int[] startCell = parseRef(parts[0]);
                int[] endCell = parseRef(parts[1]);
                for (int row = startCell[0]; row <= endCell[0]; row++) {
                    for (int col = startCell[1]; col <= endCell[1]; col++) {
                        long id = cellId(row, col);
                        refs.merge(id, 1, Integer::sum);
                    }
                }
            }
            return refs;
        }

        private static int[] unpack(long id) {
            int row = (int) (id >>> 32);
            int col = (int) id;
            return new int[]{row, col};
        }
    }
}
