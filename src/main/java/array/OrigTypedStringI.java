package array;

/**
 * LeetCode 3330, easy, tags: two pointer, array, string. Biweekly 142 Q1.
 * <p>
 * Alice is attempting to type a specific string on her computer. However, she tends to be clumsy and may press a key
 * for too long, resulting in a character being typed multiple times.
 * <p>
 * Although Alice tried to focus on her typing, she is aware that she may still have done this at most once.
 * <p>
 * You are given a string word, which represents the final output displayed on Alice's screen.
 * <p>
 * Return the total number of possible original strings that Alice might have intended to type.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: word = "abbcccc"
 * <p>
 * Output: 5
 * <p>
 * Explanation:
 * <p>
 * The possible strings are: "abbcccc", "abbccc", "abbcc", "abbc", and "abcccc".
 * <p>
 * Example 2:
 * <p>
 * Input: word = "abcd"
 * <p>
 * Output: 1
 * <p>
 * Explanation:
 * <p>
 * The only possible string is "abcd".
 * <p>
 * Example 3:
 * <p>
 * Input: word = "aaaa"
 * <p>
 * Output: 4
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * {@code 1 <= word.length <= 100}
 * word consists only of lowercase English letters.
 * Hint 1
 * Any group of consecutive characters might have been the mistake.
 */
@SuppressWarnings("unused")
public class OrigTypedStringI {
    // two pointers n, 1.
    static class Solution {
        public int possibleStringCount(String word) {
            int res = 1, i = 0;
            while (i < word.length()) {
                int j = i;
                while (j < word.length() && word.charAt(j) == word.charAt(i)) j++;
                int len = j - i;
                res += len - 1;
                i = j;
            }
            return res;
        }
    }
}
