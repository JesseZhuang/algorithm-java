package string;

import struct.TrieNode26;
import tree.ImplTrie;

import java.util.*;

import static util.Constants.dirs;
import static util.Constants.inside;

/**
 * LeetCode 212, hard, tags: trie, string, matrix, array, backtracking.
 * <p>
 * Given an m x n board of characters and a list of strings words, return all words on the board.
 * <p>
 * Each word must be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or
 * vertically neighboring. The same letter cell may not be used more than once in a word.
 * <p>
 * Example 1: https://assets.leetcode.com/uploads/2020/11/07/search1.jpg
 * <p>
 * Input: board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]],
 * words = ["oath","pea","eat","rain"]
 * Output: ["eat","oath"]
 * Example 2: https://assets.leetcode.com/uploads/2020/11/07/search2.jpg
 * <p>
 * Input: board = [["a","b"],["c","d"]], words = ["abcb"]
 * Output: []
 * <p>
 * Constraints:
 * <p>
 * m == board.length
 * n == board[i].length
 * 1 <= m, n <= 12
 * board[i][j] is a lowercase English letter.
 * 1 <= words.length <= 3 * 10^4, N
 * 1 <= words[i].length <= 10, L
 * words[i] consists of lowercase English letters.
 * All the strings of words are unique.
 * <p>
 * Hints:
 * <p>
 * You would need to optimize your backtracking to pass the larger test. Could you stop backtracking earlier?
 * <p>
 * If the current candidate does not exist in all words' prefix, you could stop backtracking immediately.
 * What kind of data structure could answer such query efficiently? Does a hash table work? Why or why not?
 * How about a Trie? If you would like to learn how to implement a basic trie, please work on this problem:
 * Implement Trie (Prefix Tree) first.
 */
public class WordSearchII {
    int m, n;
    TrieNode26 root;
    List<String> res;
    char[][] board;

    public static void main(String[] args) {
        char[][] board = {{'o', 'a', 'a', 'n'}, {'e', 't', 'a', 'e'}, {'i', 'h', 'k', 'r'}, {'i', 'f', 'l', 'v'}};
        String[] words = {"oath", "pea", "eat", "rain"}; // expected: {'eat','oath'}
        WordSearchII tbt = new WordSearchII();
        System.out.println(tbt.findWords(board, words));
        board = new char[][]{{'a', 'b'}, {'c', 'd'}};
        words = new String[]{"abcb"};  // expected: {}
        System.out.println(tbt.findWords(board, words));
        board = new char[][]{{'o', 'a', 'a', 'n'}, {'e', 't', 'a', 'e'}, {'i', 'h', 'k', 'r'}, {'i', 'f', 'l', 'v'}};
        words = new String[]{"oath", "pea", "eat", "rain", "hklf", "hf"};
        System.out.println(tbt.findWords(board, words)); // expected: [oath, eat, hf, hklf]
        board = new char[][]{{'a', 'a'}, {'a', 'a'}};
        words = new String[]{"a"};
        System.out.println(tbt.findWords1(board, words)); // multiple "a" found if not dedupe
    }

    // solution 1, 153ms, 42.8Mb. dfs with trie node. O(mn*4^L) time.
    public List<String> findWords(char[][] board, String[] words) {
        root = new TrieNode26();
        this.board = board;
        for (String w : words) root.addWord(w);
        m = board.length;
        n = board[0].length;
        res = new ArrayList<>();
        for (int i = 0; i < m; i++) // O(m)
            for (int j = 0; j < n; j++) // O(n)
                dfs(i, j, root); // O(4^L)
        return res;
    }

    private void dfs(int i, int j, TrieNode26 node) {
        if (!inside(i, j, m, n)) return;
        char tmp = board[i][j];
        int id = tmp - 'a';
        if (tmp == '#' || node.next[id] == null) return;
        node = node.next[id];
        if (node.word != null) {
            res.add(node.word);
            node.word = null; // dedupe, trick
        }
        board[i][j] = '#'; // save boolean[][] visited trick
        for (int[] d : dirs) dfs(i + d[0], j + d[1], node);
        board[i][j] = tmp;
    }

    // solution 2, slow, 2668 ms, 45.6 Mb. dfs with trie. O(mnl*4^l) time.
    // check trie.startsWith(candidate) will start from trie root every time
    // e.g., for apple, a, ap, app, appl will need to go down from a from the top
    public List<String> findWords1(char[][] board, String[] words) {
        ImplTrie.ImplTrieI trie = new ImplTrie.ImplTrieI();
        for (String w : words) trie.insert(w); // O(NL) time and space.
        Set<String> result = new HashSet<>(); // dedupe
        boolean[][] visited = new boolean[board.length][board[0].length]; // reuse, no need new for each iteration
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                String s = Character.toString(board[r][c]); // StringBuilder(char) set capacity
                if (trie.startsWith(s)) // O(L*4^L)
                    findWords1(board, trie, result, r, c, visited, new StringBuilder(s));
            }
        }
        return Arrays.asList(result.toArray(new String[0]));
    }

    private void findWords1(char[][] board, ImplTrie.ImplTrieI trie, Set<String> found, int r, int c,
                            boolean[][] visited, StringBuilder candidate) {
        visited[r][c] = true;
        String cs = candidate.toString();
        if (trie.search(cs)) found.add(cs);
        for (int[] d : dirs) {
            int nr = r + d[0], nc = c + d[1];
            if (nr >= 0 && nr < board.length && nc >= 0 && nc < board[0].length && !visited[nr][nc]) {
                if (trie.startsWith(candidate.append(board[nr][nc]).toString()))
                    findWords1(board, trie, found, nr, nc, visited, candidate);
                candidate.deleteCharAt(candidate.length() - 1); // do not forget to reset
            }
        }
        visited[r][c] = false; // important!
    }

}
