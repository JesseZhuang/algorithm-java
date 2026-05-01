package array;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class KokoBananaTest {
    @Test
    void test() {
        assertEquals(4, KokoBanana.minEatingSpeed(new int[]{3, 6, 7, 11}, 8));
        assertEquals(30, KokoBanana.minEatingSpeed(new int[]{30, 11, 23, 4, 20}, 5));
        assertEquals(23, KokoBanana.minEatingSpeed(new int[]{30, 11, 23, 4, 20}, 6));
        // single pile
        assertEquals(1, KokoBanana.minEatingSpeed(new int[]{1}, 1));
        assertEquals(5, KokoBanana.minEatingSpeed(new int[]{5}, 1));
        assertEquals(3, KokoBanana.minEatingSpeed(new int[]{5}, 2));
        // all same
        assertEquals(4, KokoBanana.minEatingSpeed(new int[]{4, 4, 4}, 3));
    }
}
