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
public class MaxSwap {
    // 0ms, 39.3 Mb, one pass
    public int maximumSwap(int num) {
        char[] digits = Integer.toString(num).toCharArray();
        int maxIndex = digits.length - 1, leftIndex = -1, rightIndex = -1;
        for (int i = digits.length - 2; i >= 0; i--) {
            if (digits[i] > digits[maxIndex]) maxIndex = i;
            else if (digits[i] < digits[maxIndex]) { // best candidate for max swap
                leftIndex = i;
                rightIndex = maxIndex;
            }
        }
        if (leftIndex == -1) return num;
        char tmp = digits[rightIndex];
        digits[rightIndex] = digits[leftIndex];
        digits[leftIndex] = tmp;
        return Integer.valueOf(new String(digits));
    }

    // 0ms, 39.3Mb, bucket to remember index (rightmost), O(N) time, O(1) space.
    public int maximumSwapBucket(int num) {
        char[] digits = Integer.toString(num).toCharArray();
        int[] buckets = new int[10]; // digit -> index
        for (int i = 0; i < digits.length; i++) buckets[digits[i] - '0'] = i;
        int max = 9;
        for (int i = 0; i < digits.length; i++) {
            for (int k = max; k > digits[i] - '0'; k--) {
                if (buckets[k] > i) {
                    char tmp = digits[i];
                    digits[i] = digits[buckets[k]];
                    digits[buckets[k]] = tmp;
                    return Integer.valueOf(new String(digits));
                }
            }
            max = digits[i] - '0';
        }
        return num;
    }

    public static void main(String[] args) {
        System.out.println('0' - 1); // 47
        System.out.println(Integer.valueOf('0')); // 48, char '0'
        System.out.println('0' > ('0' - 1));
        char maxDigit = '0' - 1;
        System.out.println(maxDigit); // '/'
        System.out.println(Character.toChars(47)); // '/'
    }
}
