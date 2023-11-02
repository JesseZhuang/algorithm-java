package array;

/**
 * LeetCode 240, medium, tags: binary search, array, matrix, divide and conquer.
 * <p>
 * Write an efficient algorithm that searches for a value target in an m x n integer matrix.
 * This matrix has the following properties:
 * <p>
 * Integers in each row are sorted in ascending from left to right.
 * Integers in each column are sorted in ascending from top to bottom.
 * <p>
 * <p>
 * Example 1: https://assets.leetcode.com/uploads/2020/11/24/searchgrid2.jpg
 * <p>
 * <p>
 * Input: matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
 * Output: true
 * Example 2: https://assets.leetcode.com/uploads/2020/11/24/searchgrid.jpg
 * <p>
 * <p>
 * Input: matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 20
 * Output: false
 * <p>
 * <p>
 * Constraints:
 * <p>
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= n, m <= 300
 * -10^9 <= matrix[i][j] <= 10^9
 * All the integers in each row are sorted in ascending order.
 * All the integers in each column are sorted in ascending order.
 * -10^9 <= target <= 10^9
 */
public class Search2DMatrix {
    // search from top right, O(m+n) time, O(1) space. 4ms, 49.36 MB.
    public boolean searchMatrix1(int[][] matrix, int target) {
        int r = 0, c = matrix[0].length - 1;
        while (c >= 0 && r < matrix.length) {
            if (target == matrix[r][c]) return true;
            else if (target < matrix[r][c]) c--;
            else if (target > matrix[r][c]) r++;
        }
        return false;
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        int r = matrix.length, c = matrix[0].length;
        return searchMatrix(matrix, new int[]{0, 0}, new int[]{r - 1, c - 1}, target);
    }

    // O(mn ^ log_4 3) time, O (log_4 mn) stack space. 28 ms, 50 MB.
    private boolean searchMatrix(int[][] matrix, int[] ul, int[] lr, int target) {
        if (ul[0] > lr[0] || ul[1] > lr[1]
                || lr[0] >= matrix.length || lr[1] >= matrix[0].length) return false;
        if (lr[0] - ul[0] == 0 && lr[1] - ul[1] == 0)
            return matrix[ul[0]][ul[1]] == target;
        int rMid = (ul[0] + lr[0]) / 2;
        int cMid = (ul[1] + lr[1]) / 2;
        if (matrix[rMid][cMid] > target) {
            return searchMatrix(matrix, ul, new int[]{rMid, cMid}, target)
                    || searchMatrix(matrix, new int[]{ul[0], cMid + 1}, new int[]{rMid, lr[1]}, target)
                    || searchMatrix(matrix, new int[]{rMid + 1, ul[1]}, new int[]{lr[0], cMid}, target);
        } else if (matrix[rMid][cMid] < target) {
            return searchMatrix(matrix, new int[]{ul[0], cMid + 1}, new int[]{rMid, lr[1]}, target)
                    || searchMatrix(matrix, new int[]{rMid + 1, ul[1]}, new int[]{lr[0], cMid}, target)
                    || searchMatrix(matrix, new int[]{rMid + 1, cMid + 1}, lr, target);
        } else return true;
    }

}
