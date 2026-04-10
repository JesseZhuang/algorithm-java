package string;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LongestSubstringWithoutRepeatingTest {

    @Test
    void test() {
        // solution 1: sliding window with hash map
        assertEquals(3, LongestSubstringWithoutRepeating.lengthOfLongestSubstring("abcabcbb"));
        assertEquals(1, LongestSubstringWithoutRepeating.lengthOfLongestSubstring("bbbbb"));
        assertEquals(3, LongestSubstringWithoutRepeating.lengthOfLongestSubstring("pwwkew"));
        assertEquals(0, LongestSubstringWithoutRepeating.lengthOfLongestSubstring(""));
        assertEquals(1, LongestSubstringWithoutRepeating.lengthOfLongestSubstring("a"));
        assertEquals(5, LongestSubstringWithoutRepeating.lengthOfLongestSubstring("abcde"));
        assertEquals(3, LongestSubstringWithoutRepeating.lengthOfLongestSubstring("abca"));

        // solution 2: sliding window with hash set
        assertEquals(3, LongestSubstringWithoutRepeating.lengthOfLongestSubstring2("abcabcbb"));
        assertEquals(1, LongestSubstringWithoutRepeating.lengthOfLongestSubstring2("bbbbb"));
        assertEquals(3, LongestSubstringWithoutRepeating.lengthOfLongestSubstring2("pwwkew"));
        assertEquals(0, LongestSubstringWithoutRepeating.lengthOfLongestSubstring2(""));
        assertEquals(1, LongestSubstringWithoutRepeating.lengthOfLongestSubstring2("a"));
        assertEquals(5, LongestSubstringWithoutRepeating.lengthOfLongestSubstring2("abcde"));
        assertEquals(3, LongestSubstringWithoutRepeating.lengthOfLongestSubstring2("abca"));
    }
}
