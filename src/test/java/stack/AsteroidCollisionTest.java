package stack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class AsteroidCollisionTest {

    @Test
    void testExample1() {
        assertArrayEquals(new int[]{5, 10},
                AsteroidCollision.asteroidCollision(new int[]{5, 10, -5}));
    }

    @Test
    void testExample2() {
        assertArrayEquals(new int[]{},
                AsteroidCollision.asteroidCollision(new int[]{8, -8}));
    }

    @Test
    void testExample3() {
        assertArrayEquals(new int[]{10},
                AsteroidCollision.asteroidCollision(new int[]{10, 2, -5}));
    }

    @Test
    void testAllPositive() {
        assertArrayEquals(new int[]{1, 2, 3, 4},
                AsteroidCollision.asteroidCollision(new int[]{1, 2, 3, 4}));
    }

    @Test
    void testAllNegative() {
        assertArrayEquals(new int[]{-4, -3, -2, -1},
                AsteroidCollision.asteroidCollision(new int[]{-4, -3, -2, -1}));
    }

    @Test
    void testSingleElement() {
        assertArrayEquals(new int[]{5},
                AsteroidCollision.asteroidCollision(new int[]{5}));
    }

    @Test
    void testLargeDestroysMany() {
        assertArrayEquals(new int[]{-10},
                AsteroidCollision.asteroidCollision(new int[]{1, 2, 3, -10}));
    }

    @Test
    void testNoCollision() {
        assertArrayEquals(new int[]{-2, -1, 1, 2},
                AsteroidCollision.asteroidCollision(new int[]{-2, -1, 1, 2}));
    }

    @Test
    void testChainCollision() {
        assertArrayEquals(new int[]{},
                AsteroidCollision.asteroidCollision(new int[]{10, -5, -10}));
    }

    @Test
    void testAlternating() {
        assertArrayEquals(new int[]{},
                AsteroidCollision.asteroidCollision(new int[]{1, -1, 2, -2}));
    }

    @Test
    void testSurvivingMix() {
        assertArrayEquals(new int[]{-2, 2},
                AsteroidCollision.asteroidCollision(new int[]{-2, 1, -1, 2}));
    }
}
