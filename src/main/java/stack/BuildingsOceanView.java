package stack;

import java.util.LinkedList;

/**
 * LeetCode 1762, LintCode 3714, medium, tags: array, stack, monotonic stack.
 * <p>
 * There are n buildings in a line. You are given an integer array heights of size n that represents the heights of
 * the buildings in the line.
 * <p>
 * The ocean is to the right of the buildings. A building has an ocean view if the building can see the ocean
 * without obstructions. Formally, a building has an ocean view if all the buildings to its right have a smaller height.
 * <p>
 * Return a list of indices (0-indexed) of buildings that have an ocean view, sorted in increasing order.
 * <p>
 * Example 1:
 * <p>
 * Input: heights = [4,2,3,1]
 * Output: [0,2,3]
 * Explanation: Building 1 (0-indexed) does not have an ocean view because building 2 is taller.
 * Example 2:
 * <p>
 * Input: heights = [4,3,2,1]
 * Output: [0,1,2,3]
 * Explanation: All the buildings have an ocean view.
 * Example 3:
 * <p>
 * Input: heights = [1,3,2,4]
 * Output: [3]
 * Explanation: Only building 3 has an ocean view.
 * Constraints:
 * <p>
 * 1 <= heights.length <= 10^5
 * 1 <= heights[i] <= 10^9
 */
public class BuildingsOceanView {
    // solution 1, stack, n time, 1 or n space (if not counting space used for result), LintCode 1002ms, 44.3Mb.
    public int[] findBuildings(int[] heights) {
        int max = 0;
        LinkedList<Integer> ans = new LinkedList<>();
        for (int i = heights.length - 1; i >= 0; --i) {
            int v = heights[i];
            if (max < v) {
                ans.addFirst(i); // push or addFirst
                max = v;
            }
        }
        return ans.stream().mapToInt(i -> i).toArray();
    }
}
