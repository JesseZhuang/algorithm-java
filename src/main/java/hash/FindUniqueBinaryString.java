package hash;

import java.util.Collections;
import java.util.HashSet;

/**
 * LeetCode 1980. Medium. Tags: array, hash table.
 * <p>
 * Given an array of unique binary strings nums, each of length n,
 * return a binary string of length n that does not appear in nums.
 * <p>
 * This implementation uses the diagonal flip construction:
 * for each index i, look at nums[i].charAt(i) and flip the bit.
 */
public class FindUniqueBinaryString {

    // 1ms 43.29mb
    public String findDifferentBinaryString(String[] nums) {
        int n = nums.length;
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            char c = nums[i].charAt(i);
            sb.append(c == '0' ? '1' : '0');
        }
        return sb.toString();
    }

    /**
     * Alternate solution using HashSet + enumeration.
     * 1ms 43.14mb
     */
    public String findDifferentBinaryStringSet(String[] nums) {
        int n = nums.length;
        HashSet<String> seen = new HashSet<>();
        Collections.addAll(seen, nums);
        int limit = 1 << n;
        char[] buf = new char[n];
        for (int mask = 0; mask < limit; mask++) {
            for (int i = 0; i < n; i++) {
                int bit = (mask >> (n - 1 - i)) & 1;
                buf[i] = bit == 1 ? '1' : '0';
            }
            String candidate = new String(buf);
            if (!seen.contains(candidate)) {
                return candidate;
            }
        }
        return "";
    }
}
