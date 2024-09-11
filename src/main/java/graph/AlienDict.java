package graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * LeetCode 269 (premium), LintCode 892, hard, GFG, tags: bfs, dfs, topological sort, graph, array, string.
 * <p>
 * There is a new alien language that uses the English alphabet. However, the order of the letters is unknown to you.
 * <p>
 * You are given a list of strings words from the alien language's dictionary. Now it is claimed that the strings
 * in words are sorted lexicographically by the rules of this new language.
 * <p>
 * If this claim is incorrect, and the given arrangement of string in words cannot correspond to any order of
 * letters, return "".
 * <p>
 * Otherwise, return a string of the unique letters in the new alien language sorted in lexicographically
 * increasing order by the new language's rules. If there are multiple solutions, return any of them.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: words = ["wrt","wrf","er","ett","rftt"]
 * Output: "wertf"
 * Example 2:
 * <p>
 * Input: words = ["z","x"]
 * Output: "zx"
 * Example 3:
 * <p>
 * Input: words = ["z","x","z"]
 * Output: ""
 * Explanation: The order is invalid, so return "".
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= words.length <= 100
 * 1 <= words[i].length <= 100
 * words[i] consists of only lowercase English letters.
 *
 * <p>
 * GFG
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
 * <p>
 * Note:
 * <p>
 * You may assume all letters are in lowercase.
 * You may assume that if a is a prefix of b, then a must appear before b in the given dictionary.
 * If the order is invalid, return an empty string.
 * There may be multiple valid order of letters, return any one of them is fine.
 * <p>
 * Constraints:
 * <p>
 * 1 <= words.length <= 100
 * 1 <= words[i].length <= 100
 * words[i] consists of only lowercase English letters.
 * <p>
 * <p>
 * GeekForGeeks or some other website constraints:
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
 * LintCode 892.
 * There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you.
 * You receive a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules
 * of this new language. Derive the order of letters in this language.
 * <p>
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
 * <p>
 * Constraints:
 * <p>
 * You may assume all letters are in lowercase.
 * The dictionary is invalid, if string a is prefix of string b and b is appear before a.
 * If the order is invalid, return an empty string.
 * There may be multiple valid order of letters, return the smallest in normal lexicographical order.
 * The letters in one string are of the same rank by default and are sorted in Human dictionary order.
 */
public class AlienDict {

    public static void main(String[] args) {
        AlienDict t = new AlienDict();
//        System.out.println(t.alienOrderBFS(new String[]{"wrt", "wrf", "er", "ett", "rftt"})); // expected wertf
//        System.out.println(t.alienOrderBFS(new String[]{"z", "x"})); // expected zx
        System.out.println(t.alienOrderBFS(new String[]{"ab", "ac"})); // expected abc
    }

    // solution 1, O(nlgn) time, O(n) space. lint code, 271ms, 21.76 Mb.
    // leet code does not require return the smallest in normal lexicographical order. O(n) time.
    public String alienOrderBFS(String[] words) {
        return topological(buildGraph(words));
    }

    // O(n) time and space.
    private Map<Character, Set<Character>> buildGraph(String[] words) {
        Map<Character, Set<Character>> res = new HashMap<>();
        for (String w : words) //  ["ab", "ac"], create "a" anyway even no edge
            for (char j : w.toCharArray()) res.putIfAbsent(j, new HashSet<>());
        for (int i = 1; i < words.length; i++) {
            int j = 0;
            String a = words[i - 1], b = words[i];
            while (j < a.length() && j < b.length() && a.charAt(j) == b.charAt(j)) j++;
            if (j == a.length()) continue; // ab, abc
            if (j >= b.length()) return null; // exclude {"abc", "ab"}: prefix constraint
            res.get(a.charAt(j)).add(b.charAt(j));
        }
        return res;
    }

    // nlgn time because of priority queue, smallest lexicographical if multiple
    private String topological(Map<Character, Set<Character>> graph) {
        if (graph == null) return "";
        Map<Character, Integer> inDegree = new HashMap<>();
        for (char c : graph.keySet()) inDegree.put(c, 0);
        for (char v : graph.keySet())
            for (char w : graph.get(v)) inDegree.put(w, inDegree.get(w) + 1);
        Queue<Character> q = new PriorityQueue<>(); // sort by natural order, lexicographical
        for (Map.Entry<Character, Integer> entry : inDegree.entrySet())
            if (entry.getValue() == 0) q.add(entry.getKey());
        StringBuilder res = new StringBuilder();
        int count = 0;
        while (!q.isEmpty()) {
            char v = q.remove();
            res.append(v);
            count++;
            for (char w : graph.get(v)) {
                int degree = inDegree.get(w) - 1;
                if (degree == 0) q.add(w);
                inDegree.put(w, degree);
            }
        }
        if (count != graph.size()) return "";
        return res.toString();
    }
}
