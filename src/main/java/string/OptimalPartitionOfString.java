package string;

import java.util.HashSet;
import java.util.Set;

/** Greedy solutions for LeetCode 2405, Optimal Partition of String. */
public final class OptimalPartitionOfString {

    private OptimalPartitionOfString() {
    }

    /** O(n) time and O(1) space for the fixed lowercase alphabet. */
    public static int partitionString(String s) {
        int partitions = 1; // The input is nonempty.
        Set<Character> seen = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            char letter = s.charAt(i);
            if (!seen.add(letter)) {
                partitions++;
                seen.clear(); // At most 26 letters to clear.
                seen.add(letter);
            }
        }
        return partitions;
    }

    /** O(n) time and O(1) space using one bit per lowercase letter. */
    public static int partitionStringBitmask(String s) {
        int partitions = 1;
        int seen = 0;
        for (int i = 0; i < s.length(); i++) {
            int bit = 1 << (s.charAt(i) - 'a');
            if ((seen & bit) != 0) {
                partitions++;
                seen = 0;
            }
            seen |= bit;
        }
        return partitions;
    }
}
