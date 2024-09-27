package struct;

import java.util.function.Function;

/**
 * a TrieNode for trie. @see {@link princeton.jsl.TrieSET}.
 */
public class TrieNode {
    /**
     * convert lower case english letter to index [0-25]
     */
    public static Function<Character, Integer> lce26 = c -> c - 'a';
    /**
     * convert 10 decimal digits to index [0,9]
     */
    public static Function<Character, Integer> decimal = d -> d - '0';

    private final Function<Character, Integer> charToId;
    public boolean isWord;
    public TrieNode[] next;
    int R; // radix

    /**
     * construct a trie node
     *
     * @param R         radix size, should be consistent in same trie
     * @param charToId, the mapping function to convert character to index.
     */
    public TrieNode(int R, Function<Character, Integer> charToId) {
        this.charToId = charToId;
        isWord = false;
        this.R = R;
        next = new TrieNode[R];
    }

    /**
     * Constructor for 26 lower case english letters.
     */
    public TrieNode() {
        this(26, lce26);
    }

    /**
     * add a word into the trie.
     *
     * @param s the word string to add
     */
    public void insert(String s) {
        TrieNode cur = this;
        for (int i = 0; i < s.length(); i++) {
            int j = charToId.apply(s.charAt(i));
            if (cur.next[j] == null) cur.next[j] = new TrieNode(R, charToId);
            cur = cur.next[j];
        }
        cur.isWord = true;
    }

    /**
     * find the length of the longest prefix from this trie node.
     *
     * @param s input string.
     * @return length of lcp for string s.
     */
    public int lcpLen(String s) {
        TrieNode cur = this;
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            int j = charToId.apply(s.charAt(i));
            if (cur.next[j] == null) break;
            cur = cur.next[j];
            res++;
        }
        return res;
    }

}
