package struct;

import java.util.function.Function;

import static struct.TrieNode.lce26;

/**
 * This version of the trie node has the word instead of the boolean flag so that it is convenient to use the word,
 * which is the sequence of the characters coming down from root. Used in WordSearchII.java.
 */
public class TrieNodeS {
    public String word; // note not boolean
    public TrieNodeS[] next;
    int R;

    public TrieNodeS(int R) {
        next = new TrieNodeS[R];
        this.R = R;
    }

    public TrieNodeS() {
        this(26);
    }

    /**
     * add a word into the trie.
     *
     * @param s              the word string to add
     * @param mapCharToIndex a function to map the character to index to be used in trie indexing
     */
    public void addWord(String s, Function<Character, Integer> mapCharToIndex) {
        TrieNodeS node = this;
        for (int i = 0; i < s.length(); i++) {
            int j = mapCharToIndex.apply(s.charAt(i));
            if (node.next[j] == null) node.next[j] = new TrieNodeS(R);
            node = node.next[j];
        }
        node.word = s;
    }

    /**
     * add a word into the trie
     *
     * @param s the word string to add
     */
    public void addWord(String s) {
        addWord(s, lce26);
    }
}
