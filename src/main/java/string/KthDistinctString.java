package string;

import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://leetcode.com/problems/kth-distinct-string-in-an-array/">LeetCode 2053</a>, easy,
 * tags: array, hash table, string.
 */
public final class KthDistinctString {
    private KthDistinctString() {}

    /**
     * Counts each string, then scans the array in its original order to find the kth string with count one.
     * Time O(n), where n is arr.length. Space O(n) for the frequency map.
     */
    public static String kthDistinct(String[] arr, int k) {
        Map<String, Integer> counts = new HashMap<>();
        for (String value : arr) { // O(n)
            counts.put(value, counts.getOrDefault(value, 0) + 1);
        }

        for (String value : arr) { // O(n)
            if (counts.get(value) == 1 && --k == 0) {
                return value;
            }
        }
        return "";
    }
}
