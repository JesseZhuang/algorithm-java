package array;

/**
 * LeetCode 289, medium, tags: array, matrix, simulation.
 * <p>
 * According to Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton devised by
 * the British mathematician John Horton Conway in 1970."
 * <p>
 * The board is made up of an m x n grid of cells, where each cell has an initial state: live (represented by a 1)
 * or dead (represented by a 0). Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using
 * the following four rules (taken from the above Wikipedia article):
 * <p>
 * Any live cell with fewer than two live neighbors dies as if caused by under-population.
 * Any live cell with two or three live neighbors lives on to the next generation.
 * Any live cell with more than three live neighbors dies, as if by over-population.
 * Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
 * The next state is created by applying the above rules simultaneously to every cell in the current state, where
 * births and deaths occur simultaneously. Given the current state of the m x n grid board, return the next state.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: board = [[0,1,0],[0,0,1],[1,1,1],[0,0,0]]
 * Output: [[0,0,0],[1,0,1],[0,1,1],[0,1,0]]
 * Example 2:
 * <p>
 * <p>
 * Input: board = [[1,1],[1,0]]
 * Output: [[1,1],[1,1]]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * m == board.length
 * n == board[i].length
 * 1 <= m, n <= 25
 * board[i][j] is 0 or 1.
 * <p>
 * <p>
 * Follow up:
 * <p>
 * Could you solve it in-place? Remember that the board needs to be updated simultaneously: You cannot update
 * some cells first and then use their updated values to update other cells.
 * <p>
 * In this question, we represent the board using a 2D array. In principle, the board is infinite, which would
 * cause problems when the active area encroaches upon the border of the array (i.e., live cells reach the border).
 * How would you address these problems?
 */
@SuppressWarnings("unused")
public class GameOfLife {
    // solution 1, O(mn) time, O(1) space, 0ms, 40.7MB.
    static class Solution {
        public void gameOfLife(int[][] board) {
            int m = board.length, n = board[0].length;
            for (int r = 0; r < m; r++) {
                for (int c = 0; c < n; c++) {
                    int count = 0;
                    for (int i = Math.max(r - 1, 0); i < Math.min(r + 2, m); i++)
                        for (int j = Math.max(c - 1, 0); j < Math.min(j + 2, n); j++)
                            count += board[i][j] & 1;
                    // [2nd bit, 1st bit] use 2nd bit to store next state
                    if (count == 3 || count - board[r][c] == 3) board[r][c] |= 2; // rules 2,4
                }
            }
            for (int r = 0; r < m; r++)
                for (int c = 0; c < n; c++)
                    board[r][c] >>= 1;
        }

    }
}
