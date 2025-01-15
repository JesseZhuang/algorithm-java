package string;

import java.util.Arrays;

/**
 * LeetCode 1930, medium.
 */
@SuppressWarnings("unused")
public class UniqL3PalinSubSeq {
    // n, 1. 281 ms, 45.44 mb.
    static class Solution {
        public int countPalindromicSubsequence(String s) {
            int first[] = new int[26], last[] = new int[26], res = 0;
            Arrays.fill(first, Integer.MAX_VALUE);
            for (int i = 0; i < s.length(); i++) {
                int id = s.charAt(i) - 'a';
                first[id] = Math.min(i, first[id]);
                last[id] = i;
            }
            for (int i = 0; i < 26; i++)
                if (first[i] < last[i])
                    res += (int) s.substring(first[i] + 1, last[i]).chars().distinct().count();
            return res;
        }
    }
}
