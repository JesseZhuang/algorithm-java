package string;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://leetcode.com/problems/words-within-two-edits-of-dictionary/">LeetCode 2452</a>, medium,
 * tags: array, string, trie.
 * <p>
 * You are given two string arrays, queries and dictionary. All words in each array comprise of lowercase English
 * letters and have the same length.
 * <p>
 * In one edit you can take a word from queries, and change any letter in it to any other letter. Find all words
 * from queries that, after a maximum of two edits, equal some word from dictionary.
 * <p>
 * Return a list of all words from queries that match with some word from dictionary after a maximum of two edits.
 * Return the words in the same order they appear in queries.
 * <p>
 * Example 1:
 * <p>
 * Input: queries = ["word","note","ants","wood"], dictionary = ["wood","joke","moat"]
 * Output: ["word","note","wood"]
 * <p>
 * Example 2:
 * <p>
 * Input: queries = ["yes"], dictionary = ["not"]
 * Output: []
 * <p>
 * Constraints:
 * <p>
 * 1 <= queries.length, dictionary.length <= 100
 * n == queries[i].length == dictionary[j].length
 * 1 <= n <= 100
 * All queries[i] and dictionary[j] are composed of lowercase English letters.
 */
public final class WordsWithinTwoEdits {
    private WordsWithinTwoEdits() {}

    /**
     * Brute force: compare each query with every dictionary word character by character. 5ms, 44.38mb.
     * Time O(q * d * m), Space O(1) extra, where q = queries.length, d = dictionary.length, m = word length.
     */
    public static List<String> twoEditWordsBrute(String[] queries, String[] dictionary) {
        List<String> res = new ArrayList<>();
        for (String q : queries) { // O(q)
            for (String d : dictionary) { // O(d)
                int mismatches = 0;
                for (int i = 0; i < q.length() && mismatches <= 2; i++) // O(m)
                    if (q.charAt(i) != d.charAt(i)) mismatches++;
                if (mismatches <= 2) {
                    res.add(q);
                    break;
                }
            }
        }
        return res;
    }

    /**
     * Trie with DFS: build a trie from dictionary words, then DFS each query allowing up to 2 mismatches.
     * 12ms, 47.90mb.
     * Time O(d * m) build + O(q * 26^2 * m) worst-case query. Space O(d * m) for trie.
     */
    public static List<String> twoEditWordsTrie(String[] queries, String[] dictionary) {
        int[][] trie = new int[dictionary.length * queries[0].length() + 1][26]; // trie nodes
        boolean[] end = new boolean[trie.length]; // marks end of a word
        int size = 1; // node 0 is root
        for (String d : dictionary) { // O(d * m) build trie
            int node = 0;
            for (int i = 0; i < d.length(); i++) {
                int c = d.charAt(i) - 'a';
                if (trie[node][c] == 0) trie[node][c] = size++;
                node = trie[node][c];
            }
            end[node] = true;
        }
        List<String> res = new ArrayList<>();
        for (String q : queries) // O(q)
            if (dfs(trie, end, q, 0, 0, 0)) res.add(q);
        return res;
    }

    private static boolean dfs(int[][] trie, boolean[] end, String q, int node, int i, int edits) {
        if (edits > 2) return false;
        if (i == q.length()) return end[node];
        for (int c = 0; c < 26; c++) { // branch on all 26 letters
            if (trie[node][c] == 0) continue;
            int nextEdits = edits + (c == q.charAt(i) - 'a' ? 0 : 1);
            if (dfs(trie, end, q, trie[node][c], i + 1, nextEdits)) return true;
        }
        return false;
    }
}
