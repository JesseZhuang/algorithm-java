package struct;

import static struct.TrieNode.lce26;

/**
 * This version of the trie node has the word and the boolean flag so that it is convenient to use the word or flag
 * as needed. Word field is used in WordSearchII.java.
 * <p>
 * Also, assumption of 26 lower cases english letters only.
 */
public class TrieNode26 {
    public String word; // note not boolean
    public boolean isWord;
    public TrieNode26[] next;
    public int cnt; // number of words in the dictionary having this prefix

    public TrieNode26() {
        next = new TrieNode26[26];
    }

    /**
     * add a word into the trie
     *
     * @param s the word string to add
     */
    public void insert(String s) {
        TrieNode26 cur = this;
        for (int i = 0; i < s.length(); i++) {
            int id = lce26.apply(s.charAt(i));
            if (cur.next[id] == null) cur.next[id] = new TrieNode26();
            cur = cur.next[id];
            cur.cnt++;
        }
        cur.word = s;
        cur.isWord = true;
    }


}
