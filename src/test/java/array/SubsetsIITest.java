package array;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SubsetsIITest {

    private void assertSubsetsII(int[] nums, int expectedSize) {
        // backtracking approach
        List<List<Integer>> res1 = SubsetsII.subsetsWithDup(nums);
        assertEquals(expectedSize, res1.size(), "subsetsWithDup size mismatch");
        assertAllUnique(res1);

        // cascading approach
        List<List<Integer>> res2 = SubsetsII.subsetsWithDupCascade(nums);
        assertEquals(expectedSize, res2.size(), "subsetsWithDupCascade size mismatch");
        assertAllUnique(res2);
    }

    private void assertAllUnique(List<List<Integer>> subsets) {
        Set<List<Integer>> unique = new HashSet<>();
        for (List<Integer> subset : subsets) {
            List<Integer> sorted = new ArrayList<>(subset);
            Collections.sort(sorted);
            assertTrue(unique.add(sorted), "Duplicate subset found: " + sorted);
        }
    }

    @Test
    void testBasicWithDuplicates() {
        // [1,2,2] → [],[1],[2],[1,2],[2,2],[1,2,2]
        assertSubsetsII(new int[]{1, 2, 2}, 6);
    }

    @Test
    void testSingleElement() {
        // [0] → [],[0]
        assertSubsetsII(new int[]{0}, 2);
    }

    @Test
    void testAllDuplicates() {
        // [1,1,1] → [],[1],[1,1],[1,1,1]
        assertSubsetsII(new int[]{1, 1, 1}, 4);
    }

    @Test
    void testNoDuplicates() {
        // [1,2,3] → all 2^3 = 8 subsets
        assertSubsetsII(new int[]{1, 2, 3}, 8);
    }

    @Test
    void testNegativeNumbers() {
        // [-1,-1,2] → [],[-1],[-1,-1],[2],[-1,2],[-1,-1,2]
        assertSubsetsII(new int[]{-1, -1, 2}, 6);
    }

    @Test
    void testLargerInput() {
        // [1,1,2,2,3,3,4,4,5,5] — each of 5 values appears twice
        // subsets count = product of (count_i + 1) = 3^5 = 243
        assertSubsetsII(new int[]{1, 1, 2, 2, 3, 3, 4, 4, 5, 5}, 243);
    }
}
