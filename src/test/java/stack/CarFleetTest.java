package stack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CarFleetTest {
    private final CarFleet.Solution sol = new CarFleet.Solution();

    @Test
    void testExample1() {
        assertEquals(3, sol.carFleet(12, new int[]{10, 8, 0, 5, 3}, new int[]{2, 4, 1, 1, 3}));
    }

    @Test
    void testExample2() {
        assertEquals(1, sol.carFleet(10, new int[]{3}, new int[]{3}));
    }

    @Test
    void testExample3() {
        assertEquals(1, sol.carFleet(100, new int[]{0, 2, 4}, new int[]{4, 2, 1}));
    }

    @Test
    void testAllSameSpeed() {
        assertEquals(3, sol.carFleet(10, new int[]{1, 4, 7}, new int[]{2, 2, 2}));
    }

    @Test
    void testSingleCar() {
        assertEquals(1, sol.carFleet(100, new int[]{50}, new int[]{10}));
    }
}
