package dp;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 3333, hard, tags: dp, array.
 * <p>
 * Alice is attempting to type a specific string on her computer. However, she tends to be clumsy and may press a key
 * for too long, resulting in a character being typed multiple times.
 * <p>
 * You are given a string word, which represents the final output displayed on Alice's screen. You are also given
 * a positive integer k.
 * <p>
 * Return the total number of possible original strings that Alice might have intended to type, if she was trying
 * to type a string of size at least k.
 * <p>
 * Since the answer may be very large, return it modulo 10^9 + 7.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: word = "aabbccdd", k = 7
 * <p>
 * Output: 5
 * <p>
 * Explanation:
 * <p>
 * The possible strings are: "aabbccdd", "aabbccd", "aabbcdd", "aabccdd", and "abbccdd".
 * <p>
 * Example 2:
 * <p>
 * Input: word = "aabbccdd", k = 8
 * <p>
 * Output: 1
 * <p>
 * Explanation:
 * <p>
 * The only possible string is "aabbccdd".
 * <p>
 * Example 3:
 * <p>
 * Input: word = "aaabbb", k = 3
 * <p>
 * Output: 8
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= word.length <= 5 * 10^5, n
 * word consists only of lowercase English letters.
 * 1 <= k <= 2000
 * <p>
 * Hint 1
 * Instead of solving for at least k, can we solve for at most k - 1 length?
 */
@SuppressWarnings("unused")
public class OrigTypedStringII {
    // dp, O(n+k^2), O(k).
    // dp[i][j] possibilities for characters in runs[0,i] string size of exactly j, where j in [0,k-1]
    // init empty string, 0 char, possibilities: 1
    // e.g., aaabbb, k:3. dp [1,0,0]
    // iterate group of aaa, dp [0,1,1] meaning size 1: a, size 2: aa
    // iterate group of bbb, dp [0,0,1] meaning size 1: not possible, size 2: ab
    // e.g., aabbccdd, k:8, init dp dp [1,0,0,0,0,0,0,0]
    // iterate group of aa, dp [0,1,1,0,0,0,0,0] meaning size 1: a, size 2: aa
    // iterate group of bb, [0, 0, 1, 2, 1, 0, 0, 0] s==5, meaning size 2: ab; size 3: aab, abb; size 4: aabb.
    // iterate group of cc: prefix_sum: ps
    // size 3: +dp[2]:1 ps 0->1, -dp[0]:0 ps 1->1, meaning taking ab, use 1 c: [abc]
    // size 4: +dp[3]:2 ps 1->3, -dp[1]:0 ps 3->3, meaning taking aab,abb, use 1 c: [abcc,aabc,abbc]
    // size 5: +dp[4]:1 ps 3->4, -dp[2]:1 ps 4->3, meaning taking aabb, removing abccc: [aabcc,abbcc,aabbc]
    // size 6: +dp[5]:0 ps 3->3, -dp[3]:2 ps 3->1, meaning removing aabccc,abbccc: [aabbcc]
    // size 7: +dp[6]:0 ps 1->1, -dp[4]:1 ps 1->0, meaning removing aabbccc: []
    static class Solution {
        public int possibleStringCount(String word, int k) {
            final int MOD = (int) (1e9 + 7);
            int n = word.length();
            List<Integer> runs = new ArrayList<>(); // run (duplicate) lengths, O(26)

            long res = 1;
            for (int i = 0; i < n; ) { // O(n)
                int cnt = 1;
                while (i < n - 1 && word.charAt(i) == word.charAt(i + 1)) {
                    cnt++;
                    i++;
                }
                res = res * cnt % MOD;
                runs.add(cnt);
                i++;
            }

            if (k <= runs.size()) return (int) res;

            int mS = k - 1; // dp upto k-1 size
            long[] dp = new long[mS + 1];
            dp[0] = 1;

            for (int cnt : runs) { // O(k*min(n,k))==O(k^2)
                long[] ndp = new long[mS + 1];
                long sum = 0;
                for (int s = 0; s <= mS; s++) {
                    if (s >= 1) sum = (sum + dp[s - 1]) % MOD;
                    if (s - cnt - 1 >= 0) sum = (sum - dp[s - cnt - 1] + MOD) % MOD;
                    ndp[s] = sum;
                }
                dp = ndp;
            }

            long atMostK = 0;
            for (int s = runs.size(); s <= mS; s++) atMostK = (atMostK + dp[s]) % MOD;

            return (int) ((res - atMostK + MOD) % MOD);
        }
    }
}
