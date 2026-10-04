package binary_search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LeetCode 2602, medium, tags: array, binary search, sorting, prefix sum.
 */
public final class MinimumOperationsToMakeAllArrayElementsEqual {

    private MinimumOperationsToMakeAllArrayElementsEqual() {
    }

    /**
     * Sorts a copy and uses prefix sums to calculate each query in O(log n) time.
     * Overall time: O((n + m) log n). Space: O(n).
     */
    public static List<Long> minOperations(int[] nums, int[] queries) {
        int[] sorted = Arrays.copyOf(nums, nums.length);
        Arrays.sort(sorted);

        long[] prefixSums = new long[sorted.length + 1];
        for (int index = 0; index < sorted.length; index++) {
            prefixSums[index + 1] = prefixSums[index] + sorted[index];
        }

        List<Long> operations = new ArrayList<>(queries.length);
        for (int query : queries) {
            int split = lowerBound(sorted, query);
            long leftCost = (long) query * split - prefixSums[split];
            long rightCost = prefixSums[sorted.length] - prefixSums[split]
                    - (long) query * (sorted.length - split);
            operations.add(leftCost + rightCost);
        }
        return operations;
    }

    private static int lowerBound(int[] nums, int target) {
        int low = 0;
        int high = nums.length;
        while (low < high) {
            int middle = low + (high - low) / 2;
            if (nums[middle] < target) {
                low = middle + 1;
            } else {
                high = middle;
            }
        }
        return low;
    }
}
