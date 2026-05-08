package dp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WordBreakTest {
    WordBreak tbt;

    @BeforeEach
    void setUp() {
        tbt = new WordBreak();
    }

    @Test
    void testDPSet() {
        assertEquals(true, tbt.wordBreakDPSet("leetcode", List.of("leet", "code")));
        assertEquals(true, tbt.wordBreakDPSet("applepenapple", List.of("apple", "pen")));
        assertEquals(false, tbt.wordBreakDPSet("catsandog", List.of("cats", "dog", "sand", "and", "cat")));
        assertEquals(true, tbt.wordBreakDPSet("a", List.of("a")));
        assertEquals(false, tbt.wordBreakDPSet("b", List.of("a")));
        assertEquals(true, tbt.wordBreakDPSet("hello", List.of("hello")));
        assertEquals(true, tbt.wordBreakDPSet("aaaa", List.of("a", "aa")));
        assertEquals(false, tbt.wordBreakDPSet("aaaaaab", List.of("a", "aa", "aaa")));
        assertEquals(true, tbt.wordBreakDPSet("catsanddog", List.of("cats", "dog", "sand", "and", "cat")));
    }

    @Test
    void testBFS() {
        assertEquals(true, tbt.wordBreakBFS("leetcode", List.of("leet", "code")));
        assertEquals(true, tbt.wordBreakBFS("applepenapple", List.of("apple", "pen")));
        assertEquals(false, tbt.wordBreakBFS("catsandog", List.of("cats", "dog", "sand", "and", "cat")));
        assertEquals(true, tbt.wordBreakBFS("a", List.of("a")));
        assertEquals(false, tbt.wordBreakBFS("b", List.of("a")));
        assertEquals(true, tbt.wordBreakBFS("hello", List.of("hello")));
        assertEquals(true, tbt.wordBreakBFS("aaaa", List.of("a", "aa")));
        assertEquals(false, tbt.wordBreakBFS("aaaaaab", List.of("a", "aa", "aaa")));
        assertEquals(true, tbt.wordBreakBFS("catsanddog", List.of("cats", "dog", "sand", "and", "cat")));
    }
}
