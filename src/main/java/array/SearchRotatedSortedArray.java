package array;

/** LeetCode 33, medium, tags: array, binary search. */
@SuppressWarnings("unused")
public final class SearchRotatedSortedArray {
    private SearchRotatedSortedArray() {}

    // O(log n) time, O(1) space.
    static class Solution {
        public static int search(int[] nums, int target) {
            int l = 0, r = nums.length - 1;
            while (l <= r) {
                int m = (l + r) / 2;
                if (nums[m] == target) return m;
                if (nums[l] <= nums[m]) { // left half sorted
                    if (nums[l] <= target && target < nums[m]) r = m - 1;
                    else l = m + 1;
                } else { // right half sorted
                    if (nums[m] < target && target <= nums[r]) l = m + 1;
                    else r = m - 1;
                }
            }
            return -1;
        }
    }

    // O(log n) time, O(1) space.
    static class Solution2 {
        public static int search(int[] nums, int target) {
            int n = nums.length;
            // find pivot (index of minimum element)
            int l = 0, r = n - 1;
            while (l < r) {
                int m = (l + r) / 2;
                if (nums[m] > nums[r]) l = m + 1;
                else r = m;
            }
            int pivot = l;
            // binary search in the correct half
            if (target >= nums[pivot] && target <= nums[n - 1]) {
                l = pivot;
                r = n - 1;
            } else {
                l = 0;
                r = pivot - 1;
            }
            while (l <= r) {
                int m = (l + r) / 2;
                if (nums[m] == target) return m;
                else if (nums[m] < target) l = m + 1;
                else r = m - 1;
            }
            return -1;
        }
    }
}
