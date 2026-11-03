package string;

import java.util.Arrays;

/**
 * <a href="https://leetcode.com/problems/largest-substring-between-two-equal-characters/">LeetCode 1624</a>,
 * easy, tags: hash table, string.
 */
public final class LargestSubstringBetweenTwoEqualCharacters {
    private LargestSubstringBetweenTwoEqualCharacters() {}

    /**
     * Records each letter's first index, so every later occurrence yields the largest possible substring ending
     * at that index. Time O(n), where n is s.length(). Space O(1) for the 26 lowercase English letters.
     */
    public static int maxLengthBetweenEqualCharacters(String s) {
        int[] firstIndex = new int[26];
        Arrays.fill(firstIndex, -1);
        int maxLength = -1;

        for (int index = 0; index < s.length(); index++) { // O(n)
            int letter = s.charAt(index) - 'a';
            if (firstIndex[letter] == -1) {
                firstIndex[letter] = index;
            } else {
                maxLength = Math.max(maxLength, index - firstIndex[letter] - 1);
            }
        }
        return maxLength;
    }
}
