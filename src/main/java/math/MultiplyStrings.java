package math;

/**
 * LeetCode 43, medium, tags: math, string, simulation.
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
 * Constraints:
 * <p>
 * 1 <= num1.length, num2.length <= 200
 * num1 and num2 consist of digits only.
 * Both num1 and num2 do not contain any leading zero, except the number 0 itself.
 */
public class MultiplyStrings {

    // iterative, O(mn) time, O(1) space considering m+n space needed for result.
    public String multiply(String num1, String num2) {
        //`num1[i] * num2[j]` will be placed at indices `[i + j`, `i + j + 1]`
        int m = num1.length(), n = num2.length();
        int[] res = new int[m + n];
        for (int i = m - 1; i >= 0; i--) // important, start from the right
            for (int j = n - 1; j >= 0; j--) {
                int lowI = i + j + 1, highI = i + j;
                int product = Character.getNumericValue(num1.charAt(i)) * Character.getNumericValue(num2.charAt(j));
                int sum = product + res[lowI];
                res[highI] += sum / 10; // += not =
                res[lowI] = sum % 10;
            }
        StringBuilder sb = new StringBuilder();
//        for (int digit : resultDigits) sb.append(digit);
        // The ^ anchor will make sure that the 0+ being matched is at the beginning of the input.
        // The (?!$) negative lookahead ensures that not the entire string will be matched.
//        return sb.toString().replaceFirst("^0+(?!$)", "");

        for (int p : res) if (!(sb.length() == 0 && p == 0)) sb.append(p); // skip leading 0s
        return sb.length() == 0 ? "0" : sb.toString();
    }
}
