package hash;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LongestPalindromeConcatenatingTest {

    @Test
    void example1() {
        assertEquals(6, LongestPalindromeConcatenating.longestPalindrome(
                new String[]{"lc", "cl", "gg"}));
    }

    @Test
    void example2() {
        assertEquals(8, LongestPalindromeConcatenating.longestPalindrome(
                new String[]{"ab", "ty", "yt", "lc", "cl", "ab"}));
    }

    @Test
    void example3() {
        assertEquals(2, LongestPalindromeConcatenating.longestPalindrome(
                new String[]{"cc", "ll", "xx"}));
    }

    @Test
    void allPalindromicPairs() {
        // aa x4 -> 2 pairs -> 8, bb x2 -> 1 pair -> 4, total 12
        assertEquals(12, LongestPalindromeConcatenating.longestPalindrome(
                new String[]{"aa", "aa", "aa", "aa", "bb", "bb"}));
    }

    @Test
    void palindromicWithCenter() {
        // aa x3 -> 1 pair (4) + center (2) = 6
        assertEquals(6, LongestPalindromeConcatenating.longestPalindrome(
                new String[]{"aa", "aa", "aa"}));
    }

    @Test
    void noPairs() {
        assertEquals(0, LongestPalindromeConcatenating.longestPalindrome(
                new String[]{"ab", "cd", "ef"}));
    }

    @Test
    void singlePalindromicWord() {
        assertEquals(2, LongestPalindromeConcatenating.longestPalindrome(
                new String[]{"gg"}));
    }

    @Test
    void emptyArray() {
        assertEquals(0, LongestPalindromeConcatenating.longestPalindrome(new String[]{}));
    }

    @Test
    void mixed() {
        // ab+ba -> 4, cd+dc -> 4, ee x3 -> 1 pair(4) + center(2) = 6
        // total = 14
        assertEquals(14, LongestPalindromeConcatenating.longestPalindrome(
                new String[]{"ab", "ba", "cd", "dc", "ee", "ee", "ee"}));
    }
}
