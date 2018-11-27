package string;

import java.util.Arrays;

/**
 * LeetCode 14. Easy.
 * <p>
 * Write a function to find the longest common prefix string amongst an array of strings.
 * <p>
 * If there is no common prefix, return an empty string "".
 * <p>
 * Example 1:
 * <pre>
 * Input: ["flower","flow","flight"]
 * Output: "fl"
 *
 * Example 2:
 * Input: ["dog","racecar","car"]
 * Output: ""
 * </pre>
 * Explanation: There is no common prefix among the input strings.
 * Note: All given inputs are in lowercase letters a-z.
 * <b>Summary:</b>
 * Average string length n. Longest prefix length m. Number of strings in array k.
 * <ul>
 * <li>index first string in all other strings, O(k)*O(n)*O(m) time, O(1) space.
 * <li>compare characters, O(k)*O(n) time, O(1) space.
 * <li>sort then compare characters, O(k)*O(n) + O(nlg_n) time, O(1) space.
 * </ul>
 */
public class LongestCommonPrefix {
    public String longestCommonPrefix1(String[] strs) {
        if (strs == null || strs.length == 0)
            return "";
        String pre = strs[0];
        for (int i = 1; i < strs.length; i++) // O(k)
            while (strs[i].indexOf(pre) != 0) // O(n) * O(m)
                pre = pre.substring(0, pre.length() - 1); // O(1)
        return pre;
    }

    public String longestCommonPrefix2(String[] strs) {
        String res = "";
        int max = 0;
        if (strs != null && strs.length > 0) {
            res = strs[0];
            max = res.length();
            for (int i = 1; i < strs.length; i++) { // O(k)
                max = Math.min(strs[i].length(), max);
                for (int j = 0; j < max; j++) if (res.charAt(j) != strs[i].charAt(j)) max = j; // O(n)
            }
        }
        return res.substring(0, max);
    }

    public String longestCommonPrefix(String[] strs) {
        String res = "";
        int max = 0;
        if (strs != null && strs.length > 0) {
            Arrays.sort(strs);
            res = strs[0];
            String last = strs[strs.length - 1];
            int N = Math.min(res.length(), last.length());
            for (; max < N; max++) if (res.charAt(max) != last.charAt(max)) break;
        }
        return res.substring(0, max);
    }
}
