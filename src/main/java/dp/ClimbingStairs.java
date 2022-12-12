package dp;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 70, easy. Tags: math, dynamic programming, memoization.
 * You are climbing a staircase. It takes n steps to reach the top.
 * <p>
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 * <p>
 * Example 1:
 * <p>
 * Input: n = 2
 * Output: 2
 * Explanation: There are two ways to climb to the top.
 * 1. 1 step + 1 step
 * 2. 2 steps
 * Example 2:
 * <p>
 * Input: n = 3
 * Output: 3
 * Explanation: There are three ways to climb to the top.
 * 1. 1 step + 1 step + 1 step
 * 2. 1 step + 2 steps
 * 3. 2 steps + 1 step
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 45
 */
public class ClimbingStairs {
    // 0ms, 40.9 Mb. O(N) time, O(1) space.
    public int climbStairsIterative(int n) {
        if (n <= 2) return n;
        int a = 1, res = 2;
        for (int i = 2; i < n; i++) {
            res += a;
            a = res - a;
        }
        return res;
    }

    Map<Integer, Integer> dp = new HashMap();

    // O(2^N) or O(1.618^N) time, O(N) space. https://stackoverflow.com/a/360938/3951955. 0ms, 39.1 Mb.
    public int climbStairsMemo(int n) {
        if (n <= 2) return n;
        if (dp.containsKey(n)) return dp.get(n);
        int steps = climbStairsMemo(n - 1) + climbStairsMemo(n - 2);
        dp.put(n, steps);
        return steps;
    }
}
