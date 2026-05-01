package graph;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RottingOrangesTest {

    @Test void example1() {
        assertEquals(4, RottingOranges.orangesRotting(new int[][]{
                {2, 1, 1}, {1, 1, 0}, {0, 1, 1}}));
    }

    @Test void example2() {
        assertEquals(-1, RottingOranges.orangesRotting(new int[][]{
                {2, 1, 1}, {0, 1, 1}, {1, 0, 1}}));
    }

    @Test void example3() {
        assertEquals(0, RottingOranges.orangesRotting(new int[][]{{0, 2}}));
    }
}
