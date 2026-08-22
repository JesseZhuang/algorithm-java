package binary_search;

import java.util.Random;

/**
 * LeetCode 528, medium, tags: array, math, binary search, prefix sum, randomized.
 * <p>
 * You are given a 0-indexed array of positive integers w where w[i] describes the weight of the ith index.
 * <p>
 * You need to implement the function pickIndex(), which randomly picks an index in the range [0, w.length - 1]
 * (inclusive) and returns it. The probability of picking an index i is w[i] / sum(w).
 * <p>
 * Example 1:
 * Input: ["Solution","pickIndex"] [[[1]],[]]
 * Output: [null,0]
 * <p>
 * Example 2:
 * Input: ["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
 * [[[1,3]],[],[],[],[],[]]
 * Output: [null,1,1,1,1,0]
 * <p>
 * Constraints:
 * 1 <= w.length <= 10^4
 * 1 <= w[i] <= 10^5
 * pickIndex will be called at most 10^4 times.
 */
public final class RandomPickWithWeight {

    private final int[] prefix;
    private final int total;
    private final Random rand;

    /**
     * Constructor. O(n) time, O(n) space.
     * Builds prefix sum array for binary search in pickIndex.
     */
    public RandomPickWithWeight(int[] w) {
        prefix = new int[w.length];
        prefix[0] = w[0];
        for (int i = 1; i < w.length; i++) {
            prefix[i] = prefix[i - 1] + w[i];
        }
        total = prefix[w.length - 1];
        rand = new Random();
    }

    /**
     * Binary search on prefix sums. O(log n) time, O(1) space.
     * Generates a random number in [1, total] and finds the leftmost index
     * whose prefix sum is >= that number.
     */
    public int pickIndex() {
        int target = rand.nextInt(total) + 1; // [1, total]
        int lo = 0, hi = prefix.length - 1;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (prefix[mid] >= target) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }
}

/**
 * Linear scan approach. Constructor O(n) time O(n) space, pickIndex O(n) time O(1) space.
 */
final class RandomPickWithWeight2 {

    private final int[] prefix;
    private final int total;
    private final Random rand;

    public RandomPickWithWeight2(int[] w) {
        prefix = new int[w.length];
        prefix[0] = w[0];
        for (int i = 1; i < w.length; i++) {
            prefix[i] = prefix[i - 1] + w[i];
        }
        total = prefix[w.length - 1];
        rand = new Random();
    }

    /**
     * Linear scan on prefix sums. O(n) time, O(1) space.
     */
    public int pickIndex() {
        int target = rand.nextInt(total) + 1;
        for (int i = 0; i < prefix.length; i++) {
            if (prefix[i] >= target) return i;
        }
        return prefix.length - 1; // should not reach here
    }
}
