package string;

import tree.ImplTrie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    // 153ms, 42.8Mb. dfs with trie node. O(mn*4^L) time.
    public List<String> findWords(char[][] board, String[] words) {
        Node root = new Node();
        for (String w : words) insert(w, root);
        int m = board.length, n = board[0].length;
        List<String> res = new ArrayList<>();
        for (int r = 0; r < m; r++) // O(m)
            for (int c = 0; c < n; c++) // O(n)
                dfs(board, r, c, root, res); // O(4^L)
        return res;
    }

    private void dfs(char[][] board, int r, int c, Node n, List<String> res) {
        char c1 = board[r][c];
        int id = c1 - 'a';
        if (c == '#' || n.next[id] == null) return;
        n = n.next[id];
        if (n.word != null) {
            res.add(n.word);
            n.word = null; // dedupe, trick
        }
        board[r][c] = '#'; // save boolean[][] visited trick
        if (r > 0) dfs(board, r - 1, c, n, res);
        if (c > 0) dfs(board, r, c - 1, n, res);
        if (r < board.length - 1) dfs(board, r + 1, c, n, res);
        if (c < board[0].length - 1) dfs(board, r, c + 1, n, res);
        board[r][c] = c1;
    }


    private void insert(String word, Node root) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            int id = word.charAt(i) - 'a';
            if (cur.next[id] == null) cur.next[id] = new Node();
            cur = cur.next[id];
        }
        cur.word = word;
    }

    // 2668 ms, 45.6 Mb. dfs with trie. O(mnl*4^l) time.
    public List<String> findWords1(char[][] board, String[] words) {
        ImplTrie trie = new ImplTrie();
        for (String w : words) trie.insert(w); // O(NL) time and space.
        Set<String> result = new HashSet<>();
        boolean[][] visited = new boolean[board.length][board[0].length]; // reuse, no need new for each iteration
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                String s = Character.toString(board[r][c]); // StringBuilder(char) set capacity
                if (trie.startsWith(s)) // O(L*4^L)
                    findWords1(board, trie, result, r, c, visited, new StringBuilder(s));
            }
        }
        return Arrays.asList(result.toArray(new String[result.size()]));
    }

    private void findWords1(char[][] board, ImplTrie trie, Set<String> found, int r, int c,
                            boolean[][] visited, StringBuilder candidate) {
        visited[r][c] = true;
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
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

    public static void main(String[] args) {
        char[][] board = {{'o', 'a', 'a', 'n'}, {'e', 't', 'a', 'e'}, {'i', 'h', 'k', 'r'}, {'i', 'f', 'l', 'v'}};
        String[] words = {"oath", "pea", "eat", "rain"}; // Output: {'eat','oath'}
        WordSearchII tbt = new WordSearchII();
        System.out.println(tbt.findWords1(board, words));
        board = new char[][]{{'a', 'b'}, {'c', 'd'}};
        words = new String[]{"abcb"};  //Output: {}
        System.out.println(tbt.findWords1(board, words));
        board = new char[][]{{'o', 'a', 'a', 'n'}, {'e', 't', 'a', 'e'}, {'i', 'h', 'k', 'r'}, {'i', 'f', 'l', 'v'}};
        words = new String[]{"oath", "pea", "eat", "rain", "hklf", "hf"};
        System.out.println(tbt.findWords1(board, words));
    }
}

class Node {
    String word;
    Node[] next;

    Node() {
        next = new Node[26];
    }
}