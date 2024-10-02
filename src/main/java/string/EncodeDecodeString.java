package string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LeetCode 271, medium. LintCode 659, tags: design, array, string.
 * <p>
 * Design an algorithm to encode a list of strings to a string. The encoded string is then sent over the network and
 * is decoded back to the original list of strings.
 * <p>
 * Machine 1 (sender) has the function:
 * <pre>
 * string encode(vector<string> strs) {
 *     // ... your code
 *     return encoded_string;
 * }
 * </pre>
 * Machine 2 (receiver) has the function:
 * <pre>
 * vector<string> decode(string s) {
 *     //... your code
 *     return strs;
 * }
 * </pre>
 * So Machine 1 does:
 * <p>
 * string encoded_string = encode(strs);
 * and Machine 2 does:
 * <p>
 * vector<string> strs2 = decode(encoded_string);
 * strs2 in Machine 2 should be the same as strs in Machine 1.
 * <p>
 * Implement the encode and decode methods.
 * <p>
 * You are not allowed to solve the problem using any serialize methods (such as eval).
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: dummy_input = ["Hello","World"]
 * Output: ["Hello","World"]
 * Explanation:
 * Machine 1:
 * Codec encoder = new Codec();
 * String msg = encoder.encode(strs);
 * Machine 1 ---msg---> Machine 2
 * <p>
 * Machine 2:
 * Codec decoder = new Codec();
 * String[] strs = decoder.decode(msg);
 * Example 2:
 * <p>
 * Input: dummy_input = [""]
 * Output: [""]
 * Note:
 * The string may contain any possible characters out of 256 valid extended ascii characters. Your algorithm should be
 * generalized enough to work on any possible characters.
 * Do not use class member/global/static variables to store states. Your encode and decode algorithms
 * should be stateless.
 * Do not rely on any library method such as eval or serialize methods. You should implement your own
 * encode/decode algorithm.
 * <p>
 * Constraints:
 * <p>
 * 1 <= strs.length <= 200
 * 0 <= strs[i].length <= 200
 * strs[i] contains any possible characters out of 256 valid ASCII characters.
 * <p>
 * <p>
 * Follow up: Could you write a generalized algorithm to work on any possible set of characters?
 */
@SuppressWarnings("unused")
public class EncodeDecodeString {

    // 1479ms, 21.5Mb. Solution1, delimiter, n, 1.
    static class Codec1 {
        private static String delimiter = String.valueOf(Character.toChars(256)); // ā, '\u0256'

        public String encode(List<String> strs) {
            return String.join(delimiter, strs);
        }

        public List<String> decode(String str) {
            return Arrays.asList(str.split(delimiter));
        }
    }

    // another idea is to encode with string length and optionally a delimiter, such as '3/aba'. 1699ms, 21.06mb.
    static class Codec2 {
        public String encode(List<String> strs) {
            StringBuilder ans = new StringBuilder();
            for (String s : strs) ans.append((char) s.length()).append(s); // 2 bytes to store the length, upto 65535
            return ans.toString();
        }

        public List<String> decode(String s) {
            List<String> res = new ArrayList<>();
            int i = 0, n = s.length();
            while (i < n) {
                int size = s.charAt(i++);
                res.add(s.substring(i, i + size));
                i += size;
            }
            return res;
        }
    }

    // Your Codec object will be instantiated and called as such:
    // Codec codec = new Codec();
    // codec.decode(codec.encode(strs));
}
