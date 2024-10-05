package string;

import java.util.Arrays;
import java.util.HashMap;

/**
 * LeetCode 567, medium, tags: hash table, two pointers, string, sliding window.
 * <p>
 * Given two strings s1 and s2, return true if s2 contains a
 * permutation
 * of s1, or false otherwise.
 * <p>
 * In other words, return true if one of s1's permutations is the substring of s2.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s1 = "ab", s2 = "eidbaooo"
 * Output: true
 * Explanation: s2 contains one permutation of s1 ("ba").
 * Example 2:
 * <p>
 * Input: s1 = "ab", s2 = "eidboaoo"
 * Output: false
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s1.length, s2.length <= 10^4: l1, l2
 * s1 and s2 consist of lowercase English letters.
 * <p>
 * Obviously, brute force will result in TLE. Think of something else.
 * Hint 2
 * How will you check whether one string is a permutation of another string?
 * Hint 3
 * One way is to sort the string and then compare. But, Is there a better way?
 * Hint 4
 * If one string is a permutation of another string then they must have one common metric. What is that?
 * Hint 5
 * Both strings must have same character frequencies, if one is permutation of another. Which data structure
 * should be used to store frequencies?
 * Hint 6
 * What about hash table? An array of size 26?
 */
@SuppressWarnings("unused")
public class PermutationInString {
    // optimized sliding window, 26(l2-l1)+l1: l2, 26+26:1. 6ms, 42.48mb.
    static class SolutionSW {
        public boolean checkInclusion(String s1, String s2) {
            int l1 = s1.length(), l2 = s2.length();
            if (l1 > l2) return false;
            int[] c1 = new int[26], c2 = new int[26]; // char count
            for (int i = 0; i < l1; i++) { // look at [0,l1)
                c1[s1.charAt(i) - 'a']++;
                c2[s2.charAt(i) - 'a']++;
            }
            int count = 0; // matched count in s1 and window of length of l1 in s2
            for (int i = 0; i < 26; i++) if (c1[i] == c2[i]) count++;
            // sliding [l1,l2)
            for (int i = 0; i < l2 - l1; i++) {
                if (count == 26) return true;
                int r = s2.charAt(i + l1) - 'a', l = s2.charAt(i) - 'a';
                c2[r]++;
                if (c2[r] == c1[r]) count++;
                else if (c2[r] == c1[r] + 1) count--;
                c2[l]--;
                if (c2[l] == c1[l]) count++;
                else if (c2[l] == c1[l] - 1) count--;
            }
            return count == 26;
        }
    }

    // map, O(l1+(26+l1)*(l2-l1)), O(l2-l1). 832ms, 44.87mb.
    static class SolutionMap {
        public boolean checkInclusion(String s1, String s2) {
            if (s1.length() > s2.length()) return false;
            HashMap<Character, Integer> s1map = new HashMap<>();
            for (int i = 0; i < s1.length(); i++)
                s1map.put(s1.charAt(i), s1map.getOrDefault(s1.charAt(i), 0) + 1);
            for (int i = 0; i <= s2.length() - s1.length(); i++) {
                HashMap<Character, Integer> s2map = new HashMap<>();
                for (int j = 0; j < s1.length(); j++)
                    s2map.put(s2.charAt(i + j), s2map.getOrDefault(s2.charAt(i + j), 0) + 1);
                if (matches(s1map, s2map)) return true;
            }
            return false;
        }

        public boolean matches(HashMap<Character, Integer> s1map, HashMap<Character, Integer> s2map) {
            for (char key : s1map.keySet()) // guaranteed key in s1map
                if (!s1map.get(key).equals(s2map.get(key))) return false; // do not compare objects with === !!!
            return true;
        }
    }

    // sort, O((l2-l1)l1lgl1), O(l1+sort).
    static class Solution {
        public boolean checkInclusion(String s1, String s2) {
            s1 = sort(s1);
            for (int i = 0; i <= s2.length() - s1.length(); i++)
                if (s1.equals(sort(s2.substring(i, i + s1.length())))) return true;
            return false;
        }

        public String sort(String s) {
            char[] t = s.toCharArray();
            Arrays.sort(t);
            return new String(t);
        }
    }
}
