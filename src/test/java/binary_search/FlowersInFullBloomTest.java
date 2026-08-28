package binary_search;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class FlowersInFullBloomTest {

    @Test
    void testExample1() {
        int[][] flowers = {{1, 6}, {3, 7}, {9, 12}, {4, 13}};
        int[] people = {2, 3, 7, 11};
        assertArrayEquals(new int[]{1, 2, 2, 2}, FlowersInFullBloom.fullBloomFlowers(flowers, people));
        assertArrayEquals(new int[]{1, 2, 2, 2}, FlowersInFullBloom.fullBloomFlowersSweep(flowers, people));
    }

    @Test
    void testExample2() {
        int[][] flowers = {{1, 10}, {3, 3}};
        int[] people = {3, 3, 2};
        assertArrayEquals(new int[]{2, 2, 1}, FlowersInFullBloom.fullBloomFlowers(flowers, people));
        assertArrayEquals(new int[]{2, 2, 1}, FlowersInFullBloom.fullBloomFlowersSweep(flowers, people));
    }

    @Test
    void testSingleFlowerSinglePerson() {
        int[][] flowers = {{5, 10}};
        int[] people = {4, 5, 7, 10, 11};
        assertArrayEquals(new int[]{0, 1, 1, 1, 0}, FlowersInFullBloom.fullBloomFlowers(flowers, people));
        assertArrayEquals(new int[]{0, 1, 1, 1, 0}, FlowersInFullBloom.fullBloomFlowersSweep(flowers, people));
    }

    @Test
    void testAllFlowersSameRange() {
        int[][] flowers = {{1, 5}, {1, 5}, {1, 5}};
        int[] people = {1, 3, 5, 6};
        assertArrayEquals(new int[]{3, 3, 3, 0}, FlowersInFullBloom.fullBloomFlowers(flowers, people));
        assertArrayEquals(new int[]{3, 3, 3, 0}, FlowersInFullBloom.fullBloomFlowersSweep(flowers, people));
    }

    @Test
    void testNoOverlap() {
        int[][] flowers = {{1, 2}, {4, 5}, {7, 8}};
        int[] people = {3, 6, 9};
        assertArrayEquals(new int[]{0, 0, 0}, FlowersInFullBloom.fullBloomFlowers(flowers, people));
        assertArrayEquals(new int[]{0, 0, 0}, FlowersInFullBloom.fullBloomFlowersSweep(flowers, people));
    }

    @Test
    void testPersonAtBoundary() {
        int[][] flowers = {{2, 5}};
        int[] people = {1, 2, 5, 6};
        assertArrayEquals(new int[]{0, 1, 1, 0}, FlowersInFullBloom.fullBloomFlowers(flowers, people));
        assertArrayEquals(new int[]{0, 1, 1, 0}, FlowersInFullBloom.fullBloomFlowersSweep(flowers, people));
    }
}
