package array;

import java.util.Arrays;

/**
 * LeetCode 3301, medium, tags: array, greedy, sorting.
 * <p>
 * You are given an array maximumHeight, where maximumHeight[i] denotes the maximum height the ith tower
 * can be assigned.
 * <p>
 * Your task is to assign a height to each tower so that:
 * <p>
 * The height of the ith tower is a positive integer and does not exceed maximumHeight[i].
 * No two towers have the same height.
 * Return the maximum possible total sum of the tower heights. If it's not possible to assign heights, return -1.
 * <p>
 * Example 1:
 * <p>
 * Input: maximumHeight = [2,3,4,3]
 * <p>
 * Output: 10
 * <p>
 * Explanation:
 * <p>
 * We can assign heights in the following way: [1, 2, 4, 3].
 * <p>
 * Example 2:
 * <p>
 * Input: maximumHeight = [15,10]
 * <p>
 * Output: 25
 * <p>
 * Explanation:
 * <p>
 * We can assign heights in the following way: [15, 10].
 * <p>
 * Example 3:
 * <p>
 * Input: maximumHeight = [2,2,1]
 * <p>
 * Output: -1
 * <p>
 * Explanation:
 * <p>
 * It's impossible to assign positive heights to each index so that no two towers have the same height.
 * <p>
 * Constraints:
 * <p>
 * 1 <= maximumHeight.length <= 10^5
 * 1 <= maximumHeight[i] <= 10^9
 * <p>
 * Hint 1
 * Sort the array maximumHeight in descending order.
 * Hint 2
 * After sorting, it can be seen that the maximum height that we can assign to the ith element is
 * min(maximumHeight[i], maximumHeight[i - 1] - 1).
 */
@SuppressWarnings("unused")
public class MaxTotalTowerHeight {
    // nlgn, 1.
    static class Solution {
        public long maximumTotalSum(int[] maxH) {
            int n = maxH.length, last = Integer.MAX_VALUE; // last assigned height
            Arrays.sort(maxH);
            long sum = 0;
            for (int i = n - 1; i >= 0; i--) {
                last = Math.min(maxH[i], last - 1);
                if (last < 1) return -1;
                sum += last;
            }
            return sum;
        }
    }
}
