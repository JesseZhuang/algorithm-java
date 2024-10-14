package heap;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

/**
 * LeetCode 2530, medium, tags: heap, array, greedy.
 * <p>
 * You are given a 0-indexed integer array nums and an integer k. You have a starting score of 0.
 * <p>
 * In one operation:
 * <p>
 * choose an index i such that 0 <= i < nums.length,
 * increase your score by nums[i], and
 * replace nums[i] with ceil(nums[i] / 3).
 * Return the maximum possible score you can attain after applying exactly k operations.
 * <p>
 * The ceiling function ceil(val) is the least integer greater than or equal to val.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [10,10,10,10,10], k = 5
 * Output: 50
 * Explanation: Apply the operation to each array element exactly once. The final score is 10 + 10 + 10 + 10 + 10 = 50.
 * Example 2:
 * <p>
 * Input: nums = [1,10,3,3,3], k = 3
 * Output: 17
 * Explanation: You can do the following operations:
 * Operation 1: Select i = 1, so nums becomes [1,4,3,3,3]. Your score increases by 10.
 * Operation 2: Select i = 1, so nums becomes [1,2,3,3,3]. Your score increases by 4.
 * Operation 3: Select i = 2, so nums becomes [1,1,1,3,3]. Your score increases by 3.
 * The final score is 10 + 4 + 3 = 17.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length, k <= 10^5
 * 1 <= nums[i] <= 10^9
 * <p>
 * Hint 1
 * It is always optimal to select the greatest element in the array.
 * Hint 2
 * Use a heap to query for the maximum in O(log n) time.
 */
@SuppressWarnings("unused")
public class MaxScoreKOps {
    // heap, n+klgn, n.
    static class Solution {
        public long maxKelements(int[] nums, int k) {
            PriorityQueue<Integer> pq = new PriorityQueue<>(
                    Arrays.stream(nums).boxed().map(i -> -i).collect(Collectors.toList()));
            long res = 0;
            while (k-- > 0) {
                int cur = -pq.remove();
                res += cur;
                pq.add(-(int) Math.ceil(cur / 3.0));
            }
            return res;
        }
    }
}
