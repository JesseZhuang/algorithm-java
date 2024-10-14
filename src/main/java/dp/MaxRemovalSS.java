package dp;

import java.util.Arrays;

/**
 * LeetCode Biweekly 141, medium.
 * <p>
 * Given a string source of size n, a string pattern that is a subsequence of source, and a sorted integer array
 * targetIndices that contains distinct numbers in the range [0,n-1].
 * <p>
 * We define an operation to remove a character at index idx from source:
 * - idx is an element of targetIndices
 * - pattern remains a subsequence of source
 * <p>
 * Performing an operation does not change the indices of other characters in source. For example, remove 'c' from
 * 'acb', the character at index 2 would still be 'b'.
 * <p>
 * Return max number of operations can be performed.
 * <p>
 * A subsequence is a string that can be derived from another by deleting some or no characters without changing order.
 * <p>
 * Constraints:
 * 1 <= n == source.length <= 3*10^3
 * 1 <= pattern.length <= n, m
 * 1 <= targetIndices.length <= n
 * targetIndices is sorted in ascending order
 * the input is generated such that targetIndices contains distinct elements in range [0,n-1]
 * source and pattern consist only of lower case english letters
 * the input is generated such that pattern appears as a subsequence in source
 */
@SuppressWarnings("unused")
public class MaxRemovalSS {
    // nm, m. credit @peace(shankardtu21)
    public static class Solution {
        public int maxRemovals(String source, String pattern, int[] targetIndices) {
            int n = source.length(), m = pattern.length();
            boolean[] v = new boolean[n];
            for (int i : targetIndices) v[i] = true;
            int[] dp = new int[m + 1];
            Arrays.fill(dp, 0x7ffffff / 2); // INT_MAX
            dp[0] = 0;
            // after ith loop, source[0,i] remove target indices value in [0,i], the cost for pattern [0,min(i,m-1)]
            // e.g., abbaa, aba, [0,1,2].
            // after i==1 th loop, dp: [0,1,2,max], removing target indices in [0,i]
            // cost is 1 if pattern is a, cost is 2 if pattern is ab
            for (int i = 0; i < n; i++) {
                for (int j = Math.min(i, m - 1); j >= 0; j--) {
                    if (source.charAt(i) == pattern.charAt(j)) {
                        int cost = dp[j] + (v[i] ? 1 : 0);
                        dp[j + 1] = Math.min(cost, dp[j + 1]);
                    }
                }
            }
            return targetIndices.length - dp[m]; // dp[m] min_used
        }
    }
}
