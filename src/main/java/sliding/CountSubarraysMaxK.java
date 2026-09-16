package sliding;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 2962, medium, tags: array, sliding window.
 * <p>
 * You are given an integer array nums and a positive integer k.
 * <p>
 * Return the number of subarrays where the maximum element of nums appears at least k times in
 * that subarray.
 * <p>
 * A subarray is a contiguous sequence of elements within an array.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,3,2,3,3], k = 2
 * Output: 6
 * Explanation: The subarrays that contain the element 3 at least 2 times are:
 * [1,3,2,3], [1,3,2,3,3], [3,2,3], [3,2,3,3], [2,3,3] and [3,3].
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [1,4,2,1], k = 3
 * Output: 0
 * Explanation: No subarray contains the element 4 at least 3 times.
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^6
 * 1 <= k <= 10^5
 */
@SuppressWarnings("unused")
public final class CountSubarraysMaxK {

    private CountSubarraysMaxK() {
    }

    // solution 1, sliding window. O(n) time, O(1) space.
    public static long countSubarrays(int[] nums, int k) {
        int max = 0;
        for (int num : nums) max = Math.max(max, num); // O(n) find global max
        long res = 0;
        int left = 0, count = 0; // count: occurrences of max in current window
        for (int right = 0; right < nums.length; right++) { // O(n) expand right
            if (nums[right] == max) count++;
            while (count >= k) { // shrink left until fewer than k max elements
                if (nums[left] == max) count--;
                left++;
            }
            res += left; // all starts in [0, left) form valid subarrays ending at right
        }
        return res;
    }

    // solution 2, binary search on positions of max element. O(n log n) time, O(n) space.
    public static long countSubarrays2(int[] nums, int k) {
        int max = 0;
        for (int num : nums) max = Math.max(max, num); // O(n) find global max
        List<Integer> positions = new ArrayList<>(); // O(n) space: indices where max appears
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == max) positions.add(i);
        }
        long res = 0;
        for (int right = 0; right < nums.length; right++) { // O(n) iterate each end
            // binary search for the (positions.size() - k + 1)-th max position <= right
            // i.e., find how many starting positions give >= k max elements in [start, right]
            // the k-th max from the right within [0, right] is at positions[idx] where
            // idx = (index of right in positions) - k + 1; earliest valid start is positions[idx] + 1
            // but we need the count of max elements in positions that are <= right
            int cnt = upperBound(positions, right); // O(log n) binary search
            if (cnt >= k) {
                // the k-th occurrence from the end (within [0, right]) is at positions[cnt - k]
                res += positions.get(cnt - k) + 1; // all starts in [0, positions[cnt-k]] are valid
            }
        }
        return res;
    }

    // returns count of elements in sorted list that are <= target. O(log n).
    private static int upperBound(List<Integer> sorted, int target) {
        int lo = 0, hi = sorted.size();
        while (lo < hi) { // O(log n) binary search
            int mid = lo + (hi - lo) / 2;
            if (sorted.get(mid) <= target) lo = mid + 1;
            else hi = mid;
        }
        return lo;
    }
}
