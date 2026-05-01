package tree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ImplTrieIITest {
    @Test
    void testTrieII() {
        ImplTrieII.Trie trie = new ImplTrieII.Trie();
        trie.insert("apple");
        trie.insert("apple");
        assertEquals(2, trie.countWordsEqualTo("apple"));
        assertEquals(2, trie.countWordsStartingWith("app"));
        trie.erase("apple");
        assertEquals(1, trie.countWordsEqualTo("apple"));
        assertEquals(1, trie.countWordsStartingWith("app"));
        trie.erase("apple");
        assertEquals(0, trie.countWordsStartingWith("app"));
    }
}
