package array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ContainsDuplicateIIITest {
    @Test
    void testSolution() {
        // if (mem.contains(nums[i] + valueDiff) || mem.contains(nums[i] - valueDiff)) return true; // wrong, range!
        assertTrue(ContainsDuplicateIII.containsNearbyAlmostDuplicateB(new int[]{1, 0, 1, 1}, 1, 2));
        // if (inRange(mem.first(), left, right) || inRange(mem.last(), left, right))
        assertTrue(ContainsDuplicateIII.containsNearbyAlmostDuplicateB(new int[]{4, 1, 6, 3}, 4, 1));
        assertTrue(ContainsDuplicateIII.containsNearbyAlmostDuplicateB(new int[]{-1, -2, 1}, 4, 2));
    }

}