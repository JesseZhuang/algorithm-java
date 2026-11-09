package sliding;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 992, hard, tags: array, hash table, sliding window.
 * <p>
 * Return the number of subarrays containing exactly k distinct integers.
 */
@SuppressWarnings("unused")
public final class SubarraysWithKDistinct {

    private SubarraysWithKDistinct() {
    }

    // O(n) time, O(n) space: each window boundary moves at most n positions.
    public static int subarraysWithKDistinct(int[] nums, int k) {
        return atMostDistinct(nums, k) - atMostDistinct(nums, k - 1);
    }

    // Counts subarrays with at most k distinct integers. O(n) time and O(n) space.
    private static int atMostDistinct(int[] nums, int k) {
        if (k <= 0) return 0;

        Map<Integer, Integer> frequencies = new HashMap<>();
        int left = 0, count = 0;
        for (int right = 0; right < nums.length; right++) {
            frequencies.merge(nums[right], 1, Integer::sum);
            while (frequencies.size() > k) {
                int leftValue = nums[left++];
                frequencies.merge(leftValue, -1, Integer::sum);
                if (frequencies.get(leftValue) == 0) frequencies.remove(leftValue);
            }
            count += right - left + 1;
        }
        return count;
    }
}
