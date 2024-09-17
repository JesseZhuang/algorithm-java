package hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * LeetCode 884, easy, tags: hash table, string, counting.
 * <p>
 * A sentence is a string of single-space separated words where each word consists only of lowercase letters.
 * <p>
 * A word is uncommon if it appears exactly once in one of the sentences, and does not appear in the other sentence.
 * <p>
 * Given two sentences s1 and s2, return a list of all the uncommon words. You may return the answer in any order.
 * <p>
 * Example 1:
 * <p>
 * Input: s1 = "this apple is sweet", s2 = "this apple is sour"
 * <p>
 * Output: ["sweet","sour"]
 * <p>
 * Explanation:
 * <p>
 * The word "sweet" appears only in s1, while the word "sour" appears only in s2.
 * <p>
 * Example 2:
 * <p>
 * Input: s1 = "apple apple", s2 = "banana"
 * <p>
 * Output: ["banana"]
 * <p>
 * Constraints:
 * <p>
 * 1 <= s1.length, s2.length <= 200
 * s1 and s2 consist of lowercase English letters and spaces.
 * s1 and s2 do not have leading or trailing spaces.
 * All the words in s1 and s2 are separated by a single space.
 */
public class UncommonWords {
    // solution 1, map, n, n, 6ms, 42.1Mb.
    public String[] uncommonFromSentences(String s1, String s2) {
        String[] words = (s1 + " " + s2).split(" ");
        HashMap<String, Integer> counts = new HashMap<>();
        for (String w : words)
            counts.put(w, counts.getOrDefault(w, 0) + 1);
        List<String> res = new ArrayList<>();
        for (String w : counts.keySet())
            if (counts.get(w) == 1) res.add(w);
        return res.toArray(new String[0]);
    }
}
