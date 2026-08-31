package array;

import java.util.*;

/**
 * LeetCode 40, medium, tags: array, backtracking, sorting.
 * Given a collection of candidate numbers (candidates) and a target number (target), find all unique
 * combinations in candidates where the candidate numbers sum to target. Each number in candidates may
 * only be used once in the combination.
 * Note: The solution set must not contain duplicate combinations.
 *
 * Constraints:
 * 1 <= candidates.length <= 100
 * 1 <= candidates[i] <= 50
 * 1 <= target <= 30
 */
public final class CombinationSumII {
    private CombinationSumII() {}

    /** Solution 1: Backtracking with sort + skip duplicates. O(2^n) time, O(n) space. */
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates); // O(n log n)
        List<List<Integer>> res = new ArrayList<>();
        backtrack(candidates, target, 0, new ArrayList<>(), res);
        return res;
    }

    private static void backtrack(int[] c, int remaining, int start, List<Integer> path, List<List<Integer>> res) {
        if (remaining == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < c.length; i++) { // O(n) branches
            if (c[i] > remaining) break; // prune: sorted
            if (i > start && c[i] == c[i - 1]) continue; // skip duplicates at same level
            path.add(c[i]);
            backtrack(c, remaining - c[i], i + 1, path, res); // i+1: each element used once
            path.remove(path.size() - 1);
        }
    }

    /** Solution 2: Counter-based backtracking. O(2^n) time, O(n) space. */
    public static List<List<Integer>> combinationSum2Counter(int[] candidates, int target) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int c : candidates) freq.merge(c, 1, Integer::sum); // O(n)
        List<int[]> entries = new ArrayList<>(); // {value, count}
        for (var e : freq.entrySet()) entries.add(new int[]{e.getKey(), e.getValue()});
        entries.sort(Comparator.comparingInt(a -> a[0])); // O(k log k), k = unique keys
        List<List<Integer>> res = new ArrayList<>();
        counterBacktrack(entries, target, 0, new ArrayList<>(), res);
        return res;
    }

    private static void counterBacktrack(List<int[]> entries, int remaining, int idx,
                                         List<Integer> path, List<List<Integer>> res) {
        if (remaining == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = idx; i < entries.size(); i++) { // O(k) unique keys
            int val = entries.get(i)[0], count = entries.get(i)[1];
            if (val > remaining) break; // prune: sorted
            for (int c = 1; c <= count && c * val <= remaining; c++) { // O(count) copies
                path.add(val);
                counterBacktrack(entries, remaining - c * val, i + 1, path, res);
            }
            for (int c = Math.min(count, remaining / val); c >= 1; c--) path.remove(path.size() - 1); // undo
        }
    }
}
