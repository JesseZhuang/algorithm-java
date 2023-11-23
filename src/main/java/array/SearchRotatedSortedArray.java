package array;

/**
 * LeetCode 33, medium, tags: array, binary search.
 * <p>
 * There is an integer array nums sorted in ascending order (with distinct values).
 * <p>
 * Prior to being passed to your function, nums is possibly rotated at an unknown pivot index k (1 <= k < nums.length)
 * such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed).
 * For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].
 * <p>
 * Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums,
 * or -1 if it is not in nums.
 * <p>
 * You must write an algorithm with O(log n) runtime complexity.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [4,5,6,7,0,1,2], target = 0
 * Output: 4
 * Example 2:
 * <p>
 * Input: nums = [4,5,6,7,0,1,2], target = 3
 * Output: -1
 * Example 3:
 * <p>
 * Input: nums = [1], target = 0
 * Output: -1
 * <p>
 * <p>
 * Constraints:
 * <pre>
 * 1 <= nums.length <= 5000
 * -10^4 <= nums[i] <= 10^4
 * All values of nums are unique.
 * nums is an ascending array that is possibly rotated.
 * -10^4 <= target <= 10^4
 * </pre>
 */
public class SearchRotatedSortedArray {

    // solution 1, 1 ms, 43 Mb. No infinity trick. lgn time, 1 space.
    public static int search2(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            //target and mid are on the same side
            if (nums[mid] < nums[0] == target < nums[0]) {
                if (nums[mid] < target) left = mid + 1; // search right of mid
                else if (nums[mid] > target) right = mid - 1;
                else return mid;
            } else if (target < nums[0]) left = mid + 1; // target<nums[0]&&nums[mid]>=nums[0], search right of mid
            else right = mid - 1; // target>=nums[0]&&nums[mid]<nums[0], search left of mid
        }
        return -1;
    }

    // solution 2, 2 pass, find min and use modulus. 1 ms, 42.6 Mb. lgn time, 1 space.
    public static int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[right]) left = mid + 1;
            else right = mid;
        }
        int minIndex = left;
        left = 0;
        right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int realMid = (mid + minIndex) % nums.length;
            if (nums[realMid] == target) return realMid;
            else if (nums[realMid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }

    // Infinity trick, 1ms, 43.2 Mb. Essentially same as solution 1.
    public static int searchInf(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            double num;
            // target and element examining are in the same section, does not matter rotated or not
            if (nums[mid] < nums[0] == target < nums[0]) num = nums[mid];
                // target in right section, mid in left
            else if (target < nums[0]) num = Double.NEGATIVE_INFINITY; // not Double.MIN
                // target in left section, mid in right
            else num = Double.POSITIVE_INFINITY; // not Double.Max
            if (num < target) left = mid + 1;
            else if (num > target) right = mid - 1;
            else return mid;
        }
        return -1;
    }


    public static void main(String[] args) {
        System.out.println(search2(new int[]{4, 5, 6, 7, 0, 1, 2}, 0));
    }
}
