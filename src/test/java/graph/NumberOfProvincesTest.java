package graph;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NumberOfProvincesTest {

    @Test
    void example1_twoProvinces() {
        int[][] isConnected = {
                {1, 1, 0},
                {1, 1, 0},
                {0, 0, 1}
        };
        assertEquals(2, NumberOfProvinces.findCircleNumDFS(isConnected));
        assertEquals(2, NumberOfProvinces.findCircleNumUnionFind(isConnected));
    }

    @Test
    void example2_threeProvinces() {
        int[][] isConnected = {
                {1, 0, 0},
                {0, 1, 0},
                {0, 0, 1}
        };
        assertEquals(3, NumberOfProvinces.findCircleNumDFS(isConnected));
        assertEquals(3, NumberOfProvinces.findCircleNumUnionFind(isConnected));
    }

    @Test
    void allConnected() {
        int[][] isConnected = {
                {1, 1, 1},
                {1, 1, 1},
                {1, 1, 1}
        };
        assertEquals(1, NumberOfProvinces.findCircleNumDFS(isConnected));
        assertEquals(1, NumberOfProvinces.findCircleNumUnionFind(isConnected));
    }

    @Test
    void singleCity() {
        int[][] isConnected = {{1}};
        assertEquals(1, NumberOfProvinces.findCircleNumDFS(isConnected));
        assertEquals(1, NumberOfProvinces.findCircleNumUnionFind(isConnected));
    }

    @Test
    void chainConnected() {
        // 0-1, 1-2, 2-3 => one province
        int[][] isConnected = {
                {1, 1, 0, 0},
                {1, 1, 1, 0},
                {0, 1, 1, 1},
                {0, 0, 1, 1}
        };
        assertEquals(1, NumberOfProvinces.findCircleNumDFS(isConnected));
        assertEquals(1, NumberOfProvinces.findCircleNumUnionFind(isConnected));
    }

    @Test
    void twoPairsDisconnected() {
        // {0,1} and {2,3} => two provinces
        int[][] isConnected = {
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {0, 0, 1, 1},
                {0, 0, 1, 1}
        };
        assertEquals(2, NumberOfProvinces.findCircleNumDFS(isConnected));
        assertEquals(2, NumberOfProvinces.findCircleNumUnionFind(isConnected));
    }

    @Test
    void nullInput() {
        assertEquals(0, NumberOfProvinces.findCircleNumDFS(null));
        assertEquals(0, NumberOfProvinces.findCircleNumUnionFind(null));
    }

    @Test
    void emptyInput() {
        assertEquals(0, NumberOfProvinces.findCircleNumDFS(new int[0][0]));
        assertEquals(0, NumberOfProvinces.findCircleNumUnionFind(new int[0][0]));
    }
}
