package ood;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * LeetCode 631, hard, tags: graph, topological sort, graph, array, matrix.
 * <p>
 * Design the basic function of Excel and implement the function of the sum formula.
 * <p>
 * Implement the Excel class:
 * <p>
 * Excel(int height, char width) Initializes the object with the height and the width of the sheet. The sheet is
 * an integer matrix mat of size height x width with the row index in the range [1, height] and the column index
 * in the range ['A', width]. All the values should be zero initially.
 * void set(int row, char column, int val) Changes the value at mat[row][column] to be val.
 * int get(int row, char column) Returns the value at mat[row][column].
 * int sum(int row, char column, List<String> numbers) Sets the value at mat[row][column] to be the sum of cells
 * represented by numbers and returns the value at mat[row][column]. This sum formula should exist until this cell is
 * overlapped by another value or another sum formula. numbers[i] could be on the format:
 * "ColRow" that represents a single cell.
 * For example, "F7" represents the cell mat[7]['F'].
 * "ColRow1:ColRow2" that represents a range of cells. The range will always be a rectangle where "ColRow1" represent
 * the position of the top-left cell, and "ColRow2" represents the position of the bottom-right cell.
 * For example, "B3:F7" represents the cells mat[i][j] for 3 <= i <= 7 and 'B' <= j <= 'F'.
 * Note:
 * <p>
 * You could assume that there will not be any circular sum reference.
 * The test cases are using double-quotes to represent a character.
 * Please remember to RESET your class variables declared in class Excel,
 * as static/class variables are persisted across multiple test cases. Please see here for more details.
 * <p>
 * For example, mat[1]['A'] == sum(1, "B") and mat[1]['B'] == sum(1, "A").
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["Excel", "set", "sum", "set", "get"]
 * [[3, "C"], [1, "A", 2], [3, "C", ["A1", "A1:B2"]], [2, "B", 2], [3, "C"]]
 * Output
 * [null, null, 4, null, 6]
 * <p>
 * Explanation
 * Excel excel = new Excel(3, "C");
 * // construct a 3*3 2D array with all zero.
 * //   A B C
 * // 1 0 0 0
 * // 2 0 0 0
 * // 3 0 0 0
 * excel.set(1, "A", 2);
 * // set mat[1]["A"] to be 2.
 * //   A B C
 * // 1 2 0 0
 * // 2 0 0 0
 * // 3 0 0 0
 * excel.sum(3, "C", ["A1", "A1:B2"]); // return 4
 * // set mat[3]["C"] to be the sum of value at mat[1]["A"] and the values sum of the rectangle range whose
 * top-left cell is mat[1]["A"] and bottom-right cell is mat[2]["B"].
 * //   A B C
 * // 1 2 0 0
 * // 2 0 0 0
 * // 3 0 0 4
 * excel.set(2, "B", 2);
 * // set mat[2]["B"] to be 2. Note mat[3]["C"] should also be changed.
 * //   A B C
 * // 1 2 0 0
 * // 2 0 2 0
 * // 3 0 0 6
 * excel.get(3, "C"); // return 6
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= height <= 26
 * 'A' <= width <= 'Z'
 * 1 <= row <= height
 * 'A' <= column <= width
 * -100 <= val <= 100
 * 1 <= numbers.length <= 5
 * numbers[i] has the format "ColRow" or "ColRow1:ColRow2".
 * At most 100 calls will be made to set, get, and sum.
 */
public class ExcelSum {
    public static class Cell {
        int val = 0;
        Map<Cell, Integer> posCnt = new HashMap<>(); // {pos, count} cells that this cell depend on
        Set<Cell> dependee = new HashSet<>(); // other cells whose formula depends on this cell

        public Cell(int val) {
            update(val);
        }

        // calculate formula and return result
        public int calc() {
            int res = 0;
            for (Cell c : posCnt.keySet()) res += c.val * posCnt.get(c); // val*cnt
            return res;
        }

        void update(int delta) {
            this.val += delta;
            for (Cell nc : dependee)
                nc.update(nc.posCnt.get(this) * delta);
        }

        // reset the cells in posCnt to be not depended by this cell and then clear posCnt
        void resetEdges() {
            for (Cell c : posCnt.keySet()) c.dependee.remove(this);
            posCnt.clear();
        }
    }

    public static class Excel {
        final Cell[][] sheet;

        public Excel(int height, char width) {
            int r = height + 1, c = width - 'A' + 1;
            this.sheet = new Cell[r][c]; // extra 1 row and col
            for (int i = 1; i <= height; ++i) // rows [1,h]
                for (int j = 0; j < c; ++j) // cols [0,w-'A']
                    sheet[i][j] = new Cell(0);
        }

        public void set(int row, char column, int val) {
            Cell c = sheet[row][column - 'A'];
            c.update(val - c.val);
        }

        public int get(int row, char column) {
            return sheet[row][column - 'A'].val;
        }

        public int sum(int row, char column, String[] numbers) {
            Cell c = sheet[row][column - 'A'];
            int tmp = c.val;
            c.resetEdges();
            c.posCnt = parse(numbers);
            for (Cell nc : c.posCnt.keySet()) nc.dependee.add(c);
            c.update(c.calc() - tmp);
            return c.val;
        }


        private Map<Cell, Integer> parse(String[] strings) {
            Map<Cell, Integer> res = new HashMap<>();
            for (final String s : strings) {
                List<Cell> cells = parse(s);
                for (Cell c : cells) res.merge(c, 1, Integer::sum);
                // res.put(c, res.getOrDefault(c,0)+1);
            }
            return res;
        }

        // A1 -> {A1:1} A1:B2 -> {A1:1,A2:1,B1:1,B2:1}; together {A1:2,A2:1,B1:1,B2:1}
        private List<Cell> parse(String s) {
            List<Cell> res = new ArrayList<>();
            if (!s.contains(":")) {
                int r = Integer.parseInt(s.substring(1)), c = s.charAt(0) - 'A';
                res.add(sheet[r][c]);
            } else {
                String[] tokens = s.split(":");
                int r1 = Integer.parseInt(tokens[0].substring(1));
                int c1 = tokens[0].charAt(0) - 'A';
                int r2 = Integer.parseInt(tokens[1].substring(1));
                int c2 = tokens[1].charAt(0) - 'A';
                for (int i = r1; i <= r2; i++)
                    for (int j = c1; j <= c2; j++)
                        res.add(sheet[i][j]);
            }
            return res;
        }
    }
}
