package dp;

import java.util.Arrays;

/**
 * <a href="https://leetcode.com/problems/stickers-to-spell-word/">LeetCode 691</a>, hard,
 * tags: array, string, dynamic programming, backtracking, bit manipulation.
 */
public final class StickersSpellWord {
    private StickersSpellWord() {}

    /**
     * Bitmask DP approach. dp[i] = min stickers for state i where each bit represents a filled character.
     * Time O(2^t * s * t), Space O(2^t), where t = target.length, s = sum of sticker lengths.
     */
    public static int minStickers(String[] stickers, String target) {
        int t = target.length(), m = 1 << t;
        int[] dp = new int[m];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int j = 0; j < m; j++) {
            if (dp[j] == Integer.MAX_VALUE) continue;
            for (String s : stickers) {
                int cur = j;
                for (char c : s.toCharArray()) {
                    for (int i = 0; i < t; i++) {
                        if (target.charAt(i) == c && ((cur >> i) & 1) == 0) {
                            cur |= 1 << i;
                            break;
                        }
                    }
                }
                dp[cur] = Math.min(dp[cur], dp[j] + 1);
            }
        }
        return dp[m - 1] == Integer.MAX_VALUE ? -1 : dp[m - 1];
    }
}
