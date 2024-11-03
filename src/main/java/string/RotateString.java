package string;

/**
 * LeetCode 796, easy, tags: string, string matching.
 * Given two strings s and goal, return true if and only if s can become goal after some number of shifts on s.
 * <p>
 * A shift on s consists of moving the leftmost character of s to the rightmost position.
 * <p>
 * For example, if s = "abcde", then it will be "bcdea" after one shift.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "abcde", goal = "cdeab"
 * Output: true
 * Example 2:
 * <p>
 * Input: s = "abcde", goal = "abced"
 * Output: false
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length, goal.length <= 100, n,m
 * s and goal consist of lowercase English letters.
 */
@SuppressWarnings("unused")
public class RotateString {
    // brute force O(n^2) try all rotations, cpp O(1) space. Java Python O(n) space.
    // O(n) typically for contains(), O(n) space.
    static class Solution {
        public boolean rotateString(String s, String goal) {
            if (s.length() != goal.length()) return false;
            String doubledString = s + s;
            return doubledString.contains(goal);
        }
    }

    // KMP, m+n, m+n
    static class Solution2 {
        public boolean rotateString(String s, String goal) {
            if (s.length() != goal.length()) return false;
            String doubledString = s + s;
            return new KMP1D(goal).inHaystack(doubledString);
        }
    }
}
