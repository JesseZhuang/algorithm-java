package tree;

/**
 * LeetCode 211, medium, tags: string, dfs, design, trie.
 * <p>
 * Design a data structure that supports adding new words and finding if a string matches any previously added string.
 * <p>
 * Implement the WordDictionary class:
 * <p>
 * WordDictionary() Initializes the object.
 * void addWord(word) Adds word to the data structure, it can be matched later.
 * bool search(word) Returns true if there is any string in the data structure that matches word or false otherwise.
 * word may contain dots '.' where dots can be matched with any letter.
 * <p>
 * <p>
 * Example:
 * <p>
 * Input
 * ["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
 * [[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
 * Output
 * [null,null,null,null,false,true,true,true]
 * <p>
 * Explanation
 * <pre>
 * WordDictionary wordDictionary = new WordDictionary();
 * wordDictionary.addWord("bad");
 * wordDictionary.addWord("dad");
 * wordDictionary.addWord("mad");
 * wordDictionary.search("pad"); // return False
 * wordDictionary.search("bad"); // return True
 * wordDictionary.search(".ad"); // return True
 * wordDictionary.search("b.."); // return True
 * </pre>
 * <p>
 * Constraints:
 * <p>
 * 1 <= word.length <= 25
 * word in addWord consists of lowercase English letters.
 * word in search consist of '.' or lowercase English letters.
 * There will be at most 3 dots in word for search queries.
 * At most 104 calls will be made to addWord and search.
 * <p>
 * Hint: You should be familiar with how a Trie works. If not, please work on this problem:
 * Implement Trie (Prefix Tree) first.
 */
public class AddSearchWord {
}

class WordDictionary { // 452 ms, 96 Mb.
    Node root;

    public WordDictionary() { // N: number of words in trie.
        root = new Node();
    }

    public void addWord(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            int id = word.charAt(i) - 'a';
            if (cur.next[id] == null) cur.next[id] = new Node();
            cur = cur.next[id];
        }
        cur.isWord = true;
    }

    public boolean search(String word) { // space R*N. time: search hit O(L), search miss: Log_R(L) L: word length
        return match(word, 0, root);
    }

    private boolean match(String word, int d, Node n) {
        if (d == word.length()) return n.isWord;
        int id = word.charAt(d) - 'a';
        if (word.charAt(d) != '.') return n.next[id] != null && match(word, d + 1, n.next[id]);
        else {
            for (int i = 0; i < n.next.length; i++)
                if (n.next[i] != null)
                    if (match(word, d + 1, n.next[i])) return true;
        }
        return false;
    }
}
