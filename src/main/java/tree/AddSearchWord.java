package tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
 * 1 <= word.length <= 25, L
 * Number of strings in dictionary, N
 * word in addWord consists of lowercase English letters. radix size R = 26.
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
    ImplTrie.Node root;

    public WordDictionary() {
        root = new ImplTrie.Node();
    }

    public void addWord(String word) {
        ImplTrie.Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            int id = word.charAt(i) - 'a';
            if (cur.next[id] == null) cur.next[id] = new ImplTrie.Node();
            cur = cur.next[id];
        }
        cur.isWord = true;
    }

    public boolean search(String word) { // space N*L. time: search hit O(L), search miss: O(L) L: word length
        return match(word, 0, root); // wild card, worst case time search hit or miss O(26^L) e.g., "..g"
    }

    private boolean match(String word, int d, ImplTrie.Node n) {
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

class WordDictionaryMap { // 2372ms, 50.5 Mb.

    Map<Integer, List<String>> map = new HashMap<>(); // space O(NL)

    public static void main(String[] args) {
        String[] words = {"bad", "dad", "mad"};
        String[] searches = {"pad", "bad", ".ad", "b.."};
        WordDictionaryMap tbt = new WordDictionaryMap();
        for (String w : words) tbt.addWord(w);
        for (String s : searches) System.out.println(String.format("%s in dict: %b", s, tbt.search(s)));
    }

    public void addWord(String word) {
        int len = word.length();
        if (!map.containsKey(len)) map.put(len, new ArrayList<>());
        map.get(len).add(word);
    }

    public boolean search(String word) {// search hit O(NL), search miss O(NL), trie search miss can be much less
        int len = word.length();
        if (!map.containsKey(len)) return false; // avoid NPE
        for (String s : map.get(len)) if (isSame(s, word)) return true;
        return false;
    }

    private boolean isSame(String s, String word) {
        for (int i = 0; i < word.length(); i++) // this compare can be less than O(L)
            if (word.charAt(i) != '.' && word.charAt(i) != s.charAt(i)) return false;
        return true;
    }
}
