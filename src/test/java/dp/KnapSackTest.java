package dp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class KnapSackTest {

    @Test
    void knapSack() {
        int[] weights = new int[]{1, 2, 3}, values = new int[]{6, 10, 12};
        assertEquals(22, KnapSack.max2D(5, weights, values));
        assertEquals(22, KnapSack.max1D(5, weights, values));
    }
}