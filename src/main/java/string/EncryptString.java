package string;

/**
 * LeetCode 3210, medium, tags: string.
 * Companies: reddit.
 * <p>
 * You are given a string s and an integer k. Encrypt the string using the following algorithm:
 * <p>
 * For each character c in s, replace c with the kth character after c in the string (in a cyclic manner).
 * Return the encrypted string.
 * <p>
 * Example 1:
 * <p>
 * Input: s = "dart", k = 3
 * <p>
 * Output: "tdar"
 * <p>
 * Explanation:
 * <p>
 * For i = 0, the 3rd character after 'd' is 't'.
 * For i = 1, the 3rd character after 'a' is 'd'.
 * For i = 2, the 3rd character after 'r' is 'a'.
 * For i = 3, the 3rd character after 't' is 'r'.
 * Example 2:
 * <p>
 * Input: s = "aaa", k = 1
 * <p>
 * Output: "aaa"
 * <p>
 * Explanation:
 * <p>
 * As all the characters are the same, the encrypted string will also be the same.
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 100
 * 1 <= k <= 10^4
 * s consists only of lowercase English letters.
 * <p>
 * Hint 1
 * Make a new string such that for each character in s, character i will correspond to (i + k) % n character
 * in the original string.
 */
@SuppressWarnings("unused")
public class EncryptString {
    // modulus, n, n. 1ms, 42.7mb.
    public String getEncryptedString(String s, int k) {
        StringBuilder res = new StringBuilder();
        int n = s.length();
        for (int i = 0; i < n; i++)
            res.append(s.charAt((i + k) % n));
        return res.toString();
    }
}
