package string;

import struct.TrieNode26;

import static struct.TrieNode.lce26;

/**
 * LeetCode 2416, hard, tags: array, string, trie, counting.
 * <p>
 * You are given an array words of size n consisting of non-empty strings.
 * <p>
 * We define the score of a string word as the number of strings words[i] such that word is a prefix of words[i].
 * <p>
 * For example, if words = ["a", "ab", "abc", "cab"], then the score of "ab" is 2, since "ab" is a prefix of
 * both "ab" and "abc".
 * Return an array answer of size n where answer[i] is the sum of scores of every non-empty prefix of words[i].
 * <p>
 * Note that a string is considered as a prefix of itself.
 * <p>
 * Example 1:
 * <p>
 * Input: words = ["abc","ab","bc","b"]
 * Output: [5,4,3,2]
 * Explanation: The answer for each string is the following:
 * - "abc" has 3 prefixes: "a", "ab", and "abc".
 * - There are 2 strings with the prefix "a", 2 strings with the prefix "ab", and 1 string with the prefix "abc".
 * The total is answer[0] = 2 + 2 + 1 = 5.
 * - "ab" has 2 prefixes: "a" and "ab".
 * - There are 2 strings with the prefix "a", and 2 strings with the prefix "ab".
 * The total is answer[1] = 2 + 2 = 4.
 * - "bc" has 2 prefixes: "b" and "bc".
 * - There are 2 strings with the prefix "b", and 1 string with the prefix "bc".
 * The total is answer[2] = 2 + 1 = 3.
 * - "b" has 1 prefix: "b".
 * - There are 2 strings with the prefix "b".
 * The total is answer[3] = 2.
 * Example 2:
 * <p>
 * Input: words = ["abcd"]
 * Output: [4]
 * Explanation:
 * "abcd" has 4 prefixes: "a", "ab", "abc", and "abcd".
 * Each prefix has a score of one, so the total is answer[0] = 1 + 1 + 1 + 1 = 4.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= words.length <= 1000, n
 * 1 <= words[i].length <= 1000, m
 * words[i] consists of lowercase English letters.
 */
@SuppressWarnings("unused")
public class SumPrefixScore {
    // O(nm) time and space. 348ms, 187mb.
    static class Solution {
        TrieNode26 root = new TrieNode26();

        int count(String s) {
            TrieNode26 cur = root;
            int res = 0;
            // The res would store the total sum of counts.
            for (char c : s.toCharArray()) {
                int id = lce26.apply(c);
                cur = cur.next[id];
                res += cur.cnt;
            }
            return res;
        }

        public int[] sumPrefixScores(String[] words) {
            int n = words.length;
            for (String word : words) root.insert(word);
            int[] scores = new int[n];
            for (int i = 0; i < n; i++) scores[i] = count(words[i]);
            return scores;
        }
    }
}
