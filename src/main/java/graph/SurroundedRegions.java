package graph;

/**
 * LeetCode 130, medium, tags: array, dfs, bfs, union find, matrix.
 * <p>
 * Given an m x n matrix board containing 'X' and 'O', capture all regions that are 4-directionally surrounded by 'X'.
 * <p>
 * A region is captured by flipping all 'O's into 'X's in that surrounded region.
 * <p>
 * Example 1: https://assets.leetcode.com/uploads/2021/02/19/xogrid.jpg
 * <p>
 * Input: board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
 * Output: [["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
 * Explanation: Notice that an 'O' should not be flipped if:
 * - It is on the border, or
 * - It is adjacent to an 'O' that should not be flipped.
 * The bottom 'O' is on the border, so it is not flipped.
 * The other three 'O' form a surrounded region, so they are flipped.
 * Example 2:
 * <p>
 * Input: board = [["X"]]
 * Output: [["X"]]
 * <p>
 * Constraints:
 * <p>
 * m == board.length
 * n == board[i].length
 * 1 <= m, n <= 200
 * board[i][j] is 'X' or 'O'.
 */
public class SurroundedRegions {

    // dfs, 1ms, 44.5 Mb. O(mn) time and space.
    public void solveDfs(char[][] board) {
        if (board.length < 2 || board[0].length < 2) return;
        int m = board.length, n = board[0].length;
        //Any 'O' connected to a boundary can't be turned to 'X', turn 'O' to '*'.
        for (int i = 0; i < m; i++) {
            boundaryDFS(board, i, 0);
            boundaryDFS(board, i, n - 1);
        }
        //Start from first and last row, turn '0' to '*'
        for (int j = 0; j < n; j++) {
            boundaryDFS(board, 0, j);
            boundaryDFS(board, m - 1, j);
        }
        //post-processing, turn 'O' to 'X', '*' back to 'O', keep 'X' intact.
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') board[i][j] = 'X';
                else if (board[i][j] == '*') board[i][j] = 'O';
            }
        }
    }

    //Use DFS algo to turn internal however boundary-connected 'O' to '*';
    private void boundaryDFS(char[][] board, int i, int j) {
        if (i < 0 || i > board.length - 1 || j < 0 || j > board[0].length - 1 || board[i][j] != 'O') return;
        board[i][j] = '*';
        boundaryDFS(board, i - 1, j);
        boundaryDFS(board, i + 1, j);
        boundaryDFS(board, i, j - 1);
        boundaryDFS(board, i, j + 1);
    }
}
