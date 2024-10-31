package dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * LeetCode 2463, hard, tags: array, dp, sorting.
 * <p>
 * There are some robots and factories on the X-axis. You are given an integer array robot where robot[i] is the
 * position of the ith robot. You are also given a 2D integer array factory where factory[j] = [position_j, limit_j]
 * indicates that position_j is the position of the jth factory and that the jth factory can repair at most limit_j
 * robots.
 * <p>
 * The positions of each robot are unique. The positions of each factory are also unique. Note that a robot can be
 * in the same position as a factory initially.
 * <p>
 * All the robots are initially broken; they keep moving in one direction. The direction could be the negative or the
 * positive direction of the X-axis. When a robot reaches a factory that did not reach its limit, the factory repairs
 * the robot, and it stops moving.
 * <p>
 * At any moment, you can set the initial direction of moving for some robot. Your target is to minimize the total
 * distance traveled by all the robots.
 * <p>
 * Return the minimum total distance traveled by all the robots. The test cases are generated such that all the robots
 * can be repaired.
 * <p>
 * Note that
 * <p>
 * All robots move at the same speed.
 * If two robots move in the same direction, they will never collide.
 * If two robots move in opposite directions and they meet at some point, they do not collide. They cross each other.
 * If a robot passes by a factory that reached its limits, it crosses it as if it does not exist.
 * If the robot moved from a position x to a position y, the distance it moved is |y - x|.
 * <p>
 * <p>
 * Example 1: https://assets.leetcode.com/uploads/2022/09/15/example1.jpg
 * <p>
 * <p>
 * Input: robot = [0,4,6], factory = [[2,2],[6,2]]
 * Output: 4
 * Explanation: As shown in the figure:
 * - The first robot at position 0 moves in the positive direction. It will be repaired at the first factory.
 * - The second robot at position 4 moves in the negative direction. It will be repaired at the first factory.
 * - The third robot at position 6 will be repaired at the second factory. It does not need to move.
 * The limit of the first factory is 2, and it fixed 2 robots.
 * The limit of the second factory is 2, and it fixed 1 robot.
 * The total distance is |2 - 0| + |2 - 4| + |6 - 6| = 4. It can be shown that we cannot achieve a better total
 * distance than 4.
 * Example 2: https://assets.leetcode.com/uploads/2022/09/15/example-2.jpg
 * <p>
 * <p>
 * Input: robot = [1,-1], factory = [[-2,1],[2,1]]
 * Output: 2
 * Explanation: As shown in the figure:
 * - The first robot at position 1 moves in the positive direction. It will be repaired at the second factory.
 * - The second robot at position -1 moves in the negative direction. It will be repaired at the first factory.
 * The limit of the first factory is 1, and it fixed 1 robot.
 * The limit of the second factory is 1, and it fixed 1 robot.
 * The total distance is |2 - 1| + |(-2) - (-1)| = 2. It can be shown that we cannot achieve a better total distance
 * than 2.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= robot.length, factory.length <= 100, n,m, k==average limit for factory
 * factory[j].length == 2
 * -10^9 <= robot[i], position_j <= 10^9
 * 0 <= limit_j <= robot.length
 * The input will be generated such that it is always possible to repair every robot.
 * <p>
 * Hint 1
 * Sort robots and factories by their positions.
 * Hint 2
 * After sorting, notice that each factory should repair some subsegment of robots.
 * Hint 3
 * Find the minimum total distance to repair first i robots with first j factories.
 */
@SuppressWarnings("unused")
public class MinDistanceTravel {
    // dp(j,k,i) to fix robots[0,i-1] with factories [0,j] limit k
    // where i in [1,n] backwards, j in [1,m-1], k in [1,l]
    // option 1, skip factory[j], res1=dp(j,k,i)
    // option 2, fix in factory[j], res2=dp(j,k,i-1) + abs(robots[i-1]-fac[j][0]), [0,i-2] already fixed + cost for i-1
    // @stanislav-iablokov and @lee-215, mnk, n+Sort
    static class Solution {
        public long minimumTotalDistance(List<Integer> R, int[][] F) {
            int n = R.size(), m = F.length;
            long[] dp = new long[n + 1];
            Arrays.fill(dp, (long) 1e12);
            Collections.sort(R);
            Arrays.sort(F, Comparator.comparingInt(a -> a[0]));
            dp[0] = 0; // fixing no robot total distance is 0
            for (int[] fac : F) // j
                for (int k = 1; k <= fac[1]; k++)
                    for (int i = n; i > 0; i--) // start,end = 0,min(j+fac[1],n)+1, iterate backwards
                        dp[i] = Math.min(Math.abs(R.get(i - 1) - fac[0]) + dp[i - 1], dp[i]);
            return dp[n];
        }
    }

    // dp[i][j]: min total distance for robots [i,rCnt-1] and factories [j,fCnt-1]
    // assign = abs(robot[i] - factoryPositions[j]) + dp[i + 1][j + 1]
    // skip = dp[i][j + 1], robot will be repaired at j+1th factory
    // nmk, mk+Sort
    static class Solution2 {
        public long minimumTotalDistance(List<Integer> robots, int[][] factories) {
            // Sort robots and factories by position
            Collections.sort(robots);
            Arrays.sort(factories, Comparator.comparingInt(a -> a[0]));
            // Flatten factory positions according to their capacities
            List<Integer> fPos = new ArrayList<>();
            for (int[] factory : factories)
                for (int i = 0; i < factory[1]; i++) fPos.add(factory[0]);
            int n = robots.size(), m = fPos.size();
            long[] dp = new long[m + 1];
            // Fill DP table using two rows for optimization
            for (int i = n - 1; i >= 0; i--) {
                // No factories left case
                if (i != n - 1) dp[m] = (long) 1e12;
                long[] ndp = new long[m + 1];
                ndp[m] = (long) 1e12;
                for (int j = m - 1; j >= 0; j--) {
                    // Assign current robot i to current factory j
                    long assign = Math.abs((long) robots.get(i) - fPos.get(j)) + dp[j + 1];
                    // Skip current factory for this robot
                    long skip = ndp[j + 1];
                    // Take the minimum option
                    ndp[j] = Math.min(assign, skip);
                }
                dp = ndp;
            }
            // Return minimum distance starting from the first robot
            return dp[0];
        }
    }
}
