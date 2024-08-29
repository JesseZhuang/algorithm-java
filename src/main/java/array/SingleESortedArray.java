package array;

/**
 * LeetCode 540, LintCode 1183, medium, tags: array, binary search.
 * <p>
 * You are given a sorted array consisting of only integers where every element appears exactly twice, except for one
 * element which appears exactly once.
 * <p>
 * Return the single element that appears only once.
 * <p>
 * Your solution must run in O(log n) time and O(1) space.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,1,2,3,3,4,4,8,8]
 * Output: 2
 * Example 2:
 * <p>
 * Input: nums = [3,3,7,7,10,11,11]
 * Output: 10
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 10^5
 * 0 <= nums[i] <= 10^5
 */
public class SingleESortedArray {
    // binary search, lgn time, 1 space, 0ms, 50.24Mb
    public int singleNonDuplicate(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == nums[mid ^ 1]) l = mid + 1; // compare with mid+1 when even, mid-1 when odd
            else r = mid;
        }
        return nums[l];
        // for [3,3,7,7,10,11,11] l,r: [0,6],[4,6],[4,5],[4,4]
    }
}
