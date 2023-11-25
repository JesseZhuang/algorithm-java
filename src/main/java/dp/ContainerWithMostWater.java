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
 * 2 <= n <= 10^5
 * 0 <= height[i] <= 10^4
 * <p>
 * Hint 1
 * If you simulate the problem, it will be O(n^2) which is not efficient.
 * Hint 2
 * Try to use two-pointers. Set one pointer to the left and one to the right of the array.
 * Always move the pointer that points to the lower line.
 * Hint 3
 * How can you calculate the amount of water at each step?
 */
public class ContainerWithMostWater {

    // solution 1, 1ms, 52.9Mb. O(N) time, O(1) space. 2 Pointer.
    public int maxArea(int[] height) {
        int res = 0, l = 0, r = height.length - 1;
        while (l < r) {
            int h = Math.min(height[l], height[r]);
            res = Math.max(res, (r - l) * h);
            while (height[l] <= h && l < r) l++; // move the lower height edge line
            while (height[r] <= h && l < r) r--;
        }
        return res;
    }

    // solution 2, 24 ms, 81.5 Mb. Only move one pointer, a little less greedy.
    public int maxArea2(int[] height) {
        int res = 0;
        int l = 0, r = height.length - 1;
        while (l < r) {
            res = Math.max(res, (r - l) * Math.min(height[l], height[r]));
            if (height[l] < height[r]) l++;
            else r--;
        }
        return res;
    }
}
