package sliding;

import java.util.Arrays;

/** LeetCode 2090: K Radius Subarray Averages. */
@SuppressWarnings("unused")
public final class KRadiusSubarrayAverages {

    private KRadiusSubarrayAverages() {
    }

    // O(n) time and O(1) auxiliary space, excluding the returned array.
    public static int[] getAverages(int[] nums, int k) {
        int[] averages = new int[nums.length];
        Arrays.fill(averages, -1);
        int windowSize = 2 * k + 1;
        if (windowSize > nums.length) return averages;

        long sum = 0;
        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];
            if (right >= windowSize - 1) {
                averages[right - k] = (int) (sum / windowSize);
                sum -= nums[right - windowSize + 1];
            }
        }
        return averages;
    }

    // O(n) time and O(n) auxiliary space for the prefix sums.
    public static int[] getAveragesByPrefixSum(int[] nums, int k) {
        int[] averages = new int[nums.length];
        Arrays.fill(averages, -1);
        int windowSize = 2 * k + 1;
        if (windowSize > nums.length) return averages;

        long[] prefix = new long[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }
        for (int center = k; center < nums.length - k; center++) {
            long sum = prefix[center + k + 1] - prefix[center - k];
            averages[center] = (int) (sum / windowSize);
        }
        return averages;
    }
}
