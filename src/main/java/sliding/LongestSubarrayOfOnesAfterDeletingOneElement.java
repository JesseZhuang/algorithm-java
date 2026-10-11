package sliding;

/** LeetCode 1493: Longest Subarray of 1's After Deleting One Element. */
@SuppressWarnings("unused")
public final class LongestSubarrayOfOnesAfterDeletingOneElement {

    private LongestSubarrayOfOnesAfterDeletingOneElement() {
    }

    public static int longestSubarray(int[] nums) {
        int left = 0;
        int zeroCount = 0;
        int longest = 0;

        // O(n) time: each endpoint moves forward at most n times; O(1) auxiliary space.
        for (int right = 0; right < nums.length; right++) {
            if (nums[right] == 0) zeroCount++;
            while (zeroCount > 1) {
                if (nums[left] == 0) zeroCount--;
                left++;
            }
            longest = Math.max(longest, right - left);
        }

        return longest;
    }

    public static int longestSubarrayWithPrefixSuffix(int[] nums) {
        int length = nums.length;
        int[] prefixOnes = new int[length];
        int[] suffixOnes = new int[length];

        // O(n) scan; the prefix count array uses O(n) space.
        for (int i = 0; i < length; i++) {
            if (nums[i] == 1) prefixOnes[i] = 1 + (i > 0 ? prefixOnes[i - 1] : 0);
        }

        // O(n) scan; the suffix count array also uses O(n) space.
        for (int i = length - 1; i >= 0; i--) {
            if (nums[i] == 1) suffixOnes[i] = 1 + (i + 1 < length ? suffixOnes[i + 1] : 0);
        }

        int longest = 0;
        // O(n) scan; total time is O(n), with O(n) auxiliary space for both count arrays.
        for (int i = 0; i < length; i++) {
            int onesBefore = i > 0 ? prefixOnes[i - 1] : 0;
            int onesAfter = i + 1 < length ? suffixOnes[i + 1] : 0;
            longest = Math.max(longest, onesBefore + onesAfter);
        }

        return longest;
    }
}
