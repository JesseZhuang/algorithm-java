package string;

/**
 * LeetCode 1055, medium, tags: greedy, two pointers, string, binary search.
 * <p>
 * A subsequence of a string is a new string that is formed from the original string by deleting some (can be none)
 * of the characters without disturbing the relative positions of the remaining characters. (i.e., "ace" is a
 * subsequence of "abcde" while "aec" is not).
 * <p>
 * Given two strings source and target, return the minimum number of subsequences of source such that their
 * concatenation equals target. If the task is impossible, return -1.
 * <p>
 * Example 1:
 * <p>
 * Input: source = "abc", target = "abcbc"
 * Output: 2
 * Explanation: The target "abcbc" can be formed by "abc" and "bc", which are subsequences of source "abc".
 * Example 2:
 * <p>
 * Input: source = "abc", target = "acdbc"
 * Output: -1
 * Explanation: The target string cannot be constructed from the subsequences of source string due to the
 * character "d" in target string.
 * Example 3:
 * <p>
 * Input: source = "xyz", target = "xzyxz"
 * Output: 3
 * Explanation: The target string can be constructed as follows "xz" + "y" + "xz".
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= source.length, target.length <= 1000
 * source and target consist of lowercase English letters.
 */
@SuppressWarnings("unused")
public class ShortestWayString {
    // two pointers, mn time, 1 space.
    static class Solution {
        public int shortestWay(String source, String target) {
            int m = source.length(), n = target.length();
            int res = 0, j = 0;
            while (j < n) {
                boolean found = false;
                int i = 0;
                while (i < m && j < n) {
                    if (source.charAt(i) == target.charAt(j)) {
                        found = true;
                        j++;
                    }
                    i++;
                }
                if (!found) return -1; // can not form target
                res++;
            }
            return res;
        }
    }
}
