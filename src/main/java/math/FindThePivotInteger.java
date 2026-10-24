package math;

/**
 * LeetCode 2485, easy, tags: math, prefix sum.
 */
public final class FindThePivotInteger {
    private FindThePivotInteger() {}

    /** O(1) time and O(1) space: the pivot squared equals the sum from 1 to n. */
    public static int pivotInteger(int n) {
        long total = (long) n * (n + 1) / 2;
        int pivot = (int) Math.sqrt(total);
        return (long) pivot * pivot == total ? pivot : -1;
    }

    /** O(n) time and O(1) space: compare running left and right sums. */
    public static int pivotIntegerPrefixSum(int n) {
        int left = 0;
        int right = n * (n + 1) / 2;
        // O(n) time, O(1) space.
        for (int pivot = 1; pivot <= n; pivot++) {
            left += pivot;
            if (left == right) return pivot;
            right -= pivot;
        }
        return -1;
    }
}
