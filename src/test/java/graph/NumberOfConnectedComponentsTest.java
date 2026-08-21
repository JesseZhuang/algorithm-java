package graph;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NumberOfConnectedComponentsTest {

    @Test
    void example1() {
        int[][] edges = {{0, 1}, {1, 2}, {3, 4}};
        assertEquals(2, NumberOfConnectedComponents.countComponentsUF(5, edges));
        assertEquals(2, NumberOfConnectedComponents.countComponentsDFS(5, edges));
    }

    @Test
    void example2AllConnected() {
        int[][] edges = {{0, 1}, {1, 2}, {2, 3}, {3, 4}};
        assertEquals(1, NumberOfConnectedComponents.countComponentsUF(5, edges));
        assertEquals(1, NumberOfConnectedComponents.countComponentsDFS(5, edges));
    }

    @Test
    void noEdges() {
        int[][] edges = {};
        assertEquals(4, NumberOfConnectedComponents.countComponentsUF(4, edges));
        assertEquals(4, NumberOfConnectedComponents.countComponentsDFS(4, edges));
    }

    @Test
    void singleNode() {
        int[][] edges = {};
        assertEquals(1, NumberOfConnectedComponents.countComponentsUF(1, edges));
        assertEquals(1, NumberOfConnectedComponents.countComponentsDFS(1, edges));
    }

    @Test
    void fullyConnectedLine() {
        int[][] edges = {{0, 1}, {1, 2}, {2, 3}, {3, 4}};
        assertEquals(1, NumberOfConnectedComponents.countComponentsUF(5, edges));
        assertEquals(1, NumberOfConnectedComponents.countComponentsDFS(5, edges));
    }

    @Test
    void threeComponents() {
        // 6 nodes in pairs: (0,1), (2,3), (4,5)
        int[][] edges = {{0, 1}, {2, 3}, {4, 5}};
        assertEquals(3, NumberOfConnectedComponents.countComponentsUF(6, edges));
        assertEquals(3, NumberOfConnectedComponents.countComponentsDFS(6, edges));
    }

    @Test
    void cycle() {
        // Triangle: 0-1-2-0
        int[][] edges = {{0, 1}, {1, 2}, {0, 2}};
        assertEquals(1, NumberOfConnectedComponents.countComponentsUF(3, edges));
        assertEquals(1, NumberOfConnectedComponents.countComponentsDFS(3, edges));
    }
}
