package array;

import java.util.HashSet;
import java.util.Set;

/** Hash-set solution for LeetCode 2367, Number of Arithmetic Triplets. */
public final class ArithmeticTriplets {

    private ArithmeticTriplets() {
    }

    /** Expected O(n) time and O(n) extra space for strictly increasing nums and positive diff. */
    public static int arithmeticTriplets(int[] nums, int diff) {
        Set<Integer> values = new HashSet<>();
        for (int num : nums) {
            values.add(num);
        }

        int count = 0;
        for (int num : nums) {
            if (values.contains(num + diff) && values.contains(num + 2 * diff)) {
                count++;
            }
        }
        return count;
    }
}
