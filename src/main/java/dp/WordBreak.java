package dp;

import struct.TrieNode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * LeetCode 139, medium, tags: dynamic programming, hash table, string, trie, memoization.
 * <p>
 * Given a string s and a dictionary of strings wordDict, return true if s can be segmented into
 * a space-separated sequence of one or more dictionary words.
 * <p>
 * Note that the same word in the dictionary may be reused multiple times in the segmentation.
 * <p>
 * Example 1:
 * <p>
 * Input: s = "leetcode", wordDict = ["leet","code"]
 * Output: true
 * Explanation: Return true because "leetcode" can be segmented as "leet code".
 * Example 2:
 * <p>
 * Input: s = "applepenapple", wordDict = ["apple","pen"]
 * Output: true
 * Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
 * Note that you are allowed to reuse a dictionary word.
 * Example 3:
 * <p>
 * Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
 * Output: false
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 300, N characters
 * 1 <= wordDict.length <= 1000, M words
 * 1 <= wordDict[i].length <= 20, average length K characters
 * s and wordDict[i] consist of only lowercase English letters.
 * All the strings of wordDict are unique.
 */
public class WordBreak {
    // 7ms, 42.5 Mb. O(N^2*K) time, O(max(N,MK)) space.
    public boolean wordBreakDPSet(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];// O(N) space, indicating word in string s ending at this index
        dp[0] = true;
        Set<String> wordSet = new HashSet<>(wordDict); // O(M*K) space.
        for (int i = 1; i <= s.length(); i++) {// O(N)
            for (int j = 0; j < i; j++) { // O(N)
                if (dp[j] && wordSet.contains(s.substring(j, i))) { // O(K)
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }


    // 2ms, 42.4 Mb. O(N^2*K) time, O(max(N,MK)) space.
    public boolean wordBreakTrie(String s, List<String> wordDict) {
        final int R = 26; // only lower case letters
        TrieNode root = new TrieNode(R);
        for (String word : wordDict) root.addWord(word, c1 -> c1 - 'a'); // O(M*K) space.
        boolean[] f = new boolean[s.length() + 1]; // O(N) space
        f[s.length()] = true;
        for (int i = s.length() - 1; i >= 0; i--) {
            TrieNode cur = root;
            for (int j = i; cur != null && j < s.length(); j++) {
                cur = cur.next[s.charAt(j) - 'a'];
                if (f[j + 1] && cur != null && cur.isWord) {
                    f[i] = true;
                    break;
                }
            }
        }
        return f[0];
    }

    // 9ms, 42.7Mb. O(max(N,MK)) space. O(V+E), V << E, O(N^2*K) time.
    public boolean wordBreakBFS(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet<>(wordDict);
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[s.length()];
        queue.add(0);
        while (!queue.isEmpty()) {//O(N)
            int start = queue.remove();
            if (visited[start]) continue;
            for (int end = start + 1; end <= s.length(); end++) {// O(N)
                if (wordDictSet.contains(s.substring(start, end))) {// O(K)
                    queue.add(end);
                    if (end == s.length()) return true;
                }
            }
            visited[start] = true;
        }
        return false;
    }

    public boolean wordBreak1(String s, List<String> wordDict) {
        return wordBreakRecur(s, new HashSet<>(wordDict), 0);
    }

    // T(n) = 2T(n - k) + k, O(2^n) time, O(max(N,MK)) space.. Time limit exceeded.
    private boolean wordBreakRecur(String s, Set<String> wordDict, int start) {
        if (start == s.length()) return true;
        for (int end = start + 1; end <= s.length(); end++)
            if (wordDict.contains(s.substring(start, end)) && wordBreakRecur(s, wordDict, end)) return true;
        return false;
    }

    // 6ms, 42.5 Mb. O(2^n) time, O(max(N,MK)) space.
    public boolean wordBreak2(String s, List<String> wordDict) {
        return wordBreakMemo(s, new HashSet<>(wordDict), 0, new Boolean[s.length()]);
    }

    private boolean wordBreakMemo(String s, Set<String> wordDict, int start, Boolean[] memo) {
        if (start == s.length()) return true;
        if (memo[start] != null) return memo[start];
        for (int end = start + 1; end <= s.length(); end++)
            if (wordDict.contains(s.substring(start, end)) && wordBreakMemo(s, wordDict, end, memo))
                return memo[start] = true;

        return memo[start] = false;
    }

}
