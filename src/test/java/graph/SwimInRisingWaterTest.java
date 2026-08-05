package graph;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SwimInRisingWaterTest {

    @Test
    void test2x2() {
        assertEquals(3, SwimInRisingWater.swimInWater(new int[][]{{0, 2}, {1, 3}}));
        assertEquals(3, SwimInRisingWater.swimInWater2(new int[][]{{0, 2}, {1, 3}}));
    }

    @Test
    void test5x5() {
        int[][] grid = {
                {0, 1, 2, 3, 4},
                {24, 23, 22, 21, 5},
                {12, 13, 14, 15, 16},
                {11, 17, 18, 19, 20},
                {10, 9, 8, 7, 6}
        };
        assertEquals(16, SwimInRisingWater.swimInWater(grid));
        assertEquals(16, SwimInRisingWater.swimInWater2(grid));
    }

    @Test
    void testSingleCell() {
        assertEquals(0, SwimInRisingWater.swimInWater(new int[][]{{0}}));
        assertEquals(0, SwimInRisingWater.swimInWater2(new int[][]{{0}}));
    }

    @Test
    void test2x2PathThroughLowerRight() {
        assertEquals(2, SwimInRisingWater.swimInWater(new int[][]{{0, 1}, {3, 2}}));
        assertEquals(2, SwimInRisingWater.swimInWater2(new int[][]{{0, 1}, {3, 2}}));
    }

    @Test
    void test2x2HighStart() {
        assertEquals(3, SwimInRisingWater.swimInWater(new int[][]{{3, 2}, {0, 1}}));
        assertEquals(3, SwimInRisingWater.swimInWater2(new int[][]{{3, 2}, {0, 1}}));
    }
}
