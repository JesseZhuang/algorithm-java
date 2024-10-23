package dp;

import java.util.ArrayList;
import java.util.List;

/**
 * LintCode 745, medium, tags: string, dp, companies: Google.
 * <p>
 * A positive integer is a palindrome if its decimal representation (without leading zeros) is a palindromic string
 * (a string that reads the same forwards and backwards). For example, the numbers 5, 77, 363, 4884, 11111, 12121
 * and 349943 are palindromes.
 * <p>
 * A range of integers is interesting if it contains an even number of palindromes. The range [L, R], with L <= R,
 * is defined as the sequence of integers from L to R (inclusive): (L, L+1, L+2, …, R-1, R). L and R are the range’s first and last numbers.
 * <p>
 * The range [L1, R1] is a subrange of [L, R] if L <= L1 <= R1 <= R. Your job is to determine how many interesting
 * sub ranges of [L, R] there are.
 * <p>
 * Data guarantee results in the range of int, and will not overflow
 * Example
 * Example 1:
 * <p>
 * Input : L = 1, R = 2
 * Output : 1
 * Example 2:
 * <p>
 * Input : L = 1, R = 7
 * Output : 12
 * Explanation: 2 palindromes: [1,2], [2,3], [3,4], [4,5], [5,6], [6,7]; 4: [1,4], [2,5], [3,6], [4,7]
 * 6: [1,6], [2,7]. total is 12.
 * Example 3:
 * <p>
 * Input : L = 87, R = 88
 * Output : 1
 */
@SuppressWarnings("unused")
public class PalindromicRanges {
    // define k as the number of palindromes in [L,R], k^2, k.
    static class Solution {
        public int palindromicRanges(int L, int R) {
            if (L > R) return 0;
            if (L == R) return 1;
            // [10, 22] -> list: {1,0,10,0} [10,11,12-21,22] 0 to mark
            List<Integer> list = new ArrayList<>();
            int start = L, i = L;
            for (; i <= R; i++) {
                if (!isPalin(i)) continue;
                list.add(i - start);
                if (i != start) list.add(0);
                start = i + 1;
            }
            if (i > start) list.add(i - start);

            int res = 0;
            for (int j = 0; j < list.size(); j++) {
                if (list.get(j) != 0) res += list.get(j) * (list.get(j) + 1) / 2;
                int zeros = list.get(j) == 0 ? 1 : 0;
                for (int k = j + 1; k < list.size(); k++) {
                    zeros += list.get(k) == 0 ? 1 : 0;
                    if (zeros % 2 != 0) continue;
                    int a = list.get(j) == 0 ? 1 : list.get(j);
                    int b = list.get(k) == 0 ? 1 : list.get(k);
                    res += a * b;
                }
            }
            return res;
        }

        private boolean isPalin(int a) {
            String s = String.valueOf(a);
            int i = 0;
            int j = s.length() - 1;
            while (i < j) {
                if (s.charAt(i) != s.charAt(j)) return false;
                i++;
                j--;
            }
            return true;
        }
    }

    // n^2, n.
    static class Solution2 {
        /**
         * @param L: A positive integer
         * @param R: A positive integer
         * @return the number of interesting sub ranges of [L,R]
         */
        public int palindromicRanges(int L, int R) {
            if (R < L) return 0;
            if (R == L) return 1; // zero palindrome is also a solution

            // 1. use an array to store all palindromes between L and R;
            // use R-L+2, to include 0 and len+1 (i.e. prefix sum for L and R)
            int[] dp = new int[R - L + 2]; // accumulation of palindrome counts
            for (int i = L; i <= R; ++i) {
                dp[i - L + 1] = dp[i - L]; // accumulate previous value
                if (isPal(i)) ++dp[i - L + 1];
            }
            // 2. use O(n^2) to check every case
            int count = 0;
            int len = R - L + 2;
            for (int i = 1; i < len; ++i)
                for (int j = i - 1; j >= 0; j--)
                    if ((dp[i] - dp[j]) % 2 == 0) ++count;
            return count;
        }

        boolean isPal(int v) {
            int rev = 0;
            int temp = v;
            while (temp != 0) {
                rev = rev * 10;
                rev += temp % 10;
                temp /= 10;
            }
            return v == rev;
        }
    }
}
