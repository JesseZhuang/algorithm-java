package array;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://leetcode.com/problems/find-k-closest-elements/">LeetCode 658</a>, medium,
 * tags: array, binary search, sliding window, sorting, heap.
 */
public final class FindKClosestElements {
    private FindKClosestElements() {}

    /**
     * Binary search for the left boundary.
     * Time O(log(n-k)), Space O(1) excluding result.
     */
    public static List<Integer> findClosestElements(int[] arr, int k, int x) {
        int l = 0, r = arr.length - k;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (x - arr[mid] > arr[mid + k] - x) l = mid + 1;
            else r = mid;
        }
        List<Integer> res = new ArrayList<>(k);
        for (int i = l; i < l + k; i++) res.add(arr[i]);
        return res;
    }
}
