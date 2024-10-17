package math;

/**
 * LeetCode 670, medium, tags: math, greedy.
 * <p>
 * You are given an integer num. You can swap two digits at most once to get the maximum valued number.
 * <p>
 * Return the maximum valued number you can get.
 * <p>
 * Example 1:
 * <p>
 * Input: num = 2736
 * Output: 7236
 * Explanation: Swap the number 2 and the number 7.
 * Example 2:
 * <p>
 * Input: num = 9973
 * Output: 9973
 * Explanation: No swap.
 * <p>
 * Constraints:
 * <p>
 * 0 <= num <= 10^8, N digits
 */
@SuppressWarnings("unused")
public class MaxSwap {
    public static void main(String[] args) {
        System.out.println('0' - 1); // 47
        System.out.println(Integer.valueOf('0')); // 48, char '0'
        System.out.println('0' > ('0' - 1));
        char maxDigit = '0' - 1;
        System.out.println(maxDigit); // '/'
        System.out.println(Character.toChars(47)); // '/'
    }

    // N, 1.
    static class Solution {
        public int maximumSwap(int num) {
            // max digit hd, index hi. low digit ld, index li
            int hd = 0, hi = 0, ld = 0, li = 0;
            int curHd = -1, curHi = 0;
            int i = 1, res = num;
            while (num > 0) {
                int d = num % 10; // iterate digits from right to left
                if (d > curHd) {
                    curHd = d;
                    curHi = i;
                } else if (d < curHd) {
                    ld = d;
                    li = i;
                    hd = curHd;
                    hi = curHi;
                }
                i *= 10;
                num /= 10;
            }
            // res += hd*(li-hi) + ld*(hi-li);
            res += li * (hd - ld) + hi * (ld - hd);
            return res;
        }
    }

    static class Solution2 {
        // 0ms, 39.3 Mb, one pass, N time and space.
        public int maximumSwap(int num) {
            char[] digits = Integer.toString(num).toCharArray();
            int maxId = digits.length - 1, l = -1, r = -1;
            for (int i = digits.length - 2; i >= 0; i--) {
                if (digits[i] > digits[maxId]) maxId = i;
                else if (digits[i] < digits[maxId]) {
                    l = i; // leftmost digit less than max digit
                    r = maxId;
                }
            }
            if (l == -1) return num;
            char tmp = digits[r];
            digits[r] = digits[l];
            digits[l] = tmp;
            return Integer.parseInt(new String(digits));
        }
    }
}
