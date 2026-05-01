package string;

/**
 * LeetCode 3167 HackerRank Better Compression, tags: string, hash table, sorting.
 * <p>
 * Consider a string that is a series of characters each followed by its frequency as an integer.
 * A properly compressed string will consist of one instance of each character in alphabetical order
 * followed by the total count of that character within the string.
 * <p>
 * Example 1:
 * <p>
 * Input: compressed = "a3c9b2c1"
 * Output: "a3b2c10"
 * <p>
 * Example 2:
 * <p>
 * Input: compressed = "c2b3a1"
 * Output: "a1b3c2"
 * <p>
 * Constraints:
 * <p>
 * 1 <= compressed.length <= 6 * 10^4
 * compressed consists only of lowercase English letters and digits.
 */
@SuppressWarnings("unused")
public final class BetterCompression {
    private BetterCompression() {
    }

    // O(n) time, O(1) space.
    static class Solution {
        public String betterCompression(String s) {
            int[] res = new int[26];
            int n = s.length(), i = 0;
            while (i < n) {
                int c = s.charAt(i) - 'a';
                i++;
                int cnt = 0;
                while (i < n && Character.isDigit(s.charAt(i))) {
                    cnt = cnt * 10 + (s.charAt(i) - '0');
                    i++;
                }
                res[c] += cnt;
            }
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < 26; j++) {
                if (res[j] > 0) {
                    sb.append((char) ('a' + j)).append(res[j]);
                }
            }
            return sb.toString();
        }
    }
}
