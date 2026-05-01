package math;

/**
 * LeetCode 1041, medium, tags: math, string, simulation.
 * <p>
 * On an infinite plane, a robot initially stands at (0, 0) and faces north.
 * The robot can receive one of three instructions:
 * "G": go straight 1 unit.
 * "L": turn 90 degrees to the left.
 * "R": turn 90 degrees to the right.
 * The robot performs the instructions given in order, and repeats them forever.
 * Return true if and only if there exists a circle in the plane such that the robot never leaves the circle.
 */
@SuppressWarnings("unused")
public final class RobotBoundedInCircle {
    private RobotBoundedInCircle() {}

    // O(n), O(1).
    public static boolean isRobotBounded(String instructions) {
        int x = 0, y = 0, dir = 0; // 0:N, 1:E, 2:S, 3:W
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        for (char c : instructions.toCharArray()) {
            if (c == 'G') {
                x += dirs[dir][0];
                y += dirs[dir][1];
            } else if (c == 'R') dir = (dir + 1) % 4;
            else dir = (dir + 3) % 4;
        }
        return (x == 0 && y == 0) || dir > 0;
    }
}
