package dp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ContainerWithMostWaterTest {

    private final ContainerWithMostWater solution = new ContainerWithMostWater();

    @Test
    void testMaxArea() {
        assertEquals(49, solution.maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
        assertEquals(1, solution.maxArea(new int[]{1, 1}));
        assertEquals(16, solution.maxArea(new int[]{4, 3, 2, 1, 4}));
        assertEquals(20, solution.maxArea(new int[]{5, 5, 5, 5, 5}));
        assertEquals(3, solution.maxArea(new int[]{3, 100}));
        assertEquals(80, solution.maxArea(new int[]{10, 1, 1, 1, 1, 1, 1, 1, 10}));
    }

    @Test
    void testMaxArea2() {
        assertEquals(49, solution.maxArea2(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
        assertEquals(1, solution.maxArea2(new int[]{1, 1}));
        assertEquals(16, solution.maxArea2(new int[]{4, 3, 2, 1, 4}));
        assertEquals(20, solution.maxArea2(new int[]{5, 5, 5, 5, 5}));
        assertEquals(3, solution.maxArea2(new int[]{3, 100}));
        assertEquals(80, solution.maxArea2(new int[]{10, 1, 1, 1, 1, 1, 1, 1, 10}));
    }
}
