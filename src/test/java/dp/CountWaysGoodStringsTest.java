package dp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CountWaysGoodStringsTest {
    @Test
    void testCountGoodStrings() {
        assertEquals(8, CountWaysGoodStrings.countGoodStrings(3, 3, 1, 1));
        assertEquals(5, CountWaysGoodStrings.countGoodStrings(2, 3, 1, 2));
    }
}
