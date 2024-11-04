package string;

/**
 * LeetCode 3163, medium, tags: string.
 * <p>
 * Given a string word, compress it using the following algorithm:
 * <p>
 * Begin with an empty string comp. While word is not empty, use the following operation:
 * Remove a maximum length prefix of word made of a single character c repeating at most 9 times.
 * Append the length of the prefix followed by c to comp.
 * Return the string comp.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: word = "abcde"
 * <p>
 * Output: "1a1b1c1d1e"
 * <p>
 * Explanation:
 * <p>
 * Initially, comp = "". Apply the operation 5 times, choosing "a", "b", "c", "d", and "e" as the prefix in each operation.
 * <p>
 * For each prefix, append "1" followed by the character to comp.
 * <p>
 * Example 2:
 * <p>
 * Input: word = "aaaaaaaaaaaaaabb"
 * <p>
 * Output: "9a5a2b"
 * <p>
 * Explanation:
 * <p>
 * Initially, comp = "". Apply the operation 3 times, choosing "aaaaaaaaa", "aaaaa", and "bb" as the prefix in each operation.
 * <p>
 * For prefix "aaaaaaaaa", append "9" followed by "a" to comp.
 * For prefix "aaaaa", append "5" followed by "a" to comp.
 * For prefix "bb", append "2" followed by "b" to comp.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= word.length <= 2 * 10^5
 * word consists only of lowercase English letters.
 * <p>
 * Hint 1
 * Each time, just cut the same character in prefix up to at max 9 times. It’s always better to cut a bigger prefix.
 */
@SuppressWarnings("unused")
public class StringCompressionIII {
    // todo
    static class Solution {

        public String compressedString(String word) {
            StringBuilder comp = new StringBuilder("");

            // pos tracks our position in the input string
            int pos = 0;

            // Process until we reach end of string
            while (pos < word.length()) {
                int consecutiveCount = 0;

                char currentChar = word.charAt(pos);

                // Count consecutive occurrences (maximum 9)
                while (
                        pos < word.length() &&
                                consecutiveCount < 9 &&
                                word.charAt(pos) == currentChar
                ) {
                    consecutiveCount++;
                    pos++;
                }

                // Append count followed by character to result
                comp.append(consecutiveCount).append(currentChar);
            }

            return comp.toString();
        }
    }
}
