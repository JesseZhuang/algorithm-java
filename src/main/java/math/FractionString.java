package math;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 166, medium, tags: hash table, string, math.
 * <p>
 * Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.
 * <p>
 * If the fractional part is repeating, enclose the repeating part in parentheses.
 * <p>
 * If multiple answers are possible, return any of them.
 * <p>
 * It is guaranteed that the length of the answer string is less than 10^4 for all the given inputs.
 * <p>
 * Example 1:
 * <p>
 * Input: numerator = 1, denominator = 2
 * Output: "0.5"
 * Example 2:
 * <p>
 * Input: numerator = 2, denominator = 1
 * Output: "2"
 * Example 3:
 * <p>
 * Input: numerator = 4, denominator = 333
 * Output: "0.(012)"
 * <p>
 * Constraints:
 * <p>
 * -2^31 <= numerator, denominator <= 2^31 - 1
 * denominator != 0
 * <p>
 * Hints:
 * No scary math, just apply elementary math knowledge. Still remember how to perform a long division?
 * Try a long division on 4/9, the repeating part is obvious. Now try 4/333. Do you see a pattern?
 * Notice that once the remainder starts repeating, so does the divided result.
 * Be wary of edge cases! List out as many test cases as you can think of and test your code thoroughly.
 * <p>
 * https://math.stackexchange.com/questions/1290402/is-there-any-explanation-for-the-repetitions-after-decimal-point-on-divisions-li
 */
public class FractionString {

    // 1ms, 39.7 Mb. O(m) time and O(m) space. m: repeating digits in result
    public static String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) return "0";
        StringBuilder res = new StringBuilder();
        if ((numerator > 0) ^ (denominator > 0)) res.append("-"); // do not forget
        long num = Math.abs((long) numerator); // cast before abs, important for int_min
        long den = Math.abs((long) denominator);
        res.append(num / den);
        num %= den;
        if (num == 0) return res.toString();
        // fractional part
        res.append(".");
        Map<Long, Integer> numIndex = new HashMap<>();
        while (num != 0) {
            numIndex.put(num, res.length());
            num *= 10;
            res.append(num / den); // quotient
            num %= den; // remainder
            if (numIndex.containsKey(num)) {
                int index = numIndex.get(num);
                res.insert(index, "(");
                res.append(")");
                break;
            }
        }
        return res.toString();
    }

    public static void main(String[] args) {
        // "0.000000000-4-6-5-6-6-1-2-8-7-30-7-7-3-9-2-5-7-8-1-2-5" instead of 0.0000000004656612873077392578125 if
        // not convert to long before casting, abs(int_min) is still int_min
        System.out.println(fractionToDecimal(-1, -2147483648));
        System.out.println(Integer.MIN_VALUE / -1); //-2147483648
        System.out.println(fractionToDecimal(Integer.MIN_VALUE, -1)); // 2147483648, over flow int
    }
}
