package heap;

import java.util.Comparator;
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
 * 1 <= s.length <= 500, n
 * s consists of lowercase English letters, k <= 26
 * <p>
 * Hint 1
 * Alternate placing the most common letters.
 */
public class ReorganizeString {
    public static void main(String[] args) {
        System.out.println(new ReorganizeString().reorganizeString2("aba"));
    }

    // solution 1, counting, n time, k space. 1ms, 41.72Mb.
    public String reorganizeString(String s) {
        int[] count = new int[26]; // can use hashmap if desired
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

    // heap, O(n+nLgk) time, O(k) space. k<=26. 4ms, 41.60 mb.
    public String reorganizeString2(String s) {
        // -cnt, char descending by count
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        int[] counts = new int[26];
        int maxCnt = 0;
        for (int i = 0; i < s.length(); i++) {
            int id = s.charAt(i) - 'a';
            counts[id]++;
            if (counts[id] > maxCnt) maxCnt = counts[id];
        }
        if (maxCnt > (s.length() + 1) / 2) return "";
        for (int i = 0; i < counts.length; i++) if (counts[i] != 0) pq.add(new int[]{-counts[i], i});
        StringBuilder sb = new StringBuilder();
        int prevId = -1; // important, to alternate, otherwise keep getting the most frequent char
        while (!pq.isEmpty()) {
            int id = pq.remove()[1];
            sb.append((char) (id + 'a'));
            counts[id]--;
            if (prevId != -1 && counts[prevId] > 0) pq.add(new int[]{-counts[prevId], prevId});
            prevId = id;
        }
        return sb.toString();
    }
}
