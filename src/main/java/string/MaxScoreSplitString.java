package string;

/**
 * <a href="https://leetcode.com/problems/maximum-score-after-splitting-a-string/">LeetCode 1422</a>, easy,
 * tags: string, prefix sum.
 */
public final class MaxScoreSplitString {
    private MaxScoreSplitString() {}

    /**
     * One pass. Time O(n), Space O(1).
     */
    public static int maxScore(String s) {
        int res = 0, zero = 0, ones = 0;
        for (char c : s.toCharArray()) if (c == '1') ones++;
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == '0') zero++;
            else ones--;
            res = Math.max(res, zero + ones);
        }
        return res;
    }
}
