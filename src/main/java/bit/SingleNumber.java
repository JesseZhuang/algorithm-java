package bit;

import java.util.HashSet;
import java.util.Set;

/** LeetCode 136. Find the number that appears once when every other number appears twice. */
public final class SingleNumber {

    private SingleNumber() {
    }

    // O(n) time and O(1) extra space. Each pair cancels out under XOR.
    public static int singleNumberXor(int[] nums) {
        int single = 0;
        for (int num : nums) {
            single ^= num;
        }
        return single;
    }

    // O(n) expected time and O(n) extra space. A second occurrence removes the pair.
    public static int singleNumberHashSet(int[] nums) {
        Set<Integer> unpaired = new HashSet<>();
        for (int num : nums) {
            if (!unpaired.add(num)) {
                unpaired.remove(num);
            }
        }
        return unpaired.iterator().next();
    }
}
