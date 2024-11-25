package struct;

import static struct.TrieNode.lce26;

/**
 * Included LeetCode 1804, medium. No need to remove when the count is 0.
 * It is guaranteed that for any function call to erase, the string word will exist in the trie.
 * <p>
 * This version of the trie node has the word and the boolean flag so that it is convenient to use the word or flag
 * as needed. Word field is used in WordSearchII.java.
 * <p>
 * Also, assumption of 26 lower cases english letters only.
 */
@SuppressWarnings("unused")
public class TrieNode26 {
    public String word; // note not boolean
    public boolean end;
    public TrieNode26[] next;
    public int cnt; // number of words in the dictionary having this prefix
    public int wc; // number of words, allow duplicate

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
        cur.end = true;
        cur.wc++;
    }

    public int countWordsEqualTo(String word) {
        TrieNode26 node = get(word);
        return node == null ? 0 : node.wc;
    }

    public int countWordsStartingWith(String prefix) {
        TrieNode26 node = get(prefix);
        return node == null ? 0 : node.cnt;
    }

    public void erase(String word) {
        TrieNode26 node = this;
        for (char c : word.toCharArray()) {
            c -= 'a';
            node = node.next[c];
            --node.cnt;
        }
        --node.wc;
    }

    private TrieNode26 get(String word) {
        TrieNode26 node = this;
        for (char c : word.toCharArray()) {
            c -= 'a';
            if (node.next[c] == null) return null;
            node = node.next[c];
        }
        return node;
    }

}
