package dp;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 131, medium, tags: string, dynamic programming, backtracking.
 * <p>
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * Return all possible palindrome partitioning of s.
 * <p>
 * Example 1:
 * <p>
 * Input: s = "aab"
 * Output: [["a","a","b"],["aa","b"]]
 * Example 2:
 * <p>
 * Input: s = "a"
 * Output: [["a"]]
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 16
 * s contains only lowercase English letters.
 */
public class PalindromePartitioning {

    // 10ms, 55.3 Mb. O(N*2^N) time, O(N) space (recursion stack)
    public List<List<String>> partitionDFS(String s) {
        List<List<String>> result = new ArrayList<>();
        dfs(0, result, new ArrayList<>(), s);
        return result;
    }

    void dfs(int start, List<List<String>> result, List<String> currentList, String s) {
        if (start >= s.length()) result.add(new ArrayList<>(currentList)); // e.g., add [a,a,b]
        for (int end = start; end < s.length(); end++) {
            if (isPalindrome(s, start, end)) {
                // add current substring in the currentList
                currentList.add(s.substring(start, end + 1));
                dfs(end + 1, result, currentList, s);
                // backtrack and remove the current substring from currentList
                currentList.remove(currentList.size() - 1);
            }
        }
    }

    boolean isPalindrome(String s, int low, int high) {
        while (low < high)
            if (s.charAt(low++) != s.charAt(high--)) return false;
        return true;
    }
}
