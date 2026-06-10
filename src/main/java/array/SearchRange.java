package array;

/** LeetCode 34, medium, tags: array, binary search. */
@SuppressWarnings("unused")
public final class SearchRange {
    private SearchRange() {}

    // O(log n) time, O(1) space.
    // Two binary searches: one for left boundary, one for right boundary.
    static class Solution {
        public static int[] searchRange(int[] nums, int target) {
            int[] result = {-1, -1};
            if (nums == null || nums.length == 0) return result;

            // Find left boundary
            int left = findBoundary(nums, target, true);
            if (left == -1) return result;

            // Find right boundary
            int right = findBoundary(nums, target, false);

            result[0] = left;
            result[1] = right;
            return result;
        }

        private static int findBoundary(int[] nums, int target, boolean findLeft) {
            int l = 0, r = nums.length - 1;
            int boundary = -1;

            while (l <= r) {
                int m = l + (r - l) / 2;
                if (nums[m] == target) {
                    boundary = m;
                    if (findLeft) {
                        r = m - 1; // continue searching left
                    } else {
                        l = m + 1; // continue searching right
                    }
                } else if (nums[m] < target) {
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }

            return boundary;
        }
    }

    // O(log n) time, O(1) space.
    // Alternative: find left boundary, then search right from there.
    static class Solution2 {
        public static int[] searchRange(int[] nums, int target) {
            int[] result = {-1, -1};
            if (nums == null || nums.length == 0) return result;

            // Find left boundary
            int l = 0, r = nums.length - 1;
            while (l < r) {
                int m = l + (r - l) / 2;
                if (nums[m] < target) {
                    l = m + 1;
                } else {
                    r = m;
                }
            }

            // Check if target exists
            if (nums[l] != target) return result;
            result[0] = l;

            // Find right boundary
            r = nums.length - 1;
            while (l < r) {
                int m = l + (r - l) / 2 + 1; // bias towards right
                if (nums[m] > target) {
                    r = m - 1;
                } else {
                    l = m;
                }
            }
            result[1] = r;

            return result;
        }
    }
}
