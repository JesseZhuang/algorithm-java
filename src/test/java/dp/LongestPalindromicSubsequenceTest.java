package dp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LongestPalindromicSubsequenceTest {

    @Test
    void testBbbab() {
        assertEquals(4, LongestPalindromicSubsequence.longestPalindromeSubseq("bbbab"));
        assertEquals(4, LongestPalindromicSubsequence.longestPalindromeSubseqOptimized("bbbab"));
    }

    @Test
    void testCbbd() {
        assertEquals(2, LongestPalindromicSubsequence.longestPalindromeSubseq("cbbd"));
        assertEquals(2, LongestPalindromicSubsequence.longestPalindromeSubseqOptimized("cbbd"));
    }

    @Test
    void testSingleChar() {
        assertEquals(1, LongestPalindromicSubsequence.longestPalindromeSubseq("a"));
        assertEquals(1, LongestPalindromicSubsequence.longestPalindromeSubseqOptimized("a"));
    }

    @Test
    void testAllSame() {
        assertEquals(4, LongestPalindromicSubsequence.longestPalindromeSubseq("aaaa"));
        assertEquals(4, LongestPalindromicSubsequence.longestPalindromeSubseqOptimized("aaaa"));
    }

    @Test
    void testPalindrome() {
        assertEquals(7, LongestPalindromicSubsequence.longestPalindromeSubseq("racecar"));
        assertEquals(7, LongestPalindromicSubsequence.longestPalindromeSubseqOptimized("racecar"));
    }

    @Test
    void testAllDistinct() {
        assertEquals(1, LongestPalindromicSubsequence.longestPalindromeSubseq("abcde"));
        assertEquals(1, LongestPalindromicSubsequence.longestPalindromeSubseqOptimized("abcde"));
    }

    @Test
    void testTwoSame() {
        assertEquals(2, LongestPalindromicSubsequence.longestPalindromeSubseq("aa"));
        assertEquals(2, LongestPalindromicSubsequence.longestPalindromeSubseqOptimized("aa"));
    }

    @Test
    void testTwoDifferent() {
        assertEquals(1, LongestPalindromicSubsequence.longestPalindromeSubseq("ab"));
        assertEquals(1, LongestPalindromicSubsequence.longestPalindromeSubseqOptimized("ab"));
    }

    @Test
    void testCharacter() {
        assertEquals(5, LongestPalindromicSubsequence.longestPalindromeSubseq("character"));
        assertEquals(5, LongestPalindromicSubsequence.longestPalindromeSubseqOptimized("character"));
    }
}
