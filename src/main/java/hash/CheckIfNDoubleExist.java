package hash;

import java.util.HashSet;
import java.util.Set;

/**
 * <a href="https://leetcode.com/problems/check-if-n-and-its-double-exist/">LeetCode 1346</a>, easy,
 * tags: array, hash table, two pointers, binary search, sorting.
 */
public final class CheckIfNDoubleExist {
    private CheckIfNDoubleExist() {}

    /**
     * HashSet approach. Time O(n), Space O(n).
     */
    public static boolean checkIfExist(int[] arr) {
        Set<Integer> seen = new HashSet<>();
        for (int n : arr) {
            if (seen.contains(2 * n) || (n % 2 == 0 && seen.contains(n / 2))) return true;
            seen.add(n);
        }
        return false;
    }
}
