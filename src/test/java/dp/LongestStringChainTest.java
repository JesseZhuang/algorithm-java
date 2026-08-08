package dp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LongestStringChainTest {

    @Test
    void example1() {
        String[] words = {"a", "b", "ba", "bca", "bda", "bdca"};
        assertEquals(4, LongestStringChain.longestStrChain(words));
    }

    @Test
    void example2() {
        String[] words = {"xbc", "pcxbcf", "xb", "cxbc", "pcxbc"};
        assertEquals(5, LongestStringChain.longestStrChain(words));
    }

    @Test
    void singleWord() {
        String[] words = {"abcd"};
        assertEquals(1, LongestStringChain.longestStrChain(words));
    }

    @Test
    void noChain() {
        String[] words = {"abc", "def", "ghi"};
        assertEquals(1, LongestStringChain.longestStrChain(words));
    }

    @Test
    void allSameLength() {
        String[] words = {"ab", "cd", "ef"};
        assertEquals(1, LongestStringChain.longestStrChain(words));
    }
}
