package array;

/**
 * LeetCode 74, medium, tags: array, binary search, matrix.
 * <p>
 * You are given an m x n integer matrix with the following two properties:
 * <p>
 * Each row is sorted in non-decreasing order.
 * The first integer of each row is greater than the last integer of the previous row.
 * <p>
 * Given an integer target, return true if target is in matrix, or false otherwise.
 * You must write a solution in O(log(m * n)) time complexity.
 * <p>
 * Example 1:
 * Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
 * Output: true
 * <p>
 * Example 2:
 * Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
 * Output: false
 * <p>
 * Constraints:
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 100
 * -10^4 <= matrix[i][j], target <= 10^4
 */
public final class Search2DMatrixI {

    private Search2DMatrixI() {
    }

    /**
     * Solution 1: Treat as a flattened sorted array, single binary search.
     * O(log(m*n)) time, O(1) space.
     */
    public static boolean searchMatrix1(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int lo = 0, hi = m * n - 1;
        // O(log(m*n)) binary search over virtual flattened array
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int val = matrix[mid / n][mid % n];
            if (val == target) return true;
            else if (val < target) lo = mid + 1;
            else hi = mid - 1;
        }
        return false;
    }

    /**
     * Solution 2: Binary search for the correct row, then binary search within that row.
     * O(log m + log n) time, O(1) space.
     */
    public static boolean searchMatrix2(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        // O(log m) binary search for the row
        int lo = 0, hi = m - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (matrix[mid][0] <= target && target <= matrix[mid][n - 1]) {
                // found candidate row, binary search within it
                return binarySearch(matrix[mid], target);
            } else if (matrix[mid][0] > target) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return false;
    }

    // O(log n) binary search within a single row
    private static boolean binarySearch(int[] row, int target) {
        int lo = 0, hi = row.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (row[mid] == target) return true;
            else if (row[mid] < target) lo = mid + 1;
            else hi = mid - 1;
        }
        return false;
    }
}
