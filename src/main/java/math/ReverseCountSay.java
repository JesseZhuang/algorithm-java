package math;

/**
 * Companies: pinterest.
 * <p>
 * Constraints:
 * 1. The count should be less than 10, e.g., 12_11 could be one 2 one 1 or 121 ones.
 */
public class ReverseCountSay {
    public static void main(String[] args) {
        Solution tbt = new Solution();
        String current = "111221"; // The current term (e.g., 5th in Count and Say)
        String previous = tbt.reverseCountAndSay(current); // Reverse it to get the 4th term
        System.out.println("Previous term: " + previous);
    }

    // O(n) time (geometric series, a/(1-r)). O(n/1.3) result space or O(1).
    static class Solution {
        public String reverseCountAndSay(String s) {
            StringBuilder res = new StringBuilder();
            int n = s.length();
            for (int i = 0; i < n; i += 2) {
                int cnt = s.charAt(i) - '0';
                char digit = s.charAt(i + 1);
                res.append(String.valueOf(digit).repeat(cnt));
            }
            return res.toString();
        }
    }

}
