package string;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MinWindowSubstringTest {
    @Test
    void minWindow() {
        MinWindowSubstring tbt = new MinWindowSubstring();
        assertEquals("BANC", tbt.minWindow("ADOBECODEBANC", "ABC"));
        assertEquals("a", tbt.minWindow("a", "a"));
        assertEquals("", tbt.minWindow("a", "aa"));
        assertEquals("", tbt.minWindow("a", "b"));
        assertEquals("a", tbt.minWindow("ab", "a"));
        assertEquals("b", tbt.minWindow("ab", "b"));
        assertEquals("abc", tbt.minWindow("abc", "ac"));
        assertEquals("ab", tbt.minWindow("bdab", "ab"));
        assertEquals("aaa", tbt.minWindow("aaflslflsldkalskaaa", "aaa"));
        assertEquals("cwae", tbt.minWindow("cabwefgewcwaefgcf", "cae"));
    }
}
