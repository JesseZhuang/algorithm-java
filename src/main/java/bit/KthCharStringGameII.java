package bit;

/**
 * LeetCode 3307, weekly 417 Q4, hard.
 * <p>
 * Alice and Bob are playing a game. Initially, Alice has a string word = "a".
 * <p>
 * You are given a positive integer k. You are also given an integer array operations, where operations[i] represents
 * the type of the ith operation.
 * <p>
 * Now Bob will ask Alice to perform all operations in sequence:
 * <p>
 * If operations[i] == 0, append a copy of word to itself.
 * If operations[i] == 1, generate a new string by changing each character in word to its next character in the
 * English alphabet, and append it to the original word. For example, performing the operation on "c" generates
 * "cd" and performing the operation on "zb" generates "zbac".
 * Return the value of the kth character in word after performing all the operations.
 * <p>
 * Note that the character 'z' can be changed to 'a' in the second type of operation.
 * <p>
 * Example 1:
 * <p>
 * Input: k = 5, operations = [0,0,0]
 * <p>
 * Output: "a"
 * <p>
 * Explanation:
 * <p>
 * Initially, word == "a". Alice performs the three operations as follows:
 * <p>
 * Appends "a" to "a", word becomes "aa".
 * Appends "aa" to "aa", word becomes "aaaa".
 * Appends "aaaa" to "aaaa", word becomes "aaaaaaaa".
 * Example 2:
 * <p>
 * Input: k = 10, operations = [0,1,0,1]
 * <p>
 * Output: "b"
 * <p>
 * Explanation:
 * <p>
 * Initially, word == "a". Alice performs the four operations as follows:
 * <p>
 * Appends "a" to "a", word becomes "aa".
 * Appends "bb" to "aa", word becomes "aabb".
 * Appends "aabb" to "aabb", word becomes "aabbaabb".
 * Appends "bbccbbcc" to "aabbaabb", word becomes "aabbaabbbbccbbcc".
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= k <= 10^14
 * 1 <= operations.length <= 100
 * operations[i] is either 0 or 1.
 * The input is generated such that word has at least k characters after all operations.
 * <p>
 * Hint 1
 * Try to replay the operations kth character was part of.
 * Hint 2
 * The kth character is only affected if it is present in the first half of the string.
 */
@SuppressWarnings("unused")
public class KthCharStringGameII {
    // @lee215, string length doubles each op. 1,2,4,8,16...
    // If the operation is 0, s[1xxx] = s[xxx]
    // If the operation is 1, s[1xxx] = s[xxx] + 1
    // for each bit in k's binary, if 1, operation matters, if 0, no
    // e.g., k=3-- 2 (b10), ilog2 is 1, i:1, k>>1&1:1, this op matters, i:2, k>>2&1:0, this op does not matter
    static class Solution1 {
        public char kthCharacter(long k, int[] operations) {
            int cnt = 0, n = operations.length; // cnt: count of op that matters
            k -= 1; // index of char
            int len1 = 63 - Long.numberOfLeadingZeros(k); // see {@link BitUtil.ilog2}, floor(log2(x))
            for (int i = len1; i >= 0; --i)
                if ((k >> i & 1) > 0) cnt += operations[i];
            return (char) ('a' + cnt % 26);
        }
    }

    static class Solution2 {
        public char kthCharacter(long k, int[] operations) {
            int i = (int) (Math.floor(Math.log(k) / Math.log(2))); // ilog2: floor(log2(k))
            int count = 0;
            while (k > 1) {
                if (k > (1L << i)) { // only the one bits k> 1<<i, e.g., b1010, op matters for i:3,1
                    if (operations[i] == 1) count++;
                    k -= (1L << i);
                }
                i--;
            }
            return (char) ('a' + (count % 26));
        }
    }
}
