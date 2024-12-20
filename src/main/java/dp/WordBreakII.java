package dp;

import struct.TrieNode;

import java.util.*;

/**
 * LeetCode 140, hard, tags: array, hash table, string, dynamic programming, backtracking, trie, memoization.
 * <p>
 * Given a string s and a dictionary of strings wordDict, add spaces in s to construct a sentence where each word is
 * a valid dictionary word. Return all such possible sentences in any order.
 * <p>
 * Note that the same word in the dictionary may be reused multiple times in the segmentation.
 * <p>
 * Example 1:
 * <p>
 * Input: s = "catsanddog", wordDict = ["cat","cats","and","sand","dog"]
 * Output: ["cats and dog","cat sand dog"]
 * Example 2:
 * <p>
 * Input: s = "pineapplepenapple", wordDict = ["apple","pen","applepen","pine","pineapple"]
 * Output: ["pine apple pen apple","pineapple pen apple","pine applepen apple"]
 * Explanation: Note that you are allowed to reuse a dictionary word.
 * Example 3:
 * <p>
 * Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
 * Output: []
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 20, N
 * 1 <= wordDict.length <= 1000, M
 * 1 <= wordDict[i].length <= 10, K
 * s and wordDict[i] consist of only lowercase English letters.
 * All the strings of wordDict are unique.
 * Input is generated in a way that the length of the answer doesn't exceed 105.
 */
public class WordBreakII {
    int maxLen = 0;
    Set<String> d; // dict of valid words
    String s;
    Map<Integer, List<String>> cache;

    // https://leetcode.com/problems/word-break-ii/discuss/44167/My-concise-JAVA-solution-based-on-memorized-DFS/215095
    // These are all good approaches when not all combinations are valid, but won't change the time complexity O(2^n)
    // in the worse case scenario where all combinations of the string are correct (e,g, s=aaa, dic=[a, aa, aaa]).
    // Some might argue that they reduce the number of recursive/iterative calls to n^2 using memo or DP just like
    // word break I. However, the time complexity of each recursive call in this approach is not linear anymore.
    // Imagine the length of sublist is 2^(n-1). Optimization only happens when the return value is an integer or
    // boolean. This is why we don't use DP/memo to solve subsets/permutation problem because all combinations are
    // valid. The code below combines (1) and (4) and beats 99% as the solution above suffers the problem that
    // the dictionary size might be too large.
    // 4ms, 40.9Mb, O(N*2^N) time, O(max(N,MK)+N*2^N(recurse)) space.
    public List<String> wordBreak(String s, List<String> wordDict) {
        d = new HashSet<>();
        this.s = s;
        for (String w : wordDict) {
            d.add(w);
            if (w.length() > maxLen) maxLen = w.length();
        }
        cache = new HashMap<>(); // index -> result list
        return dfs(0);
    }

    public List<String> dfs(int start) {
        if (cache.containsKey(start)) return cache.get(start);
        List<String> res = new ArrayList<>();
        if (start == s.length()) res.add("");
        for (int i = start + 1; i <= start + maxLen && i <= s.length(); i++) { // reduce # iterations using maxLen
            String w = s.substring(start, i);// the next word [start,i)
            if (d.contains(w)) {
                List<String> next = dfs(i);
                for (String sn : next) {
                    if (sn.isEmpty()) res.add(w); // reached the end
                    else res.add(w + " " + sn);
                }
            }
        }
        cache.put(start, res);
        return res;
    }

    // solution 2, trie and iterate from end optimization. 5ms, 41.32Mb. same complexity.
    static class Solution {
        public List<String> wordBreak(String s, List<String> wordDict) {
            TrieNode root = new TrieNode();
            for (String word : wordDict) root.insert(word);
            Map<Integer, List<String>> cache = new HashMap<>();

            // Iterate from the end of the string to the beginning, no need to recurse
            for (int start = s.length() - 1; start >= 0; start--) {
                List<String> sentences = new ArrayList<>();
                TrieNode cur = root;
                for (int end = start; end < s.length(); end++) {
                    char c = s.charAt(end);
                    int index = c - 'a';
                    // Check if the current character exists in the trie
                    if (cur.next[index] == null) break;
                    cur = cur.next[index];
                    if (cur.isWord) {
                        String cw = s.substring(start, end + 1); // current word
                        // if it's the last word, add it as a valid sentence
                        if (end == s.length() - 1) sentences.add(cw);
                        else {
                            // append it to each sentence formed by the remaining substring
                            List<String> next = cache.get(end + 1); // scanned backward, so this will not be null
                            for (String sentence : next) sentences.add(cw + " " + sentence);
                        }
                    }
                }
                cache.put(start, sentences);
            }
            return cache.getOrDefault(0, new ArrayList<>());
        }
    }
}
