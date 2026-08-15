package binary_search;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MinDaysBouquetsTest {

    @Test
    void testExample1() {
        assertEquals(3, MinDaysBouquets.minDays(new int[]{1, 10, 3, 10, 2}, 3, 1));
    }

    @Test
    void testExample2() {
        assertEquals(-1, MinDaysBouquets.minDays(new int[]{1, 10, 3, 10, 2}, 3, 2));
    }

    @Test
    void testExample3() {
        assertEquals(12, MinDaysBouquets.minDays(new int[]{7, 7, 7, 7, 12, 7, 7}, 2, 3));
    }

    @Test
    void testAllSameDay() {
        assertEquals(5, MinDaysBouquets.minDays(new int[]{5, 5, 5, 5}, 2, 2));
    }

    @Test
    void testSingleFlower() {
        assertEquals(1, MinDaysBouquets.minDays(new int[]{1}, 1, 1));
    }

    @Test
    void testImpossible() {
        assertEquals(-1, MinDaysBouquets.minDays(new int[]{1, 2, 3}, 2, 2));
    }

    @Test
    void testNeedMaxDay() {
        assertEquals(1000000000, MinDaysBouquets.minDays(new int[]{1000000000, 1000000000}, 1, 2));
    }

    @Test
    void testAlreadyBloomed() {
        assertEquals(1, MinDaysBouquets.minDays(new int[]{1, 1, 1, 1}, 2, 2));
    }
}
