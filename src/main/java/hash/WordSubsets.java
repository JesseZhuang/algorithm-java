package hash;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://leetcode.com/problems/word-subsets/">LeetCode 916</a>, medium,
 * tags: array, hash table, string.
 */
public final class WordSubsets {
    private WordSubsets() {}

    /**
     * Union all counters for words2, then filter words1.
     * Time O(n*l1 + m*l2), Space O(l1 + l2).
     */
    public static List<String> wordSubsets(String[] words1, String[] words2) {
        int[] maxCnt = new int[26];
        for (String b : words2) {
            int[] cnt = count(b);
            for (int i = 0; i < 26; i++) maxCnt[i] = Math.max(maxCnt[i], cnt[i]);
        }
        List<String> res = new ArrayList<>();
        for (String a : words1) {
            int[] cnt = count(a);
            boolean universal = true;
            for (int i = 0; i < 26; i++) {
                if (cnt[i] < maxCnt[i]) {
                    universal = false;
                    break;
                }
            }
            if (universal) res.add(a);
        }
        return res;
    }

    private static int[] count(String s) {
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) cnt[c - 'a']++;
        return cnt;
    }
}
