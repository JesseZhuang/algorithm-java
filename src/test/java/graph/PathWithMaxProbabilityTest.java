package graph;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PathWithMaxProbabilityTest {

    private static final double DELTA = 1e-5;

    @Test
    void testExample1() {
        int[][] edges = {{0, 1}, {1, 2}, {0, 2}};
        double[] prob = {0.5, 0.5, 0.2};
        assertEquals(0.25, PathWithMaxProbability.maxProbability(3, edges, prob, 0, 2), DELTA);
        assertEquals(0.25, PathWithMaxProbability.maxProbability2(3, edges, prob, 0, 2), DELTA);
    }

    @Test
    void testExample2() {
        int[][] edges = {{0, 1}, {1, 2}, {0, 2}};
        double[] prob = {0.5, 0.5, 0.3};
        assertEquals(0.3, PathWithMaxProbability.maxProbability(3, edges, prob, 0, 2), DELTA);
        assertEquals(0.3, PathWithMaxProbability.maxProbability2(3, edges, prob, 0, 2), DELTA);
    }

    @Test
    void testNoPath() {
        int[][] edges = {{0, 1}};
        double[] prob = {0.5};
        assertEquals(0.0, PathWithMaxProbability.maxProbability(3, edges, prob, 0, 2), DELTA);
        assertEquals(0.0, PathWithMaxProbability.maxProbability2(3, edges, prob, 0, 2), DELTA);
    }

    @Test
    void testDirectEdge() {
        int[][] edges = {{0, 1}};
        double[] prob = {0.8};
        assertEquals(0.8, PathWithMaxProbability.maxProbability(2, edges, prob, 0, 1), DELTA);
        assertEquals(0.8, PathWithMaxProbability.maxProbability2(2, edges, prob, 0, 1), DELTA);
    }

    @Test
    void testLongerPathBetter() {
        // direct edge 0->2 has prob 0.1, but 0->1->2 has 0.9*0.9=0.81
        int[][] edges = {{0, 1}, {1, 2}, {0, 2}};
        double[] prob = {0.9, 0.9, 0.1};
        assertEquals(0.81, PathWithMaxProbability.maxProbability(3, edges, prob, 0, 2), DELTA);
        assertEquals(0.81, PathWithMaxProbability.maxProbability2(3, edges, prob, 0, 2), DELTA);
    }

    @Test
    void testDisconnected() {
        // nodes 0-1 connected, nodes 2-3 connected, no path from 0 to 3
        int[][] edges = {{0, 1}, {2, 3}};
        double[] prob = {0.5, 0.5};
        assertEquals(0.0, PathWithMaxProbability.maxProbability(4, edges, prob, 0, 3), DELTA);
        assertEquals(0.0, PathWithMaxProbability.maxProbability2(4, edges, prob, 0, 3), DELTA);
    }

    @Test
    void testSingleNodeStartEqualsEnd() {
        // start == end edge case: n=2 but start==end not possible per constraints,
        // test with n=3, start=0, end=0 (trivial path probability = 1.0 conceptually)
        // However LeetCode states start != end, so we test with adjacent scenario:
        // n=2, single edge, start=0, end=1
        int[][] edges = {{0, 1}};
        double[] prob = {1.0};
        assertEquals(1.0, PathWithMaxProbability.maxProbability(2, edges, prob, 0, 1), DELTA);
        assertEquals(1.0, PathWithMaxProbability.maxProbability2(2, edges, prob, 0, 1), DELTA);
    }
}
