package tree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AddSearchWordTest {

    @Test
    void testTrieBasicExample() {
        WordDictionary wd = new WordDictionary();
        wd.addWord("bad");
        wd.addWord("dad");
        wd.addWord("mad");
        assertFalse(wd.search("pad"));
        assertTrue(wd.search("bad"));
        assertTrue(wd.search(".ad"));
        assertTrue(wd.search("b.."));
    }

    @Test
    void testTrieEmptyDictionary() {
        WordDictionary wd = new WordDictionary();
        assertFalse(wd.search("a"));
    }

    @Test
    void testTrieSingleChar() {
        WordDictionary wd = new WordDictionary();
        wd.addWord("a");
        assertTrue(wd.search("a"));
        assertTrue(wd.search("."));
        assertFalse(wd.search("b"));
        assertFalse(wd.search(".."));
    }

    @Test
    void testTrieAllDots() {
        WordDictionary wd = new WordDictionary();
        wd.addWord("abc");
        wd.addWord("xyz");
        assertTrue(wd.search("..."));
        assertFalse(wd.search(".."));
        assertFalse(wd.search("...."));
    }

    @Test
    void testTriePrefixNotWord() {
        WordDictionary wd = new WordDictionary();
        wd.addWord("apple");
        assertFalse(wd.search("app"));
        assertTrue(wd.search("apple"));
        assertTrue(wd.search("appl."));
    }

    @Test
    void testTrieSamePrefix() {
        WordDictionary wd = new WordDictionary();
        wd.addWord("app");
        wd.addWord("apple");
        assertTrue(wd.search("app"));
        assertTrue(wd.search("apple"));
        assertFalse(wd.search("appl"));
    }

    @Test
    void testTrieDotInMiddle() {
        WordDictionary wd = new WordDictionary();
        wd.addWord("bat");
        wd.addWord("bar");
        assertTrue(wd.search("ba."));
        assertTrue(wd.search("b.t"));
        assertFalse(wd.search("b.x"));
    }

    @Test
    void testMapBasicExample() {
        WordDictionaryMap wd = new WordDictionaryMap();
        wd.addWord("bad");
        wd.addWord("dad");
        wd.addWord("mad");
        assertFalse(wd.search("pad"));
        assertTrue(wd.search("bad"));
        assertTrue(wd.search(".ad"));
        assertTrue(wd.search("b.."));
    }

    @Test
    void testMapEmptyDictionary() {
        WordDictionaryMap wd = new WordDictionaryMap();
        assertFalse(wd.search("a"));
    }

    @Test
    void testMapSingleChar() {
        WordDictionaryMap wd = new WordDictionaryMap();
        wd.addWord("a");
        assertTrue(wd.search("a"));
        assertTrue(wd.search("."));
        assertFalse(wd.search("b"));
        assertFalse(wd.search(".."));
    }

    @Test
    void testMapAllDots() {
        WordDictionaryMap wd = new WordDictionaryMap();
        wd.addWord("abc");
        wd.addWord("xyz");
        assertTrue(wd.search("..."));
        assertFalse(wd.search(".."));
        assertFalse(wd.search("...."));
    }

    @Test
    void testMapPrefixNotWord() {
        WordDictionaryMap wd = new WordDictionaryMap();
        wd.addWord("apple");
        assertFalse(wd.search("app"));
        assertTrue(wd.search("apple"));
        assertTrue(wd.search("appl."));
    }

    @Test
    void testMapSamePrefix() {
        WordDictionaryMap wd = new WordDictionaryMap();
        wd.addWord("app");
        wd.addWord("apple");
        assertTrue(wd.search("app"));
        assertTrue(wd.search("apple"));
        assertFalse(wd.search("appl"));
    }

    @Test
    void testMapDotInMiddle() {
        WordDictionaryMap wd = new WordDictionaryMap();
        wd.addWord("bat");
        wd.addWord("bar");
        assertTrue(wd.search("ba."));
        assertTrue(wd.search("b.t"));
        assertFalse(wd.search("b.x"));
    }
}
