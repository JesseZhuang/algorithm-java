package heap;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * LeetCode 767, medium, tags: hash table, string, greedy, sorting, heap, counting.
 * <p>
 * Given a string s, rearrange the characters of s so that any two adjacent characters are not the same.
 * <p>
 * Return any possible rearrangement of s or return "" if not possible.
 * <p>
 * Example 1:
 * <p>
 * Input: s = "aab"
 * Output: "aba"
 * Example 2:
 * <p>
 * Input: s = "aaab"
 * Output: ""
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 500
 * s consists of lowercase English letters.
 * <p>
 * Hint 1
 * Alternate placing the most common letters.
 */
public class ReorganizeString {
    public static void main(String[] args) {
        System.out.println(new ReorganizeString().reorganizeString2("aba"));
    }

    // solution 1, counting, n time, 1 space. 1ms, 41.72Mb.
    public String reorganizeString(String s) {
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) count[s.charAt(i) - 'a']++;
        int max = 0, maxInd = 0;
        for (int i = 0; i < count.length; i++) {
            if (count[i] > max) {
                max = count[i];
                maxInd = i;
            }
        }
        if (max > (s.length() + 1) / 2) return ""; // Impossible to form a solution, do not forget
        char[] res = new char[s.length()];
        int idx = 0;
        while (count[maxInd] > 0) { // place max frequency char at index 0,2,4,...
            res[idx] = (char) (maxInd + 'a'); // remember to cast
            idx += 2;
            count[maxInd]--;
        }
        for (int i = 0; i < count.length; i++) {
            while (count[i] > 0) {
                if (idx >= res.length) idx = 1; // must be inside to continue after the most frequent letter
                res[idx] = (char) (i + 'a');
                idx += 2;
                count[i]--;
            }
        }
        return String.valueOf(res);
    }

    // heap, O(n+nLgk) time, O(k) space. k<=26. 7ms, 41.56 Mb.
    public String reorganizeString2(String s) {
        Map<Character, Integer> counts = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            int count = counts.getOrDefault(s.charAt(i), 0) + 1;
            if (count > (s.length() + 1) / 2) return "";
            counts.put(s.charAt(i), count);
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        for (char c : counts.keySet()) pq.add(new int[]{c, -counts.get(c)}); // neg so counts desc
        StringBuilder sb = new StringBuilder();
        Character preC = null;
        while (!pq.isEmpty()) { // nLgk time
            char cur = (char) pq.remove()[0];
            sb.append(cur);
            counts.put(cur, counts.get(cur) - 1); // decrement since used one char
            if (preC != null && counts.get(preC) > 0) pq.add(new int[]{preC, -counts.get(preC)});
            preC = cur;
        }
        return sb.toString();
    }
}
