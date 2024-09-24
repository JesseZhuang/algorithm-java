package tree;

import java.util.HashSet;

/**
 * LeetCode 3043, medium, tags: array, hash table, trie, string.
 * <p>
 * You are given two arrays with positive integers arr1 and arr2.
 * <p>
 * A prefix of a positive integer is an integer formed by one or more of its digits, starting from its leftmost digit.
 * For example, 123 is a prefix of the integer 12345, while 234 is not.
 * <p>
 * A common prefix of two integers a and b is an integer c, such that c is a prefix of both a and b. For example,
 * 5655359 and 56554 have a common prefix 565 while 1223 and 43456 do not have a common prefix.
 * <p>
 * You need to find the length of the longest common prefix between all pairs of integers (x, y) such that x
 * belongs to arr1 and y belongs to arr2.
 * <p>
 * Return the length of the longest common prefix among all pairs. If no common prefix exists among them, return 0.
 * <p>
 * Example 1:
 * <p>
 * Input: arr1 = [1,10,100], arr2 = [1000]
 * Output: 3
 * Explanation: There are 3 pairs (arr1[i], arr2[j]):
 * - The longest common prefix of (1, 1000) is 1.
 * - The longest common prefix of (10, 1000) is 10.
 * - The longest common prefix of (100, 1000) is 100.
 * The longest common prefix is 100 with a length of 3.
 * Example 2:
 * <p>
 * Input: arr1 = [1,2,3], arr2 = [4,4,4]
 * Output: 0
 * Explanation: There exists no common prefix for any pair (arr1[i], arr2[j]), hence we return 0.
 * Note that common prefixes between elements of the same array do not count.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= arr1.length, arr2.length <= 5 * 10^4, m,n. M=max(arr1), N=max(arr2).
 * 1 <= arr1[i], arr2[i] <= 10^8
 * <p>
 * Hint1
 * Put all the possible prefixes of each element in arr1 into a HashSet.
 * Hint 2
 * For all the possible prefixes of each element in arr2, check if it exists in the HashSet.
 */
@SuppressWarnings("unused")
public class FindLenLCP {

    // solution 1, trie, same complexity. 34ms, 55.25mb.
    static class Solution1 {
        public int longestCommonPrefix(int[] arr1, int[] arr2) {
            Trie trie = new Trie();
            for (int num : arr1) trie.insert(num);
            int res = 0;
            for (int num : arr2) {
                int len = trie.findLongestPrefix(num);
                res = Math.max(res, len);
            }
            return res;
        }

        static class TrieNode {
            // Each node has up to 10 possible children (digits 0-9)
            TrieNode[] next = new TrieNode[10];
        }

        static class Trie {
            TrieNode root = new TrieNode();

            // Insert a number into the Trie by treating it as a string of digits
            void insert(int num) {
                TrieNode node = root;
                String numStr = Integer.toString(num);
                for (char digit : numStr.toCharArray()) {
                    int id = digit - '0';
                    if (node.next[id] == null) node.next[id] = new TrieNode();
                    node = node.next[id];
                }
            }

            int findLongestPrefix(int num) {
                TrieNode node = root;
                String numStr = Integer.toString(num);
                int len = 0;
                for (char digit : numStr.toCharArray()) {
                    int id = digit - '0';
                    if (node.next[id] == null) break;
                    len++;
                    node = node.next[id];
                }
                return len;
            }
        }
    }

    // solution 2, hashset, mlog_10M+nlog_10N time, mlog_10M space.
    static class Solution2 {
        public int longestCommonPrefix(int[] arr1, int[] arr2) {
            HashSet<Integer> arr1Prefix = new HashSet<>(); // prefixes from arr1
            for (int val : arr1) {
                while (!arr1Prefix.contains(val) && val > 0) {
                    arr1Prefix.add(val);
                    val /= 10; // Generate the next shorter prefix by removing the last digit
                }
            }
            int res = 0;
            for (int val : arr2) {
                // removing the last digit if not found in the prefix set
                while (!arr1Prefix.contains(val) && val > 0) val /= 10;
                if (val > 0) // Length of the matched prefix using log10 to determine the number of digits
                    res = Math.max(res, (int) Math.log10(val) + 1); // 10->2 digits
            }
            return res;
        }
    }
}
