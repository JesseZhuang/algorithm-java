package string;

import java.util.List;

/**
 * LeetCode 271, medium.
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
 * Note:
 * The string may contain any possible characters out of 256 valid ascii characters. Your algorithm should be
 * generalized enough to work on any possible characters.
 * Do not use class member/global/static variables to store states. Your encode and decode algorithms
 * should be stateless.
 * Do not rely on any library method such as eval or serialize methods. You should implement your own
 * encode/decode algorithm.
 */
public class EncodeDecodeString {
    public String encode(List<String> strs) {
        return null;
    }

    public List<String> decode(String s) {
        return null;
    }
}
