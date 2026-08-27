package dp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InterleavingStringTest {
    @Test
    void test1D() {
        assertEquals(true, InterleavingString.isInterleave("aabcc", "dbbca", "aadbbcbcac"));
        assertEquals(false, InterleavingString.isInterleave("aabcc", "dbbca", "aadbbbaccc"));
        assertEquals(true, InterleavingString.isInterleave("", "", ""));
        assertEquals(false, InterleavingString.isInterleave("", "", "a"));
        assertEquals(true, InterleavingString.isInterleave("a", "", "a"));
        assertEquals(true, InterleavingString.isInterleave("", "b", "b"));
        assertEquals(true, InterleavingString.isInterleave("a", "b", "ab"));
        assertEquals(true, InterleavingString.isInterleave("a", "b", "ba"));
        assertEquals(true, InterleavingString.isInterleave("abc", "def", "adbcef"));
        assertEquals(true, InterleavingString.isInterleave("abc", "def", "abcdef"));
        assertEquals(true, InterleavingString.isInterleave("aaaa", "aaaa", "aaaaaaaa"));
        assertEquals(true, InterleavingString.isInterleave("ab", "cd", "cadb"));
        assertEquals(false, InterleavingString.isInterleave("ab", "cd", "cdba"));
    }

    @Test
    void test2D() {
        assertEquals(true, InterleavingString.isInterleave2("aabcc", "dbbca", "aadbbcbcac"));
        assertEquals(false, InterleavingString.isInterleave2("aabcc", "dbbca", "aadbbbaccc"));
        assertEquals(true, InterleavingString.isInterleave2("", "", ""));
        assertEquals(false, InterleavingString.isInterleave2("", "", "a"));
        assertEquals(true, InterleavingString.isInterleave2("a", "", "a"));
        assertEquals(true, InterleavingString.isInterleave2("", "b", "b"));
        assertEquals(true, InterleavingString.isInterleave2("a", "b", "ab"));
        assertEquals(true, InterleavingString.isInterleave2("a", "b", "ba"));
        assertEquals(true, InterleavingString.isInterleave2("abc", "def", "adbcef"));
        assertEquals(true, InterleavingString.isInterleave2("abc", "def", "abcdef"));
        assertEquals(true, InterleavingString.isInterleave2("aaaa", "aaaa", "aaaaaaaa"));
        assertEquals(true, InterleavingString.isInterleave2("ab", "cd", "cadb"));
        assertEquals(false, InterleavingString.isInterleave2("ab", "cd", "cdba"));
    }
}
