package string;

import java.util.HashSet;
import java.util.Set;

/**
 * LeetCode 1593, medium, tags: hash table, string, backtracking.
 * <p>
 * Given a string s, return the maximum number of unique substrings that the given string can be split into.
 * <p>
 * You can split string s into any list of non-empty substrings, where the concatenation of the substrings forms the
 * original string. However, you must split the substrings such that all of them are unique.
 * <p>
 * A substring is a contiguous sequence of characters within a string.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "ababccc"
 * Output: 5
 * Explanation: One way to split maximally is ['a', 'b', 'ab', 'c', 'cc']. Splitting like
 * ['a', 'b', 'a', 'b', 'c', 'cc'] is not valid as you have 'a' and 'b' multiple times.
 * Example 2:
 * <p>
 * Input: s = "aba"
 * Output: 2
 * Explanation: One way to split maximally is ['a', 'ba'].
 * Example 3:
 * <p>
 * Input: s = "aa"
 * Output: 1
 * Explanation: It is impossible to split the string any further.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 16
 * <p>
 * s contains only lower case English letters.
 * <p>
 * Hint 1
 * Use a set to keep track of which substrings have been used already
 * Hint 2
 * Try each possible substring at every position and backtrack if a complete split is not possible
 */
@SuppressWarnings("unused")
public class SplitStringUnique {
    // backtrack(pruning), n*2^n, n.
    static class Solution {
        Set<String> seen;
        int maxCnt;
        String s;

        public int maxUniqueSplit(String s) {
            seen = new HashSet<>();
            maxCnt = 0;
            this.s = s;
            backtrack(0, 0);
            return maxCnt;
        }

        private void backtrack(int start, int count) {
            if (count + (s.length() - start) <= maxCnt) return; // pruning
            if (start == s.length()) {
                maxCnt = Math.max(maxCnt, count);
                return;
            }
            for (int end = start + 1; end <= s.length(); ++end) {
                String substring = s.substring(start, end);
                if (!seen.add(substring)) continue;
                // If the substring is unique
                backtrack(end, count + 1);
                seen.remove(substring); // backtrack
            }
        }
    }
}
