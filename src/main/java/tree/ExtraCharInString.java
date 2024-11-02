package tree;

import struct.TrieNode;

import static struct.TrieNode.lce26;

/**
 * LeetCode 2707, medium, tags: array, hash table, string, dp, trie.
 * <p>
 * You are given a 0-indexed string s and a dictionary of words dictionary. You have to break s into one or more non-overlapping substrings such that each substring is present in dictionary. There may be some extra characters in s which are not present in any of the substrings.
 * <p>
 * Return the minimum number of extra characters left over if you break up s optimally.
 * <p>
 * Example 1:
 * <p>
 * Input: s = "leetscode", dictionary = ["leet","code","leetcode"]
 * Output: 1
 * Explanation: We can break s in two substrings: "leet" from index 0 to 3 and "code" from index 5 to 8. There is only 1 unused character (at index 4), so we return 1.
 * <p>
 * Example 2:
 * <p>
 * Input: s = "sayhelloworld", dictionary = ["hello","world"]
 * Output: 3
 * Explanation: We can break s in two substrings: "hello" from index 3 to 7 and "world" from index 8 to 12. The characters at indices 0, 1, 2 are not used in any substring and thus are considered as extra characters. Hence, we return 3.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 50
 * 1 <= dictionary.length <= 50
 * 1 <= dictionary[i].length <= 50
 * dictionary[i] and s consists of only lowercase English letters
 * dictionary contains distinct words
 * <p>
 * Hint 1
 * Can we use Dynamic Programming here?
 * Hint 2
 * Define DP[i] as the min extra character if breaking up s[0:i] optimally.
 */
@SuppressWarnings("unused")
public class ExtraCharInString {

    // solution 1, trie search backwards, n^2 time, n+mk space. with hashset same space, n^3 time. 9ms, 45.4mb.
    // can also recursive and search forward
    static class Solution {
        public int minExtraChar(String s, String[] dictionary) {
            int n = s.length();
            var root = new TrieNode();
            for (String w : dictionary) root.insert(w);
            var dp = new int[n + 1]; // note n+1, index->res: min number of extra char
            // base case dp[n] empty string: 0
            for (int start = n - 1; start >= 0; start--) {
                dp[start] = dp[start + 1] + 1; // assume charAt(start) not in dictionary
                var cur = root;
                for (int end = start; end < n; end++) {
                    int id = lce26.apply(s.charAt(end));
                    if (cur.next[id] == null) break; // not in dictionary, give up, try start--
                    cur = cur.next[id];
                    // s[start,end] is in dictionary
                    // dp[start] = min(dp[start], dp[end+1]), dp[end+1] for s[end+1,n-1]
                    if (cur.isWord) dp[start] = Math.min(dp[start], dp[end + 1]);
                }
            }
            return dp[0];
        }
    }
}
