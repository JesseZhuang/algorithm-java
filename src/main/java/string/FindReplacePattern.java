package string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LeetCode 890, LintCode 1592, medium, tags: array, hash table, string.
 * <p>
 * Given a list of strings words and a string pattern, return a list of words[i] that match pattern. You may return
 * the answer in any order.
 * <p>
 * A word matches the pattern if there exists a permutation of letters p so that after replacing every letter x in
 * the pattern with p(x), we get the desired word.
 * <p>
 * Recall that a permutation of letters is a bijection from letters to letters: every letter maps to another letter,
 * and no two letters map to the same letter.
 * <p>
 * Example 1:
 * <p>
 * Input: words = ["abc","deq","mee","aqq","dkd","ccc"], pattern = "abb"
 * Output: ["mee","aqq"]
 * Explanation: "mee" matches the pattern because there is a permutation {a -> m, b -> e, ...}.
 * "ccc" does not match the pattern because {a -> c, b -> c, ...} is not a permutation, since a and b map
 * to the same letter.
 * Example 2:
 * <p>
 * Input: words = ["a","b","c"], pattern = "a"
 * Output: ["a","b","c"]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= pattern.length <= 20, p
 * 1 <= words.length <= 50, n
 * words[i].length == pattern.length, l
 * pattern and words[i] are lowercase English letters.
 */
@SuppressWarnings("unused")
public class FindReplacePattern {
    // solution 1, filter. O(nl) time and space. 3ms, 42.54mb.
    // other solutions including two maps, normalize (mnpqrr, xyzabb->aabcde), indexof equal.
    public List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> ans = new ArrayList<>();
        for (String word : words)
            if (match(word, pattern))
                ans.add(word);
        return ans;
    }

    public boolean match(String w, String p) {
        Map<Character, Character> llm = new HashMap<>(); // letter->letter mapping
        for (int i = 0; i < w.length(); ++i) {
            char c1 = w.charAt(i), c2 = p.charAt(i);
            if (llm.computeIfAbsent(c1, k -> c2) != c2) return false;
        }
        boolean[] seen = new boolean[26];
        for (char c : llm.values()) {
            int id = c - 'a';
            if (seen[id]) return false;
            seen[id] = true;
        }
        return true;
        // return llm.values().size() == new HashSet<>(llm.values()).size();
    }
}
