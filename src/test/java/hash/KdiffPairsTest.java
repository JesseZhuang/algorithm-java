package hash;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class KdiffPairsTest {

    @Test void example1() {
        assertEquals(2, KdiffPairs.findPairs(new int[]{3, 1, 4, 1, 5}, 2));
    }

    @Test void example2() {
        assertEquals(4, KdiffPairs.findPairs(new int[]{1, 2, 3, 4, 5}, 1));
    }

    @Test void example3() {
        assertEquals(1, KdiffPairs.findPairs(new int[]{1, 3, 1, 5, 4}, 0));
    }
}
