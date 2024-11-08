package math;

/**
 * LeetCode 1041, medium, tags: math, string, simulation.
 * Companies: LinkedIn.
 * <p>
 * On an infinite plane, a robot initially stands at (0, 0) and faces north. Note that:
 * <p>
 * The north direction is the positive direction of the y-axis.
 * The south direction is the negative direction of the y-axis.
 * The east direction is the positive direction of the x-axis.
 * The west direction is the negative direction of the x-axis.
 * The robot can receive one of three instructions:
 * <p>
 * "G": go straight 1 unit.
 * "L": turn 90 degrees to the left (i.e., anti-clockwise direction).
 * "R": turn 90 degrees to the right (i.e., clockwise direction).
 * The robot performs the instructions given in order, and repeats them forever.
 * <p>
 * Return true if and only if there exists a circle in the plane such that the robot never leaves the circle.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: instructions = "GGLLGG"
 * Output: true
 * Explanation: The robot is initially at (0, 0) facing the north direction.
 * "G": move one step. Position: (0, 1). Direction: North.
 * "G": move one step. Position: (0, 2). Direction: North.
 * "L": turn 90 degrees anti-clockwise. Position: (0, 2). Direction: West.
 * "L": turn 90 degrees anti-clockwise. Position: (0, 2). Direction: South.
 * "G": move one step. Position: (0, 1). Direction: South.
 * "G": move one step. Position: (0, 0). Direction: South.
 * Repeating the instructions, the robot goes into the cycle: (0, 0) --> (0, 1) --> (0, 2) --> (0, 1) --> (0, 0).
 * Based on that, we return true.
 * Example 2:
 * <p>
 * Input: instructions = "GG"
 * Output: false
 * Explanation: The robot is initially at (0, 0) facing the north direction.
 * "G": move one step. Position: (0, 1). Direction: North.
 * "G": move one step. Position: (0, 2). Direction: North.
 * Repeating the instructions, keeps advancing in the north direction and does not go into cycles.
 * Based on that, we return false.
 * Example 3:
 * <p>
 * Input: instructions = "GL"
 * Output: true
 * Explanation: The robot is initially at (0, 0) facing the north direction.
 * "G": move one step. Position: (0, 1). Direction: North.
 * "L": turn 90 degrees anti-clockwise. Position: (0, 1). Direction: West.
 * "G": move one step. Position: (-1, 1). Direction: West.
 * "L": turn 90 degrees anti-clockwise. Position: (-1, 1). Direction: South.
 * "G": move one step. Position: (-1, 0). Direction: South.
 * "L": turn 90 degrees anti-clockwise. Position: (-1, 0). Direction: East.
 * "G": move one step. Position: (0, 0). Direction: East.
 * "L": turn 90 degrees anti-clockwise. Position: (0, 0). Direction: North.
 * Repeating the instructions, the robot goes into the cycle: (0, 0) --> (0, 1) --> (-1, 1) --> (-1, 0) --> (0, 0).
 * Based on that, we return true.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= instructions.length <= 100
 * instructions[i] is 'G', 'L' or, 'R'.
 * <p>
 * Hint 1
 * Calculate the final vector of how the robot travels after executing all instructions once - it consists of a change
 * in position plus a change in direction.
 * Hint 2
 * The robot stays in the circle if and only if (looking at the final vector) it changes direction (ie.
 * doesn't stay pointing north), or it moves 0.
 */
@SuppressWarnings("unused")
public class RobotCircle {
    static class Solution {
        public boolean isRobotBounded(String ins) {
            int x = 0, y = 0, i = 0, d[][] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // n,e,s,w; clockwise
            for (int j = 0; j < ins.length(); ++j)
                if (ins.charAt(j) == 'R') i = (i + 1) % 4; // clockwise
                else if (ins.charAt(j) == 'L') i = (i + 3) % 4; // counter-clockwise
                else {
                    x += d[i][0];
                    y += d[i][1];
                }
            return x == 0 && y == 0 || i > 0; // returns to origin or not facing north, then eventually be back
        }
    }
}
