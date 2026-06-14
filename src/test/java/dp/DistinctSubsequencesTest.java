package dp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DistinctSubsequencesTest {
    @Test
    void test1D() {
        assertEquals(3, DistinctSubsequences.numDistinct("rabbbit", "rabbit"));
        assertEquals(5, DistinctSubsequences.numDistinct("babgbag", "bag"));
        assertEquals(0, DistinctSubsequences.numDistinct("abc", "def"));
        assertEquals(1, DistinctSubsequences.numDistinct("abc", ""));
        assertEquals(0, DistinctSubsequences.numDistinct("", "a"));
        assertEquals(1, DistinctSubsequences.numDistinct("", ""));
        assertEquals(1, DistinctSubsequences.numDistinct("abc", "abc"));
        assertEquals(3, DistinctSubsequences.numDistinct("aaa", "a"));
        assertEquals(0, DistinctSubsequences.numDistinct("a", "aaa"));
        assertEquals(10, DistinctSubsequences.numDistinct("aaaaa", "aaa"));
    }

    @Test
    void test2D() {
        assertEquals(3, DistinctSubsequences.numDistinct2D("rabbbit", "rabbit"));
        assertEquals(5, DistinctSubsequences.numDistinct2D("babgbag", "bag"));
        assertEquals(0, DistinctSubsequences.numDistinct2D("abc", "def"));
        assertEquals(1, DistinctSubsequences.numDistinct2D("abc", ""));
        assertEquals(0, DistinctSubsequences.numDistinct2D("", "a"));
        assertEquals(1, DistinctSubsequences.numDistinct2D("", ""));
        assertEquals(1, DistinctSubsequences.numDistinct2D("abc", "abc"));
        assertEquals(3, DistinctSubsequences.numDistinct2D("aaa", "a"));
        assertEquals(0, DistinctSubsequences.numDistinct2D("a", "aaa"));
        assertEquals(10, DistinctSubsequences.numDistinct2D("aaaaa", "aaa"));
    }
}
