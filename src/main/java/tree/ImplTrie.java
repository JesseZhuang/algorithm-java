package tree;

/**
 * LeetCode 208, medium, tags: hash table, string, design, trie.
 * Companies: pinterest.
 * <p>
 * A trie (pronounced as "try") or prefix tree is a tree data structure used to efficiently store and retrieve keys in
 * a dataset of strings. There are various applications of this data structure, such as autocomplete and spellchecker.
 * <p>
 * Implement the Trie class:
 * <p>
 * Trie() Initializes the trie object.
 * void insert(String word) Inserts the string word into the trie.
 * boolean search(String word) Returns true if the string word is in the trie (i.e., was inserted before), and
 * false otherwise.
 * boolean startsWith(String prefix) Returns true if there is a previously inserted string word that has the
 * prefix, and false otherwise.
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
 * [[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
 * Output
 * [null, null, true, false, true, null, true]
 * <p>
 * Explanation
 * Trie trie = new Trie();
 * trie.insert("apple");
 * trie.search("apple");   // return True
 * trie.search("app");     // return False
 * trie.startsWith("app"); // return True
 * trie.insert("app");
 * trie.search("app");     // return True
 * <p>
 * Constraints:
 * <p>
 * 1 <= word.length, prefix.length <= 2000
 * word and prefix consist only of lowercase English letters.
 * At most 3 * 104 calls in total will be made to insert, search, and startsWith
 */
@SuppressWarnings("unused")
public class ImplTrie { // 36ms, 50.6 Mb. iterative.

    static class Node {
        private static final int R = 26;
        boolean isWord;
        Node[] next; // only one null in JVM, space is not linear with number of null links

        Node() {
            next = new Node[R];
        }
    }

    public static class ImplTrieI {
        Node root;

        public ImplTrieI() { // O(1) time, O(26, 1) space.
            root = new Node();
        }

        public void insert(String word) { // O(n) time, n word length, O(26n) space.
            Node cur = root;
            for (int i = 0; i < word.length(); i++) {
                int id = word.charAt(i) - 'a';
                if (cur.next[id] == null) cur.next[id] = new Node();
                cur = cur.next[id];
            }
            cur.isWord = true;
        }

        public boolean search(String word) { // O(n) time, O(1) space
            Node n = get(word);
            return n != null && n.isWord;
        }

        public boolean startsWith(String prefix) {
            return get(prefix) != null;
        }

        private Node get(String word) { // O(n) time, O(1) space
            Node cur = root;
            for (int i = 0; i < word.length(); i++) {
                int id = word.charAt(i) - 'a';
                if (cur.next[id] == null) return null;
                cur = cur.next[id];
            }
            return cur;
        }
    }

    static class ImplTrieR { // 33ms, 51.1 Mb. recursive.
        Node root;

        ImplTrieR() {
            root = new Node();
        }

        private Node insert(Node n, String word, int d) {
            if (n == null) n = new Node();
            if (d < word.length()) {
                int id = word.charAt(d) - 'a';
                n.next[id] = insert(n.next[id], word, d + 1);
            } else n.isWord = true; // else must be here
            return n; // must return n, otherwise when n.next[id] is null, the created new node cannot be set as n.next[id]
        }

        public void insert(String word) { // + O(n) recursion stack space.
            root = insert(root, word, 0);
        }

        public boolean search(String word) {
            Node n = get(root, word, 0);
            return n != null && n.isWord;
        }

        public boolean startsWith(String prefix) {
            return get(root, prefix, 0) != null;
        }

        // O(n) time, O(n) recursive stack space
        private Node get(Node n, String word, int d) {
            if (n == null) return null;
            if (d == word.length()) return n;
            return get(n.next[word.charAt(d) - 'a'], word, d + 1);
        }
    }
}



