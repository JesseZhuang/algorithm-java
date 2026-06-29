package hash;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 2131 - Longest Palindrome by Concatenating Two Letter Words.
 * <p>
 * Time: O(n), Space: O(n)
 */
public final class LongestPalindromeConcatenating {

    private LongestPalindromeConcatenating() {
    }

    public static int longestPalindrome(String[] words) {
        Map<String, Integer> freq = new HashMap<>();
        for (String w : words) {
            freq.merge(w, 1, Integer::sum);
        }

        int length = 0;
        boolean hasOddCenter = false;

        for (Map.Entry<String, Integer> entry : freq.entrySet()) {
            String word = entry.getKey();
            int count = entry.getValue();

            if (word.charAt(0) == word.charAt(1)) {
                // Palindromic word
                int pairs = count / 2;
                length += pairs * 4;
                if (count % 2 == 1) {
                    hasOddCenter = true;
                }
            } else {
                // Non-palindromic word: pair with reverse
                String rev = "" + word.charAt(1) + word.charAt(0);
                if (word.compareTo(rev) < 0) {
                    int revCount = freq.getOrDefault(rev, 0);
                    int pairs = Math.min(count, revCount);
                    length += pairs * 4;
                }
            }
        }

        if (hasOddCenter) {
            length += 2;
        }

        return length;
    }
}
