package array;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class MinIncrementUniqueTest {
    @Test
    void testSortGreedy() {
        assertEquals(1, MinIncrementUnique.sortGreedy(new int[]{1, 2, 2}));
        assertEquals(6, MinIncrementUnique.sortGreedy(new int[]{3, 2, 1, 2, 1, 7}));
        assertEquals(0, MinIncrementUnique.sortGreedy(new int[]{1, 2, 3}));
        assertEquals(0, MinIncrementUnique.sortGreedy(new int[]{5}));
        assertEquals(3, MinIncrementUnique.sortGreedy(new int[]{1, 1, 1}));
        assertEquals(0, MinIncrementUnique.sortGreedy(new int[]{0}));
        assertEquals(1, MinIncrementUnique.sortGreedy(new int[]{0, 0}));
        assertEquals(6, MinIncrementUnique.sortGreedy(new int[]{0, 0, 0, 0}));
        assertEquals(0, MinIncrementUnique.sortGreedy(new int[]{100000}));
        assertEquals(1, MinIncrementUnique.sortGreedy(new int[]{2, 2}));
    }

    @Test
    void testCountingSort() {
        assertEquals(1, MinIncrementUnique.countingSort(new int[]{1, 2, 2}));
        assertEquals(6, MinIncrementUnique.countingSort(new int[]{3, 2, 1, 2, 1, 7}));
        assertEquals(0, MinIncrementUnique.countingSort(new int[]{1, 2, 3}));
        assertEquals(0, MinIncrementUnique.countingSort(new int[]{5}));
        assertEquals(3, MinIncrementUnique.countingSort(new int[]{1, 1, 1}));
        assertEquals(0, MinIncrementUnique.countingSort(new int[]{0}));
        assertEquals(1, MinIncrementUnique.countingSort(new int[]{0, 0}));
        assertEquals(6, MinIncrementUnique.countingSort(new int[]{0, 0, 0, 0}));
        assertEquals(0, MinIncrementUnique.countingSort(new int[]{100000}));
        assertEquals(1, MinIncrementUnique.countingSort(new int[]{2, 2}));
    }
}
