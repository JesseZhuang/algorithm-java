package array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FindDuplicateTest {

    @Test
    void testFloydCycleDetection() {
        assertEquals(2, FindDuplicate.findDuplicate1(new int[]{1, 3, 4, 2, 2}));
        assertEquals(3, FindDuplicate.findDuplicate1(new int[]{3, 1, 3, 4, 2}));
        assertEquals(1, FindDuplicate.findDuplicate1(new int[]{1, 1, 1, 1, 1}));
        assertEquals(1, FindDuplicate.findDuplicate1(new int[]{1, 1}));
        assertEquals(2, FindDuplicate.findDuplicate1(new int[]{2, 2, 2, 2, 2}));
        assertEquals(4, FindDuplicate.findDuplicate1(new int[]{1, 4, 4, 2, 3}));
        assertEquals(4, FindDuplicate.findDuplicate1(new int[]{1, 2, 3, 4, 4}));
        assertEquals(5, FindDuplicate.findDuplicate1(new int[]{5, 1, 2, 3, 4, 5}));
    }

    @Test
    void testSort() {
        assertEquals(2, FindDuplicate.findDuplicate3(new int[]{1, 3, 4, 2, 2}));
        assertEquals(3, FindDuplicate.findDuplicate3(new int[]{3, 1, 3, 4, 2}));
        assertEquals(1, FindDuplicate.findDuplicate3(new int[]{1, 1, 1, 1, 1}));
        assertEquals(1, FindDuplicate.findDuplicate3(new int[]{1, 1}));
        assertEquals(4, FindDuplicate.findDuplicate3(new int[]{1, 4, 4, 2, 3}));
    }
}
