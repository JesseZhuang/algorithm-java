package struct;

import java.util.function.Function;

/**
 * a TrieNode for trie. @see {@link princeton.jsl.TrieSET}.
 */
public class TrieNode {
    public boolean isWord;
    public TrieNode[] next;
    int R; // radix

    /**
     * construct a trie node
     *
     * @param R radix size, should be consistent in same trie
     */
    public TrieNode(int R) {
        isWord = false;
        this.R = R;
        next = new TrieNode[R];
    }

    /**
     * add a word into the trie.
     *
     * @param s              the word string to add
     * @param mapCharToIndex a function to map the character to index to be used in trie indexing
     */
    public void addWord(String s, Function<Character, Integer> mapCharToIndex) {
        TrieNode node = this;
        for (int i = 0; i < s.length(); i++) {
            int j = mapCharToIndex.apply(s.charAt(i));
            if (node.next[j] == null) node.next[j] = new TrieNode(R);
            node = node.next[j];
        }
        node.isWord = true;
    }
}
