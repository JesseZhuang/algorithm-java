package array;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FindAllDuplicatesTest {

    @Test
    void testNegationMarking() {
        assertSorted(List.of(2, 3), FindAllDuplicates.findDuplicates1(new int[]{4, 3, 2, 7, 8, 2, 3, 1}));
        assertSorted(List.of(1), FindAllDuplicates.findDuplicates1(new int[]{1, 1, 2}));
        assertSorted(List.of(), FindAllDuplicates.findDuplicates1(new int[]{1}));
        assertSorted(List.of(1, 2), FindAllDuplicates.findDuplicates1(new int[]{1, 2, 1, 2}));
        assertSorted(List.of(2), FindAllDuplicates.findDuplicates1(new int[]{2, 2}));
        assertSorted(List.of(), FindAllDuplicates.findDuplicates1(new int[]{1, 2, 3, 4, 5}));
        assertSorted(List.of(1, 4), FindAllDuplicates.findDuplicates1(new int[]{1, 3, 4, 2, 1, 4}));
    }

    @Test
    void testCyclicSort() {
        assertSorted(List.of(2, 3), FindAllDuplicates.findDuplicates2(new int[]{4, 3, 2, 7, 8, 2, 3, 1}));
        assertSorted(List.of(1), FindAllDuplicates.findDuplicates2(new int[]{1, 1, 2}));
        assertSorted(List.of(), FindAllDuplicates.findDuplicates2(new int[]{1}));
        assertSorted(List.of(1, 2), FindAllDuplicates.findDuplicates2(new int[]{1, 2, 1, 2}));
        assertSorted(List.of(2), FindAllDuplicates.findDuplicates2(new int[]{2, 2}));
        assertSorted(List.of(), FindAllDuplicates.findDuplicates2(new int[]{1, 2, 3, 4, 5}));
        assertSorted(List.of(1, 4), FindAllDuplicates.findDuplicates2(new int[]{1, 3, 4, 2, 1, 4}));
    }

    private void assertSorted(List<Integer> expected, List<Integer> actual) {
        List<Integer> sortedExpected = expected.stream().sorted().toList();
        List<Integer> sortedActual = actual.stream().sorted().toList();
        assertEquals(sortedExpected, sortedActual);
    }
}
