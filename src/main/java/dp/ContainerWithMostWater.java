package dp;

/**
 * LeetCode 11, medium, tags: array, two pointer, greedy, dynamic programming.
 * <p>
 * You are given an integer array height of length n. There are n vertical lines drawn such that the two endpoints of
 * the ith line are (i, 0) and (i, height[i]).
 * <p>
 * Find two lines that together with the x-axis form a container, such that the container contains the most water.
 * <p>
 * Return the maximum amount of water a container can store.
 * <p>
 * Notice that you may not slant the container.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: height = [1,8,6,2,5,4,8,3,7]
 * Output: 49
 * Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of
 * water (blue section) the container can contain is 49.
 * Example 2:
 * <p>
 * Input: height = [1,1]
 * Output: 1
 * <p>
 * <p>
 * Constraints:
 * <p>
 * n == height.length
 * 2 <= n <= 105
 * 0 <= height[i] <= 104
 */
public class ContainerWithMostWater {

    // 1ms, 52.9Mb. O(N) time, O(1) space. 2 Pointer.
    public int maxArea(int[] height) {
        int water = 0;
        int i = 0, j = height.length - 1;
        while (i < j) {
            int h = Math.min(height[i], height[j]);
            water = Math.max(water, (j - i) * h);
            while (height[i] <= h && i < j) i++; // move the lower height edge line
            while (height[j] <= h && i < j) j--;
        }
        return water;
    }

    // 24 ms, 81.5 Mb. Only move one pointer, a little less greedy.
    public int maxArea2(int[] height) {
        int water = 0;
        int i = 0, j = height.length - 1;
        while (i < j) {
            water = Math.max(water, (j - i) * Math.min(height[i], height[j]));
            if (height[i] < height[j]) i++;
            else j--;
        }
        return water;
    }
}
