package string;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * LeetCode 854, LintCode 1490, hard, tags: string, bfs.
 * Companies: Google.
 * <p>
 * Strings s1 and s2 are k-similar (for some non-negative integer k) if we can swap the positions of two letters in
 * s1 exactly k times so that the resulting string equals s2.
 * <p>
 * Given two anagrams s1 and s2, return the smallest k for which s1 and s2 are k-similar.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s1 = "ab", s2 = "ba"
 * Output: 1
 * Explanation: The two string are 1-similar because we can use one swap to change s1 to s2: "ab" --> "ba".
 * Example 2:
 * <p>
 * Input: s1 = "abc", s2 = "bca"
 * Output: 2
 * Explanation: The two strings are 2-similar because we can use two swaps to change s1 to s2:
 * "abc" --> "bac" --> "bca".
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s1.length <= 20
 * s2.length == s1.length
 * s1 and s2 contain only lowercase letters from the set {'a', 'b', 'c', 'd', 'e', 'f'}.
 * s2 is an anagram of s1.
 */
@SuppressWarnings("unused")
public class KSimilarString {
    // @caraxin, todo
    static class Solution {
        public int kSimilarity(String A, String B) {
            if (A.equals(B)) return 0;
            Set<String> vis = new HashSet<>();
            Queue<String> q = new LinkedList<>();
            q.add(A);
            vis.add(A);
            int res = 0;
            while (!q.isEmpty()) {
                res++;
                for (int sz = q.size(); sz > 0; sz--) {
                    String s = q.poll();
                    int i = 0;
                    while (s.charAt(i) == B.charAt(i)) i++;
                    for (int j = i + 1; j < s.length(); j++) {
                        if (s.charAt(j) == B.charAt(j) || s.charAt(j) != B.charAt(i)) continue;
                        String temp = swap(s, i, j);
                        if (temp.equals(B)) return res;
                        if (vis.add(temp)) q.add(temp);
                    }
                }
            }
            return res;
        }

        public String swap(String s, int i, int j) {
            char[] ca = s.toCharArray();
            char temp = ca[i];
            ca[i] = ca[j];
            ca[j] = temp;
            return new String(ca);
        }
    }
}
