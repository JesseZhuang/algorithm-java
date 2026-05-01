package dp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GridGameTest {

    @Test void example1() {
        assertEquals(4, GridGame.gridGame(new int[][]{{2, 5, 4}, {1, 5, 1}}));
    }

    @Test void example2() {
        assertEquals(4, GridGame.gridGame(new int[][]{{3, 3, 1}, {8, 5, 2}}));
    }

    @Test void example3() {
        assertEquals(7, GridGame.gridGame(new int[][]{{1, 3, 1, 15}, {1, 3, 3, 1}}));
    }
}
