package hash;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FindTheDifferenceOfTwoArraysTest {

    private void assertDifference(Set<Integer> onlyFirst, Set<Integer> onlySecond, int[] nums1, int[] nums2) {
        List<List<Integer>> result = FindTheDifferenceOfTwoArrays.findDifference(nums1, nums2);
        assertEquals(2, result.size());
        assertEquals(onlyFirst, new HashSet<>(result.get(0)));
        assertEquals(onlySecond, new HashSet<>(result.get(1)));
        assertEquals(onlyFirst.size(), result.get(0).size());
        assertEquals(onlySecond.size(), result.get(1).size());
    }

    @Test
    void duplicatesAppearOnlyOnce() {
        assertDifference(Set.of(3), Set.of(), new int[]{1, 2, 3, 3}, new int[]{1, 1, 2, 2});
    }

    @Test
    void identicalArraysHaveNoDifference() {
        assertDifference(Set.of(), Set.of(), new int[]{4, 4, -2}, new int[]{-2, 4, 4});
    }

    @Test
    void disjointArraysReturnAllDistinctValues() {
        assertDifference(Set.of(1, 2), Set.of(3, 4), new int[]{1, 2}, new int[]{3, 4});
    }

    @Test
    void handlesNegativeValues() {
        assertDifference(Set.of(-8), Set.of(-1), new int[]{-8, -3, 2}, new int[]{-3, -1, 2});
    }

    @Test
    void handlesValueBoundaries() {
        assertDifference(Set.of(1000), Set.of(-999, 999),
                new int[]{-1000, 0, 1000, 1000}, new int[]{-1000, 0, -999, 999});
    }

    @Test
    void handlesAsymmetricDifferences() {
        assertDifference(Set.of(7), Set.of(10, 11),
                new int[]{7, 7, 8, 9}, new int[]{8, 8, 9, 10, 11, 11});
    }

    @Test
    void equalSingletonsHaveNoDifference() {
        assertDifference(Set.of(), Set.of(), new int[]{42}, new int[]{42});
    }

    @Test
    void distinctSingletonsAppearInTheirRespectiveLists() {
        assertDifference(Set.of(-1000), Set.of(1000), new int[]{-1000}, new int[]{1000});
    }
}
