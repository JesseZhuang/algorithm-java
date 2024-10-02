package dp;


import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 159, LintCode 928, medium, tags: hash table, two pointers, string, sliding window.
 * <p>
 * Given a string s, return the length of the longest substring that contains at most two distinct characters.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "eceba"
 * Output: 3
 * Explanation: The substring is "ece" which its length is 3.
 * Example 2:
 * <p>
 * Input: s = "ccaabbb"
 * Output: 5
 * Explanation: The substring is "aabbb" which its length is 5.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 10^5
 * s consists of English letters.
 */
public class LongestS2DC {
    // solution 1, sliding window 2p, lint code 343 ms, 20.67 Mb. n time, 52 space. LinkedHashMap if 52 is concerning
    // imagine 1000 a followed by b-z,A-Z (1 each) map size 52 with a count still > 0
    public static int lengthOfLongestSubstringTwoDistinct(String s) {
        // Write your code here
        int res = 0, n = s.length();
        Map<Character, Integer> count = new HashMap<>();
        for (int l = 0, r = 0; r < n; r++) {
            char c = s.charAt(r);
            count.put(c, count.getOrDefault(c, 0) + 1);
            if (count.size() <= 2) res = Math.max(res, r - l + 1);
            else {
                char lc = s.charAt(l++);
                int cnt = count.get(lc);
                if (cnt == 1) count.remove(lc);
                else count.put(lc, cnt - 1);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        // use debug and check map size, can see count.size() == 26
        lengthOfLongestSubstringTwoDistinct("aaaa aaaa aaaa aaaa aaaa aaaa bcdefghijklmnopqrstuvwzyz");
        System.out.println(String.valueOf(69));
        System.out.println(String.valueOf('\u0000'));
    }
}
