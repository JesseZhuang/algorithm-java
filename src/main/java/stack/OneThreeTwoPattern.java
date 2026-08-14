package stack;

/**
 * LeetCode 456 - 132 Pattern
 *
 * Given an array of n integers nums, a 132 pattern is a subsequence of three integers
 * nums[i], nums[j], nums[k] such that i < j < k and nums[i] < nums[k] < nums[j].
 */
public final class OneThreeTwoPattern {

    private OneThreeTwoPattern() {}

    /**
     * Approach 1: Monotonic stack scanning right to left, tracking largest popped value as '2' candidate.
     *
     * We maintain a decreasing stack from top to bottom. When we encounter a value larger than
     * the stack top, we pop elements — each popped element is a candidate for the '2' role
     * (the middle value in the 132 pattern). We track the maximum popped value. If any element
     * to the left is smaller than this max popped value, we found a 132 pattern.
     *
     * Time O(n), Space O(n)
     */
    public static boolean find132patternStack(int[] nums) {
        if (nums == null || nums.length < 3) return false;

        int n = nums.length;
        int[] stack = new int[n];
        int top = -1;
        int third = Integer.MIN_VALUE; // the '2' candidate (nums[k])

        for (int i = n - 1; i >= 0; i--) {
            if (nums[i] < third) {
                return true; // nums[i] is the '1', third is the '2', the element that pushed third out is '3'
            }
            while (top >= 0 && nums[i] > stack[top]) {
                third = stack[top--];
            }
            stack[++top] = nums[i];
        }
        return false;
    }

    /**
     * Approach 2: Prefix min + monotonic stack.
     *
     * We precompute the prefix minimum array (candidates for '1'). Then we scan from right to
     * left with a stack that maintains candidates for '2'. For each j, if nums[j] > min[j]
     * and there exists a stack element between min[j] and nums[j], we found a 132 pattern.
     *
     * Time O(n), Space O(n)
     */
    public static boolean find132patternPrefixMin(int[] nums) {
        if (nums == null || nums.length < 3) return false;

        int n = nums.length;
        int[] minPrefix = new int[n];
        minPrefix[0] = nums[0];
        for (int i = 1; i < n; i++) {
            minPrefix[i] = Math.min(minPrefix[i - 1], nums[i]);
        }

        int[] stack = new int[n];
        int top = -1;

        for (int i = n - 1; i >= 1; i--) {
            if (nums[i] > minPrefix[i]) {
                // Pop elements that are <= minPrefix[i] since they can't serve as '2'
                while (top >= 0 && stack[top] <= minPrefix[i]) {
                    top--;
                }
                // If stack top is less than nums[i], we found '1' < '2' < '3'
                if (top >= 0 && stack[top] < nums[i]) {
                    return true;
                }
                stack[++top] = nums[i];
            }
        }
        return false;
    }
}
