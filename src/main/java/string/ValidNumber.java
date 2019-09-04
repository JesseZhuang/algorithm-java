package string;

/**
 * LeetCode 65. Hard. Tags: Math, String.
 * <p>
 * Validate if a given string can be interpreted as a decimal number.
 * <p>
 * Some examples:
 * <pre>
 * "0" => true
 * " 0.1 " => true
 * "abc" => false
 * "1 a" => false
 * "2e10" => true
 * " -90e3   " => true
 * " 1e" => false
 * "e3" => false
 * " 6e-1" => true
 * " 99e2.5 " => false
 * "53.5e93" => true
 * " --6 " => false
 * "-+3" => false
 * "95a54e53" => false
 * </pre>
 * <p>
 * Note: It is intended for the problem statement to be ambiguous. You should gather all requirements up front
 * before implementing one. However, here is a list of characters that can be in a valid decimal number:
 * <p>
 * <ul>
 * <li>Numbers 0-9
 * <li>Exponent - "e"
 * <li>Positive/negative sign - "+"/"-"
 * <li>Decimal point - "."
 * Of course, the context of these characters also matters in the input.
 * <p>
 * Update (2015-02-10):
 * The signature of the C++ function had been updated. If you still see your function signature accepts
 * a const char * argument, please click the reload button to reset your code definition.
 * <b>Summary:</b>
 * <ul>
 * <li>regex, O(n) time, O(1) space. 19ms 10.18%, 36.7 MB, 100%.
 * <li>if else. O(n) time, O(1) space. 1ms 100%, 35.9 MB, 100%.
 * </ul>
 */
public class ValidNumber {
    public boolean isNumber1(String s) {
        s = s.trim();
        boolean pointSeen = false;
        boolean eSeen = false;
        boolean numberSeen = false;
        for (int i = 0; i < s.length(); i++) {
            if ('0' <= s.charAt(i) && s.charAt(i) <= '9') {
                numberSeen = true;
            } else if (s.charAt(i) == '.') {
                if (eSeen || pointSeen) return false;
                pointSeen = true;
            } else if (s.charAt(i) == 'e') {
                if (eSeen || !numberSeen) return false;
                numberSeen = false;// no number seen after e yet
                eSeen = true;
            } else if (s.charAt(i) == '-' || s.charAt(i) == '+') {
                if (i != 0 && s.charAt(i - 1) != 'e') return false;
            } else return false;
        }
        return numberSeen;
    }

    public boolean isNumberRegex(String s) {
        return s.trim().matches("[-+]?(\\d+\\.?|\\.\\d+)\\d*(e[-+]?\\d+)?");
    }
}
