package string;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class KMP1DTest {

    KMP1D tbt;

    /**
     * restartTable[j] is used as j=restartTable[j-1] when search needle in haystack
     * for aaab example, table[2]=2. for haystack aaaab
     * mismatch when j==3, i==3
     * we back up j to 2, will match when haystack[i] == 'a'. then match 'b' j==3, i==4, found needle
     * In brute force search, on mismatch at i==3 j==3, we back up i to 1, j to 0.
     */
    @Test
    void testTable() {
        tbt = new KMP1D("aaab");
        assertArrayEquals(new int[]{0, 1, 2, 0}, tbt.restartTable);
    }

    @ParameterizedTest
    @CsvSource({"abcd,a", "aacecaaa,aacecaa", "aca,aca", "aaab,aaa"})
    void findPalindromePrefix(String s, String p) {
        assertEquals(p, KMP1D.longestPalindrome(s));
    }
}