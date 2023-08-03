package array;

import java.util.stream.Stream;

/**
 * LeetCode 2496. Easy. Tags: array, string.
 * The value of an alphanumeric string can be defined as:
 * <p>
 * The numeric representation of the string in base 10, if it comprises of digits only.
 * The length of the string, otherwise.
 * Given an array strs of alphanumeric strings, return the maximum value of any string in strs.
 * <p>
 * Example 1:
 * <p>
 * Input: strs = ["alic3","bob","3","4","00000"]
 * Output: 5
 * Explanation:
 * - "alic3" consists of both letters and digits, so its value is its length, i.e. 5.
 * - "bob" consists only of letters, so its value is also its length, i.e. 3.
 * - "3" consists only of digits, so its value is its numeric equivalent, i.e. 3.
 * - "4" also consists only of digits, so its value is 4.
 * - "00000" consists only of digits, so its value is 0.
 * Hence, the maximum value is 5, of "alic3".
 * Example 2:
 * <p>
 * Input: strs = ["1","01","001","0001"]
 * Output: 1
 * Explanation:
 * Each string in the array has value 1. Hence, we return 1.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= strs.length <= 100, N
 * 1 <= strs[i].length <= 9, M
 * strs[i] consists of only lowercase English letters and digits.
 */
public class MaxValStringInArray {
    // 5ms, 52.5 Mb. O(N*M) time, N strings, average length M. O(1) space.
    public int maximumValue(String[] strs) {
        int max = 0;
        for (String s : strs) {
            int n;
            try {
                n = Integer.parseInt(s); // also considers negative number, more than needed
            } catch (NumberFormatException e) {
                n = s.length();
            }
            max = Math.max(max, n);
        }
        return max;
    }

    // 6ms, 42.5Mb.
    public int maximumValueStream(String[] strs) {
        return Stream.of(strs).mapToInt(t -> t.matches("\\d+") ? Integer.valueOf(t) : t.length())
                .max().getAsInt();
    }
}
