package hash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * LeetCode 350, easy, tags: hash table, array, two pointers, binary search, sorting.
 * <p>
 * Given two integer arrays nums1 and nums2, return an array of their intersection. Each element in the result must
 * appear as many times as it shows in both arrays and you may return the result in any order.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums1 = [1,2,2,1], nums2 = [2,2]
 * Output: [2,2]
 * Example 2:
 * <p>
 * Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * Output: [4,9]
 * Explanation: [9,4] is also accepted.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums1.length, nums2.length <= 1000, m, n
 * 0 <= nums1[i], nums2[i] <= 1000
 * <p>
 * <p>
 * Follow up:
 * <p>
 * What if the given array is already sorted? How would you optimize your algorithm?
 * <p>
 * solution 2, two pointer, m+n time, 1 space.
 * <p>
 * What if nums1's size is small compared to nums2's size? Which algorithm is better?
 * <p>
 * solution 1, swap two arrays. less space. min (m,n).
 * <p>
 * What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into
 * the memory at once?
 * <p>
 * If nums1 fits into memory, use solution 1 and process nums2 chunk by chunk.
 * If neither fits, split numeric range and use solution 1. For example, process [0,100], then [101,200], ...
 */
public class IntersectionTwoArrays {
    // solution 1, 3ms, 43.74MB. map, m+n time, min(m,n) space.
    public int[] intersect1(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) return intersect1(nums2, nums1);
        HashMap<Integer, Integer> count = new HashMap<>();
        for (int n : nums1) count.put(n, count.getOrDefault(n, 0) + 1);
        ArrayList<Integer> list = new ArrayList<>();
        for (int n : nums2)
            if (count.containsKey(n) && count.get(n) > 0) {
                list.add(n);
                count.put(n, count.get(n) - 1);
            }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) res[i] = list.get(i);
        return res;
    }

    // solution 2, 3ms, 42.24MB. sort, two pointer. mlgm+nlgn time, O(1) space.
    public int[] intersect2(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int m = nums1.length, n = nums2.length;
        int i = 0, j = 0;
        List<Integer> list = new ArrayList<>();
        while (i < m && j < n) {
            int a = nums1[i], b = nums2[j];
            if (a == b) {
                list.add(a);
                i++;
                j++;
            } else if (a < b) {
                i++;
            } else {
                j++;
            }
        }
        int[] ret = new int[list.size()];
        for (int k = 0; k < list.size(); k++) ret[k] = list.get(k);
        return ret;
    }
}
