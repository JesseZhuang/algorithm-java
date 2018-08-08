package math;

/**
 * LeetCode 43. Medium.
 * <p>
 * Tags: Math, String.
 * <p>
 * Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2,
 * also represented as a string.
 * <p>
 * Example 1:
 * <p>
 * Input: num1 = "2", num2 = "3"
 * Output: "6"
 * Example 2:
 * <p>
 * Input: num1 = "123", num2 = "456"
 * Output: "56088"
 * Note:
 * <p>
 * <ul>
 * <li>The length of both num1 and num2 is < 110.
 * <li>Both num1 and num2 contain only digits 0-9.
 * <li>Both num1 and num2 do not contain any leading zero, except the number 0 itself.
 * <li>You must not use any built-in BigInteger library or convert the inputs to integer directly.
 * </ul>
 * <p>
 * <b>Summary</b>:
 * <p>
 * <ul>
 * <li>iterative, O(m*n) time, O(1) space.
 * </ul>
 */
public class MultiplyStrings {
    public String multiply(String num1, String num2) {
        //`num1[i] * num2[j]` will be placed at indices `[i + j`, `i + j + 1]`
        int m = num1.length(), n = num2.length();
        int[] resultDigits = new int[m + n];
        for (int i = m - 1; i >= 0; i--)
            for (int j = n - 1; j >= 0; j--) {
                int lowIndex = i + j + 1, highIndex = i + j;
                int product = Character.getNumericValue(num1.charAt(i)) * Character.getNumericValue(num2.charAt(j));
                int sum = product + resultDigits[lowIndex];
                resultDigits[highIndex] += sum / 10;
                resultDigits[lowIndex] = sum % 10;
            }

        StringBuilder sb = new StringBuilder();
        for (int digit : resultDigits) sb.append(digit);
        // The ^ anchor will make sure that the 0+ being matched is at the beginning of the input.
        // The (?!$) negative lookahead ensures that not the entire string will be matched.
        return sb.toString().replaceFirst("^0+(?!$)", "");

//        for(int p : resultDigits) if(!(sb.length() == 0 && p == 0)) sb.append(p);
//        return sb.length() == 0 ? "0" : sb.toString();
    }
}
