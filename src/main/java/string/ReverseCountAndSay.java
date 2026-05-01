package string;

/**
 * Reverse of <a href="https://leetcode.com/problems/count-and-say/">LeetCode 38</a>,
 * follow-up question. Tags: math, string, simulation.
 * <p>
 * Given a run-length-encoded string {@code s} where each pair of characters is
 * (count, digit), reconstruct the previous term of the Count-and-Say sequence
 * by expanding each pair.
 */
public final class ReverseCountAndSay {
    private ReverseCountAndSay() {}

    /**
     * Expand RLE pairs back to the original string.
     * Time O(n / 1.3) ≈ O(n), Space O(n) for the output buffer.
     */
    public static String reverseCountSay(String s) {
        if (s == null || s.isEmpty()) return "";
        if ((s.length() & 1) != 0) throw new IllegalArgumentException("odd length input");
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < s.length(); i += 2) {
            char countCh = s.charAt(i);
            if (countCh < '0' || countCh > '9') throw new IllegalArgumentException("non-digit count");
            int cnt = countCh - '0';
            char digit = s.charAt(i + 1);
            for (int k = 0; k < cnt; k++) out.append(digit);  // O(cnt) per pair
        }
        return out.toString();
    }
}
