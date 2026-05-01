package string;

/**
 * LeetCode 423 LintCode 1247, medium, tags: hash table, math, string.
 * <p>
 * Given a string s containing an out-of-order English representation of digits 0-9,
 * return the digits in ascending order.
 * <p>
 * Example 1:
 * <p>
 * Input: s = "owoztneoer"
 * Output: "012"
 * <p>
 * Example 2:
 * <p>
 * Input: s = "fviefuro"
 * Output: "45"
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 10^5
 * s[i] is one of the characters ["e","g","f","i","h","o","n","s","r","u","t","w","v","x","z"].
 * s is guaranteed to be valid.
 */
@SuppressWarnings("unused")
public final class ReconstructDigits {
    private ReconstructDigits() {
    }

    // O(n) time, O(1) space.
    static class Solution {
        public String originalDigits(String s) {
            int[] cnt = new int[26];
            for (char c : s.toCharArray()) cnt[c - 'a']++;
            int[] res = new int[10];
            res[0] = cnt['z' - 'a'];
            res[2] = cnt['w' - 'a'];
            res[4] = cnt['u' - 'a'];
            res[6] = cnt['x' - 'a'];
            res[8] = cnt['g' - 'a'];
            res[1] = cnt['o' - 'a'] - (res[0] + res[2] + res[4]);
            res[3] = cnt['r' - 'a'] - (res[0] + res[4]);
            res[5] = cnt['f' - 'a'] - res[4];
            res[7] = cnt['s' - 'a'] - res[6];
            res[9] = cnt['i' - 'a'] - (res[5] + res[6] + res[8]);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 10; i++)
                for (int j = 0; j < res[i]; j++)
                    sb.append(i);
            return sb.toString();
        }
    }
}
