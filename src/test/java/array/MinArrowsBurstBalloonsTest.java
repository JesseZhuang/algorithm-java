package array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MinArrowsBurstBalloonsTest {

    // LeetCode Example 1
    @Test
    void example1() {
        int[][] points = {{10, 16}, {2, 8}, {1, 6}, {7, 12}};
        assertEquals(2, MinArrowsBurstBalloons.findMinArrowShots(points));
    }

    @Test
    void example1Solution2() {
        int[][] points = {{10, 16}, {2, 8}, {1, 6}, {7, 12}};
        assertEquals(2, MinArrowsBurstBalloons.findMinArrowShots2(points));
    }

    // LeetCode Example 2
    @Test
    void example2() {
        int[][] points = {{1, 2}, {3, 4}, {5, 6}, {7, 8}};
        assertEquals(4, MinArrowsBurstBalloons.findMinArrowShots(points));
    }

    @Test
    void example2Solution2() {
        int[][] points = {{1, 2}, {3, 4}, {5, 6}, {7, 8}};
        assertEquals(4, MinArrowsBurstBalloons.findMinArrowShots2(points));
    }

    // LeetCode Example 3
    @Test
    void example3() {
        int[][] points = {{1, 2}, {2, 3}, {3, 4}, {4, 5}};
        assertEquals(2, MinArrowsBurstBalloons.findMinArrowShots(points));
    }

    @Test
    void example3Solution2() {
        int[][] points = {{1, 2}, {2, 3}, {3, 4}, {4, 5}};
        assertEquals(2, MinArrowsBurstBalloons.findMinArrowShots2(points));
    }

    // Single balloon
    @Test
    void singleBalloon() {
        int[][] points = {{5, 10}};
        assertEquals(1, MinArrowsBurstBalloons.findMinArrowShots(points));
        assertEquals(1, MinArrowsBurstBalloons.findMinArrowShots2(points));
    }

    // All overlapping
    @Test
    void allOverlapping() {
        int[][] points = {{1, 10}, {2, 9}, {3, 8}, {4, 7}};
        assertEquals(1, MinArrowsBurstBalloons.findMinArrowShots(points));
        assertEquals(1, MinArrowsBurstBalloons.findMinArrowShots2(points));
    }

    // Touching edges
    @Test
    void touchingEdges() {
        int[][] points = {{1, 2}, {2, 3}, {3, 4}};
        assertEquals(2, MinArrowsBurstBalloons.findMinArrowShots(points));
        assertEquals(2, MinArrowsBurstBalloons.findMinArrowShots2(points));
    }

    // Negative range with INT_MIN and INT_MAX
    @Test
    void intMinMaxRange() {
        int[][] points = {
            {Integer.MIN_VALUE, Integer.MIN_VALUE + 1},
            {Integer.MAX_VALUE - 1, Integer.MAX_VALUE},
            {-1, 1}
        };
        assertEquals(3, MinArrowsBurstBalloons.findMinArrowShots(points));
        assertEquals(3, MinArrowsBurstBalloons.findMinArrowShots2(points));
    }
}
