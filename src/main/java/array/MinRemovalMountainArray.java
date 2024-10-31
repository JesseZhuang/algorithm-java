package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * LeetCode1671, hard, tags: binary search, array, dp, greedy.
 * <p>
 * You may recall that an array arr is a mountain array if and only if:
 * <p>
 * arr.length >= 3
 * There exists some index i (0-indexed) with 0 < i < arr.length - 1 such that:
 * arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
 * arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
 * Given an integer array nums, return the minimum number of elements to remove to make
 * nums a mountain array.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,3,1]
 * Output: 0
 * Explanation: The array itself is a mountain array so we do not need to remove any elements.
 * Example 2:
 * <p>
 * Input: nums = [2,1,1,5,6,2,3,1]
 * Output: 3
 * Explanation: One solution is to remove the elements at indices 0, 1, and 5, making the array nums = [1,5,6,3,1].
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 3 <= nums.length <= 1000
 * 1 <= nums[i] <= 109
 * It is guaranteed that you can make a mountain array out of nums.
 * <p>
 * Hint 1
 * Think the opposite direction instead of minimum elements to remove the maximum mountain subsequence
 * Hint 2
 * Think of LIS (longest increasing subsequence) it's kind of close
 * <p>
 * See {@link dp.LongestIncreasingSubSequence}
 */
@SuppressWarnings("unused")
public class MinRemovalMountainArray {
    // binary search, nlgn, n.
    static class Solution {
        private List<Integer> getLISLen(List<Integer> v) {
            List<Integer> lisLen = new ArrayList<>(Collections.nCopies(v.size(), 1));
            List<Integer> lis = new ArrayList<>();
            lis.add(v.getFirst());
            for (int i = 1; i < v.size(); i++) {
                int index = lowerBound(lis, v.get(i));
                // Add to the list if v[i] is greater than the last element
                if (index == lis.size()) lis.add(v.get(i));
                else lis.set(index, v.get(i)); // Replace the element at index with v[i]
                lisLen.set(i, lis.size());
            }
            return lisLen;
        }

        // Returns the index of the first element which is equal to or greater than target. bisect left.
        private int lowerBound(List<Integer> lis, int target) {
            int left = 0, right = lis.size() - 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (lis.get(mid) >= target) right = mid - 1;
                else left = mid + 1;
            }
            return left;
        }

        public int minimumMountainRemovals(int[] nums) {
            int N = nums.length;
            List<Integer> numsList = Arrays.stream(nums).boxed().collect(Collectors.toList());
            List<Integer> lisLen = getLISLen(numsList);

            Collections.reverse(numsList);
            List<Integer> ldsLength = getLISLen(numsList);
            // Reverse to correctly depict the length of longest decreasing subsequence for each index.
            Collections.reverse(ldsLength);
            int res = Integer.MAX_VALUE;
            for (int i = 0; i < N; i++) {
                if (lisLen.get(i) > 1 && ldsLength.get(i) > 1)
                    res = Math.min(res, N - lisLen.get(i) - ldsLength.get(i) + 1);
                // N- (lis+lds-1)
            }
            return res;
        }
    }
}
