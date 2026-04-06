package hash;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>
 * LeetCode 874. Medium. Tags: array, hash table, simulation.
 * <p>
 * A robot on an infinite XY-plane starts at point (0, 0) facing north. The robot can receive a sequence of
 * these three possible types of commands:
 * <ul>
 * <li>-2: Turn left 90 degrees.
 * <li>-1: Turn right 90 degrees.
 * <li>1 <= k <= 9: Move forward k units, one unit at a time.
 * </ul>
 * <p>
 * Some of the grid squares are obstacles. The i-th obstacle is at grid point obstacles[i] = (xi, yi).
 * If the robot runs into an obstacle, it will stay in its current position and move on to the next command.
 * <p>
 * Return the maximum Euclidean distance squared that the robot ever gets from the origin.
 * <pre>
 * Example 1:
 * Input: commands = [4,-1,3], obstacles = []
 * Output: 25
 * Explanation: The robot starts at (0,0): Move north 4 units to (0,4). Turn right.
 * Move east 3 units to (3,4). The furthest point the robot ever gets is (3,4), which squared is 25.
 *
 * Example 2:
 * Input: commands = [4,-1,4,-2,4], obstacles = [[2,4]]
 * Output: 65
 * Explanation: The robot starts at (0,0): Move north 4 units to (0,4). Turn right.
 * Move east 4 units to (4,4). Turn left. Move north 4 units to (4,8).
 * The furthest point the robot ever gets from the origin is (4,8), which squared is 65.
 *
 * Example 3:
 * Input: commands = [6,-1,-1,6], obstacles = [[0,0]]
 * Output: 36
 * Explanation: The robot starts at (0,0): Move north 6 units to (0,6). Turn right.
 * Turn right. Move south 6 units to (0,0). Note that the obstacle at (0,0) does not
 * prevent the robot from crossing it. The furthest point is (0,6), which squared is 36.
 *
 * Constraints:
 * 1 <= commands.length <= 10^4
 * commands[i] is either -2, -1, or an integer in the range [1, 9].
 * 0 <= obstacles.length <= 10^4
 * -3 * 10^4 <= xi, yi <= 3 * 10^4
 * The answer is guaranteed to be less than 2^31.
 * </pre>
 */
public final class WalkingRobotSimulation {
    private WalkingRobotSimulation() {}

    /**
     * O(n*k+m) time, O(m) space, where n is commands length, k is max command value (9), m is obstacles length.
     */
    public static int robotSim(int[] commands, int[][] obstacles) {
        int[] dx = {0, 1, 0, -1}; // N, E, S, W
        int[] dy = {1, 0, -1, 0};
        Set<Long> obs = new HashSet<>();
        for (int[] o : obstacles) obs.add(encode(o[0], o[1]));
        int x = 0, y = 0, d = 0, res = 0;
        for (int c : commands) {
            if (c == -2) d = (d + 3) % 4;
            else if (c == -1) d = (d + 1) % 4;
            else {
                for (int i = 0; i < c; i++) {
                    int nx = x + dx[d], ny = y + dy[d];
                    if (obs.contains(encode(nx, ny))) break;
                    x = nx;
                    y = ny;
                }
                res = Math.max(res, x * x + y * y);
            }
        }
        return res;
    }

    private static long encode(int x, int y) {
        return ((long) x + 30001) * 60003 + (y + 30001);
    }
}
