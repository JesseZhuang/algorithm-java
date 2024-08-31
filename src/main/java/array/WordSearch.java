package array;

/**
 * LeetCode 79, medium, tags: array, matrix, backtracking. LintCode 123.
 * Given an m x n grid of characters board and a string word, return true if word exists in the grid.
 * <p>
 * The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally
 * or vertically neighboring. The same letter cell may not be used more than once.
 * <p>
 * Example 1: https://assets.leetcode.com/uploads/2020/11/04/word2.jpg
 * <p>
 * Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 * Output: true
 * Example 2: https://assets.leetcode.com/uploads/2020/11/04/word-1.jpg
 * <p>
 * Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
 * Output: true
 * Example 3: https://assets.leetcode.com/uploads/2020/10/15/word3.jpg
 * <p>
 * Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
 * Output: false
 * <p>
 * <p>
 * Constraints:
 * <p>
 * m == board.length
 * n = board[i].length
 * 1 <= m, n <= 6
 * 1 <= word.length <= 15, l
 * board and word consists of only lowercase and uppercase English letters.
 */
public class WordSearch {
    // 383ms, 42.5Mb. O(mn 3^l) time, O(mn) space, O(min(l,mn)) recursion space.
    // Some use bitwise XOR 256 or set char to not letter to save space (visited array)
    public boolean exist(char[][] board, String word) {
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board[0].length; j++)
                if (dfs(board, word, 0, visited, i, j)) return true;
        return false;
    }

    private boolean dfs(char[][] board, String word, int index, boolean[][] visited, int i, int j) {
        if (index == word.length()) return true;
        if (i >= board.length || j >= board[0].length || i < 0 || j < 0
                || visited[i][j] || board[i][j] != word.charAt(index)) return false; // board[i][j]=='8' visited
        visited[i][j] = true; // board[i][j] ^= 256;
        int[][] dirs = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
        for (int[] dir : dirs)
            if (dfs(board, word, index + 1, visited, i + dir[0], j + dir[1])) return true;
        visited[i][j] = false; // board[i][j] ^= 256;
        return false;
    }
}
