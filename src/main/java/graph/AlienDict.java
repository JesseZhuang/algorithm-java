package graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * LeetCode 269 (premium), hard, GFG, tags: bfs, topological sort, graph, string.
 * Given a sorted dictionary of an alien language having N words and k starting alphabets of standard dictionary.
 * Find the order of characters in the alien language.
 * Note: Many orders may be possible for a particular test case, thus you may return any valid order and output
 * will be 1 if the order of string returned by the function is correct else 0 denoting incorrect string returned.
 * <p>
 * Example 1:
 * <p>
 * Input:
 * N = 5, K = 4
 * dict = {"baa","abcd","abca","cab","cad"}
 * Output: 1
 * Explanation:
 * Here order of characters is
 * 'b', 'd', 'a', 'c' Note that words are sorted
 * and in the given language "baa" comes before
 * "abcd", therefore 'b' is before 'a' in output.
 * Similarly, we can find other orders.
 * Example 2:
 * <p>
 * Input:
 * N = 3, K = 3
 * dict = {"caa","aaa","aab"}
 * Output:
 * 1
 * Explanation:
 * Here order of characters is
 * 'c', 'a', 'b' Note that words are sorted
 * and in the given language "caa" comes before
 * "aaa", therefore 'c' is before 'a' in output.
 * Similarly, we can find other orders.
 * <p>
 * Your Task:
 * You don't need to read or print anything. Your task is to complete the function findOrder() which takes
 * the string array dict[], its size N and the integer K as input parameter and returns a string denoting
 * the order of characters in the alien language.
 * <p>
 * Expected Time Complexity: O(N * |S| + K) , where |S| denotes maximum length.
 * Expected Space Complexity: O(K)
 * <p>
 * Constraints:
 * 1 ≤ N ≤ 300
 * 1 ≤ K ≤ 26
 * 1 ≤ Length of words ≤ 50
 * <p>
 * lint code 892
 * There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you.
 * You receive a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules
 * of this new language. Derive the order of letters in this language.
 * <p>
 * You may assume all letters are in lowercase.
 * The dictionary is invalid, if string 'a' is prefix of string b and b is appeared before 'a'.
 * If the order is invalid, return an empty string.
 * There may be multiple valid order of letters, return the smallest in normal lexicographical order.
 * The letters in one string are of the same rank by default and are sorted in Human dictionary order.
 * Example 1:
 * <p>
 * Input：["wrt","wrf","er","ett","rftt"]
 * Output："wertf"
 * Explanation：
 * from "wrt"and"wrf" ,we can get 't'<'f'
 * from "wrt"and"er" ,we can get 'w'<'e'
 * from "er"and"ett" ,we can get 'r'<'t'
 * from "ett"and"rftt" ,we can get 'e'<'r'
 * So return "wertf"
 * Example 2:
 * <p>
 * Input：["z","x"]
 * Output："zx"
 * Explanation：
 * from "z" and "x"，we can get 'z' < 'x'
 * So return "zx"
 */
public class AlienDict {
    public String findOrder(String[] dict, int N, int K) {
        return null;
    }

    public String alienOrder(String[] words) {
        StringBuilder sb = new StringBuilder();
        Map<Character, Set<Character>> adj = new HashMap<>();
        for (int i = 0; i <= words.length - 2; i++) {
            String cw = words[i];
            String nw = words[i + 1];
            for (int j = 0; j < words[i].length(); j++) {
                if (j >= words[i + 1].length()) return "";
                if (cw.charAt(j) == nw.charAt(j)) continue;
                adj.putIfAbsent(cw.charAt(j), new HashSet<>());
                adj.get(cw.charAt(j)).add(nw.charAt(j));
            }
        }
        return sb.reverse().toString();
    }


}
