package array;

/**
 * LeetCode 1275, easy, tags: array, hash table, matrix, simulation.
 * Tic-tac-toe is played by two players A and B on a 3 x 3 grid. The rules of Tic-Tac-Toe are:
 * <p>
 * Players take turns placing characters into empty squares ' '.
 * The first player A always places 'X' characters, while the second player B always places 'O' characters.
 * 'X' and 'O' characters are always placed into empty squares, never on filled ones.
 * The game ends when there are three of the same (non-empty) character filling any row, column, or diagonal.
 * The game also ends if all squares are non-empty.
 * No more moves can be played if the game is over.
 * Given a 2D integer array moves where moves[i] = [rowi, coli] indicates that the ith move will be played on
 * grid[rowi][coli]. return the winner of the game if it exists (A or B). In case the game ends in a draw return "Draw".
 * If there are still movements to play return "Pending".
 * <p>
 * You can assume that moves is valid (i.e., it follows the rules of Tic-Tac-Toe), the grid is initially empty,
 * and A will play first.
 * <p>
 * Example 1: https://assets.leetcode.com/uploads/2021/09/22/xo1-grid.jpg
 * <p>
 * Input: moves = [[0,0],[2,0],[1,1],[2,1],[2,2]]
 * Output: "A"
 * Explanation: A wins, they always play first.
 * Example 2: https://assets.leetcode.com/uploads/2021/09/22/xo2-grid.jpg
 * <p>
 * Input: moves = [[0,0],[1,1],[0,1],[0,2],[1,0],[2,0]]
 * Output: "B"
 * Explanation: B wins.
 * Example 3: https://assets.leetcode.com/uploads/2021/09/22/xo3-grid.jpg
 * <p>
 * <p>
 * Input: moves = [[0,0],[1,1],[2,0],[1,0],[1,2],[2,1],[0,1],[0,2],[2,2]]
 * Output: "Draw"
 * Explanation: The game ends in a draw since there are no moves to make.
 * <p>
 * Constraints:
 * <p>
 * 1 <= moves.length <= 9
 * moves[i].length == 2
 * 0 <= rowi, coli <= 2
 * There are no repeated elements on moves.
 * moves follow the rules of tic-tac-toe.
 */
public class FindWinnerTicTacToe {
    // 0ms, 40.2Mb. O(n) time and space.
    // Applies to a larger board as well assuming rule is similar.
    public String tictactoe(int[][] moves) {
        int n = 3; // make generic for larger board size
        int[] rows = new int[n], cols = new int[n];
        int diag1 = 0;
        int diag2 = 0;
        int curPlayer = 1; // A:1, B:-1
        for (int[] move : moves) {
            rows[move[0]] += curPlayer;
            cols[move[1]] += curPlayer;
            if (move[0] == move[1]) diag1 += curPlayer;
            if (move[0] + move[1] == n - 1) diag2 += curPlayer;
            if (Math.abs(rows[move[0]]) == n || Math.abs(cols[move[1]]) == n
                    || Math.abs(diag1) == n || Math.abs(diag2) == n)
                return curPlayer == 1 ? "A" : "B";
            curPlayer *= -1;
        }
        return moves.length < n * n ? "Pending" : "Draw";
    }

    // 0ms, 40.2Mb.
    public String tictactoe2(int[][] moves) {
        int[][] rows = new int[2][3], cols = new int[2][3];
        int[] diag1 = new int[2], diag2 = new int[2];
        for (int i = 0; i < moves.length; i++) {
            int cr = moves[i][0], cc = moves[i][1], id = i % 2;
            if (++rows[id][cr] == 3 || ++cols[id][cc] == 3
                    || (cr == cc && ++diag1[id] == 3) || (cr + cc == 2 && ++diag2[id] == 3))
                return id == 0 ? "A" : "B";
        }
        return moves.length < 9 ? "Pending" : "Draw";
    }
}
