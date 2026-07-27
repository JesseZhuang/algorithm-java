package graph;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MinCostConnectAllPointsTest {

    @Test
    void testPrimExample1() {
        int[][] points = {{0, 0}, {2, 2}, {3, 10}, {5, 2}, {7, 0}};
        assertEquals(20, MinCostConnectAllPoints.minCostConnectPointsPrim(points));
    }

    @Test
    void testKruskalExample1() {
        int[][] points = {{0, 0}, {2, 2}, {3, 10}, {5, 2}, {7, 0}};
        assertEquals(20, MinCostConnectAllPoints.minCostConnectPointsKruskal(points));
    }

    @Test
    void testPrimExample2() {
        int[][] points = {{3, 12}, {-2, 5}, {-4, 1}};
        assertEquals(18, MinCostConnectAllPoints.minCostConnectPointsPrim(points));
    }

    @Test
    void testKruskalExample2() {
        int[][] points = {{3, 12}, {-2, 5}, {-4, 1}};
        assertEquals(18, MinCostConnectAllPoints.minCostConnectPointsKruskal(points));
    }

    @Test
    void testSinglePoint() {
        int[][] points = {{0, 0}};
        assertEquals(0, MinCostConnectAllPoints.minCostConnectPointsPrim(points));
        assertEquals(0, MinCostConnectAllPoints.minCostConnectPointsKruskal(points));
    }

    @Test
    void testTwoPoints() {
        int[][] points = {{0, 0}, {1, 1}};
        assertEquals(2, MinCostConnectAllPoints.minCostConnectPointsPrim(points));
        assertEquals(2, MinCostConnectAllPoints.minCostConnectPointsKruskal(points));
    }

    @Test
    void testCollinear() {
        int[][] points = {{0, 0}, {1, 0}, {2, 0}, {3, 0}};
        assertEquals(3, MinCostConnectAllPoints.minCostConnectPointsPrim(points));
        assertEquals(3, MinCostConnectAllPoints.minCostConnectPointsKruskal(points));
    }
}
