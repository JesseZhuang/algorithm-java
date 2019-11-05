package array;

/**
 * LeetCode. Medium. Tags: Array, Binary Search.
 * A peak element is an element that is greater than its neighbors.
 * <p>
 * Given an input array nums, where nums[i] ≠ nums[i+1], find a peak element and return its index.
 * <p>
 * The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
 * <p>
 * You may imagine that nums[-1] = nums[n] = -∞.
 * <p>
 * Example 1:
 * <pre>
 * Input: nums = [1,2,3,1]
 * Output: 2
 * </pre>
 * Explanation: 3 is a peak element and your function should return the index number 2.
 * <p>
 * Example 2:
 * <pre>
 * Input: nums = [1,2,1,3,5,6,4]
 * Output: 1 or 5
 * </pre>
 * Explanation: Your function can return either index number 1 where the peak element is 2,
 * or index number 5 where the peak element is 6.
 * Note:
 * <p>
 * Your solution should be in logarithmic complexity.
 */
public class FindPeakElement {
    public int findPeakElementRecur(int[] nums) {
        return search(nums, 0, nums.length - 1);
    }

    public int search(int[] nums, int l, int r) {
        if (l == r) return l;
        int mid = (l + r) / 2;
        if (nums[mid] > nums[mid + 1]) return search(nums, l, mid);
        return search(nums, mid + 1, r);
    }

    public int findPeakElementIter(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = (l + r) / 2;
            if (nums[mid] > nums[mid + 1])
                r = mid;
            else
                l = mid + 1;
        }
        return l;
    }
}
