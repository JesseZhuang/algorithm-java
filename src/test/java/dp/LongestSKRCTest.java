package dp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LongestSKRCTest {
    @Test
    void testLongestSubstring1() {
        assertEquals(3, LongestSKRC.longestSubstring1("aaabb", 3));
        assertEquals(5, LongestSKRC.longestSubstring1("ababbc", 2));
        assertEquals(1, LongestSKRC.longestSubstring1("a", 1));
        assertEquals(0, LongestSKRC.longestSubstring1("abc", 4));
        assertEquals(5, LongestSKRC.longestSubstring1("aaaaa", 2));
        assertEquals(0, LongestSKRC.longestSubstring1("abcdef", 2));
        assertEquals(6, LongestSKRC.longestSubstring1("aabbcc", 2));
        assertEquals(6, LongestSKRC.longestSubstring1("aaabbbdcccc", 2));
        assertEquals(7, LongestSKRC.longestSubstring1("abcdefg", 1));
        assertEquals(3, LongestSKRC.longestSubstring1("cbcbbaaa", 3));
    }

    @Test
    void testLongestSubstring2() {
        assertEquals(3, LongestSKRC.longestSubstring2("aaabb", 3));
        assertEquals(5, LongestSKRC.longestSubstring2("ababbc", 2));
        assertEquals(1, LongestSKRC.longestSubstring2("a", 1));
        assertEquals(0, LongestSKRC.longestSubstring2("abc", 4));
        assertEquals(5, LongestSKRC.longestSubstring2("aaaaa", 2));
        assertEquals(0, LongestSKRC.longestSubstring2("abcdef", 2));
        assertEquals(6, LongestSKRC.longestSubstring2("aabbcc", 2));
        assertEquals(6, LongestSKRC.longestSubstring2("aaabbbdcccc", 2));
        assertEquals(7, LongestSKRC.longestSubstring2("abcdefg", 1));
        assertEquals(3, LongestSKRC.longestSubstring2("cbcbbaaa", 3));
    }
}
