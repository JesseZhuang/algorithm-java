package dp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

    // https://leetcode.com/problems/word-break-ii/discuss/44167/My-concise-JAVA-solution-based-on-memorized-DFS/215095
    // These are all good approaches when not all combinations are valid, but won't change the time complexity O(2^n)
    // in the worse case scenario where all combinations of the string are correct (e,g, s=aaa, dic=[a, aa, aaa]).
    // Some might argue that they reduce the number of recursive/iterative calls to n^2 using memo or DP just like
    // word break I. However, the time complexity of each recursive call in this approach is not linear anymore.
    // Imagine the length of sublist is 2^(n-1). Optimization only happens when the return value is a integer or
    // boolean. This is why we don't use DP/memo to solve subsets/permutation problem because all combinations are
    // valid. The code below combines (1) and (4) and beats 99% as the solution above suffers the problem that
    // the dictionary size might be too large.
    // 4ms, 40.9Mb, O(2^n) time, O(max(N,MK)) space.
    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> hs = new HashSet<>();
        for (String w : wordDict) {
            hs.add(w);
            if (w.length() > maxLen) maxLen = w.length();
        }
        Map<Integer, List<String>> map = new HashMap<>(); // index -> result list
        return helper(hs, s, 0, map);
    }

    public List<String> helper(Set<String> hs, String s, int start, Map<Integer, List<String>> map) {
        if (map.containsKey(start)) return map.get(start);
        List<String> list = new ArrayList<>();
        if (start == s.length()) list.add("");
        for (int i = start; i < start + maxLen && i < s.length(); i++) { // reduce # iterations using maxLen
            if (hs.contains(s.substring(start, i + 1))) {
                List<String> nexts = helper(hs, s, i + 1, map);
                for (String next : nexts) {
                    if (next == "") list.add(s.substring(start, i + 1) + next); // reaches the end
                    else list.add(s.substring(start, i + 1) + " " + next);
                }
            }
        }
        map.put(start, list);
        return list;
    }
}
