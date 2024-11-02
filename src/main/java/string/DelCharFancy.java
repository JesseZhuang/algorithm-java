package string;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * LeetCode 1957, easy, tags:string.
 * <p>
 * A fancy string is a string where no three consecutive characters are equal.
 * <p>
 * Given a string s, delete the minimum possible number of characters from s to make it fancy.
 * <p>
 * Return the final string after the deletion. It can be shown that the answer will always be unique.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "leeetcode"
 * Output: "leetcode"
 * Explanation:
 * Remove an 'e' from the first group of 'e's to create "leetcode".
 * No three consecutive characters are equal, so return "leetcode".
 * Example 2:
 * <p>
 * Input: s = "aaabaaaa"
 * Output: "aabaa"
 * Explanation:
 * Remove an 'a' from the first group of 'a's to create "aabaaaa".
 * Remove two 'a's from the second group of 'a's to create "aabaa".
 * No three consecutive characters are equal, so return "aabaa".
 * Example 3:
 * <p>
 * Input: s = "aab"
 * Output: "aab"
 * Explanation: No three consecutive characters are equal, so return "aab".
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 105
 * s consists only of lowercase English letters.
 * <p>
 * Hint 1
 * What's the optimal way to delete characters if three or more consecutive characters are equal?
 * Hint 2
 * If three or more consecutive characters are equal, keep two of them and delete the rest.
 */
@SuppressWarnings("unused")
public class DelCharFancy {
    static class Solution {
        // n, n/1 (result space). @Vlad. in place two pointer method.
        public String makeFancyString(String s) {
            return IntStream.range(0, s.length())
                    .filter(i -> i < 2 || (s.charAt(i) != s.charAt(i - 1) || s.charAt(i) != s.charAt(i - 2)))
                    .mapToObj(i -> String.valueOf(s.charAt(i)))
                    .collect(Collectors.joining());
        }
    }
}

