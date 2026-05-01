package string;

/**
 * <a href="https://leetcode.com/problems/construct-k-palindrome-strings/">LeetCode 1400</a>, medium,
 * tags: hash table, string, greedy, counting.
 */
public final class ConstructKPalindromeStrings {
    private ConstructKPalindromeStrings() {}

    /**
     * Count odd-frequency characters. Time O(n), Space O(1).
     */
    public static boolean canConstruct(String s, int k) {
        if (s.length() < k) return false;
        if (s.length() == k) return true;
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) cnt[c - 'a']++;
        int odd = 0;
        for (int c : cnt) if ((c & 1) == 1) odd++;
        return odd <= k;
    }
}
