package array;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SubsetsTest {

    private void assertSubsets(int[] nums, int expectedSize) {
        // backtracking (instance method, uses instance fields so need fresh object)
        Subsets bt = new Subsets();
        List<List<Integer>> res1 = bt.subsets(nums);
        assertEquals(expectedSize, res1.size());
        assertAllUnique(res1);

        // bit mask
        Subsets bs = new Subsets();
        List<List<Integer>> res2 = bs.subsetsBS(nums);
        assertEquals(expectedSize, res2.size());
        assertAllUnique(res2);

        // cascading (static)
        List<List<Integer>> res3 = Subsets.subsetsCascade(nums);
        assertEquals(expectedSize, res3.size());
        assertAllUnique(res3);
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
    void testThreeElements() {
        assertSubsets(new int[]{1, 2, 3}, 8);
    }

    @Test
    void testSingleElement() {
        assertSubsets(new int[]{0}, 2);
    }

    @Test
    void testEmpty() {
        assertSubsets(new int[]{}, 1);
    }

    @Test
    void testTwoElements() {
        assertSubsets(new int[]{1, 2}, 4);
    }

    @Test
    void testTenElements() {
        int[] nums = IntStream.rangeClosed(1, 10).toArray();
        assertSubsets(nums, 1024);
    }
}
