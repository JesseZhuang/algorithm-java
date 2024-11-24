package string;

import struct.TrieNode26;

import java.util.HashSet;
import java.util.Set;

/**
 * LeetCode 1698, LintCode 3717, medium, tags: trie, string, suffix array, hash function, rolling hash.
 * <p>
 * Given a string s, return the number of distinct substrings of s.
 * <p>
 * A substring of a string is obtained by deleting any number of characters (possibly zero) from the front of the
 * string and any number (possibly zero) from the back of the string.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "aabbaba"
 * Output: 21
 * Explanation: The set of distinct strings is ["a","b","aa","bb","ab","ba","aab","abb","bab","bba","aba","aabb",
 * "abba","bbab","baba","aabba","abbab","bbaba","aabbab","abbaba","aabbaba"]
 * Example 2:
 * <p>
 * Input: s = "abcdefg"
 * Output: 28
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 500, n
 * s consists of lowercase English letters.
 * <p>
 * Follow up: Can you solve this problem in O(n) time complexity?
 * Talk about:
 * 1. O(nlgn) time solution using suffix arrays
 * 2. O(n) solution using suffix tree (Ukkonen's algorithm) or a suffix automaton.
 * <p>
 * References:
 * 1. CP-algorithms, string hashing and suffix array
 * 2. Dmitry Babichev for python version of rabin karp and suffix arrays.
 */
@SuppressWarnings("unused")
public class NDistinctSS {
    // solution 1, trie, n^2 time and space. 242ms, 24.70mb.
    static class Solution1 {
        public int countDistinctSubstrings(String s) {
            TrieNode26 root = new TrieNode26();
            TrieNode26 cur;
            int count = 0;
            for (int i = 0; i < s.length(); i++) {
                cur = root;
                for (int j = i; j < s.length(); j++) {
                    if (cur.next[s.charAt(j) - 'a'] == null) {
                        cur.next[s.charAt(j) - 'a'] = new TrieNode26();
                        count++;
                    }
                    cur = cur.next[s.charAt(j) - 'a'];
                }
            }
            return count;
        }
    }

    // string hashing, n^2 time and space. 252ms, 20.60mb. rabin karp is similar to this one.
    static class Solution3 {
        public int countDistinctSubstrings(String s) {
            RollingHash rl = new RollingHash(s);
            int res = 0;
            for (int l = 1; l <= rl.n; l++) { // length of the substring
                Set<Long> hs = new HashSet<>(); // only need to make sure the same length substrings are unique
                for (int i = 0; i <= rl.n - l; i++) {
                    long hash = rl.substringHash(s, i, i + l - 1);
                    hs.add(hash);
                }
                res += hs.size();
            }
            return res;
        }
    }
}
