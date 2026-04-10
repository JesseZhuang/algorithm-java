package stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode 739, medium, tags: array, stack, monotonic stack.
 * <p>
 * Given an array of integers temperatures, return an array answer such that answer[i] is the number of days
 * you have to wait after the ith day to get a warmer temperature. If there is no future day for which this
 * is possible, keep answer[i] == 0 instead.
 * <p>
 * Example 1:
 * Input: temperatures = [73,74,75,71,69,72,76,73]
 * Output: [1,1,4,2,1,1,0,0]
 * <p>
 * Example 2:
 * Input: temperatures = [30,40,50,60]
 * Output: [1,1,1,0]
 * <p>
 * Example 3:
 * Input: temperatures = [30,60,90]
 * Output: [1,1,0]
 * <p>
 * Constraints:
 * 1 <= temperatures.length <= 10^5
 * 30 <= temperatures[i] <= 100
 */
public final class DailyTemperatures {
    private DailyTemperatures() {}

    public static int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] res = new int[n];
        Deque<Integer> stack = new ArrayDeque<>(); // monotonic decreasing stack of indices
        for (int i = 0; i < n; i++) { // O(n)
            while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) { // O(n) total
                int j = stack.pop();
                res[j] = i - j;
            }
            stack.push(i);
        }
        return res; // Time O(n), Space O(n)
    }
}
