package hash;

/**
 * LeetCode 1248. Medium. Tags: array, hash table, sliding window, prefix sum.
 * <p>
 * Given a positive integer array nums and an integer k, return the number of
 * non-empty contiguous subarrays containing exactly k odd numbers.
 * <p>
 * Constraints: 1 <= nums.length <= 50000, 1 <= nums[i] <= 100000,
 * 1 <= k <= nums.length.
 * <p>
 * https://leetcode.com/problems/count-number-of-nice-subarrays/
 */
public final class NumberOfNiceSubarrays {

    private NumberOfNiceSubarrays() {
    }

    /** Prefix odd-count frequencies. O(n) time, O(n) space. */
    public static int numberOfSubarrays(int[] nums, int k) {
        int[] frequencies = new int[nums.length + 1]; // O(n) space
        frequencies[0] = 1; // empty prefix
        int oddCount = 0;
        int count = 0;
        for (int num : nums) { // O(n): each prefix takes O(1) work
            oddCount += num & 1;
            if (oddCount >= k) {
                count += frequencies[oddCount - k];
            }
            frequencies[oddCount]++;
        }
        return count;
    }

    /** Sliding window: exactly(k) = atMost(k) - atMost(k - 1). O(n) time, O(1) space. */
    public static int numberOfSubarraysSliding(int[] nums, int k) {
        return atMost(nums, k) - atMost(nums, k - 1);
    }

    /** Counts subarrays containing at most k odd numbers. O(n) time, O(1) space. */
    private static int atMost(int[] nums, int k) {
        int left = 0;
        int oddCount = 0;
        int count = 0;
        for (int right = 0; right < nums.length; right++) { // O(n) total
            oddCount += nums[right] & 1;
            while (oddCount > k) { // left advances at most n times across the loop
                oddCount -= nums[left++] & 1;
            }
            count += right - left + 1;
        }
        return count;
    }
}
