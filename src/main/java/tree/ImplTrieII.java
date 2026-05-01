package tree;

/**
 * <a href="https://leetcode.com/problems/implement-trie-ii-prefix-tree/">LeetCode 1804</a>, medium,
 * tags: design, trie, hash table, string.
 */
public final class ImplTrieII {
    private ImplTrieII() {}

    public static class Trie {
        private final Trie[] next = new Trie[26];
        private int wordCount = 0;
        private int prefixCount = 0;

        public Trie() {}

        public void insert(String word) {
            Trie cur = this;
            for (char c : word.toCharArray()) {
                int id = c - 'a';
                if (cur.next[id] == null) cur.next[id] = new Trie();
                cur = cur.next[id];
                cur.prefixCount++;
            }
            cur.wordCount++;
        }

        public int countWordsEqualTo(String word) {
            Trie node = get(word);
            return node == null ? 0 : node.wordCount;
        }

        public int countWordsStartingWith(String prefix) {
            Trie node = get(prefix);
            return node == null ? 0 : node.prefixCount;
        }

        public void erase(String word) {
            Trie cur = this;
            for (char c : word.toCharArray()) {
                int id = c - 'a';
                cur = cur.next[id];
                cur.prefixCount--;
            }
            cur.wordCount--;
        }

        private Trie get(String word) {
            Trie cur = this;
            for (char c : word.toCharArray()) {
                int id = c - 'a';
                if (cur.next[id] == null) return null;
                cur = cur.next[id];
            }
            return cur;
        }
    }
}
