package stack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class DailyTemperaturesTest {

    @Test
    void testExample1() {
        assertArrayEquals(new int[]{1, 1, 4, 2, 1, 1, 0, 0},
                DailyTemperatures.dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73}));
    }

    @Test
    void testExample2() {
        assertArrayEquals(new int[]{1, 1, 1, 0},
                DailyTemperatures.dailyTemperatures(new int[]{30, 40, 50, 60}));
    }

    @Test
    void testExample3() {
        assertArrayEquals(new int[]{1, 1, 0},
                DailyTemperatures.dailyTemperatures(new int[]{30, 60, 90}));
    }

    @Test
    void testSingle() {
        assertArrayEquals(new int[]{0},
                DailyTemperatures.dailyTemperatures(new int[]{50}));
    }

    @Test
    void testDecreasing() {
        assertArrayEquals(new int[]{0, 0, 0, 0},
                DailyTemperatures.dailyTemperatures(new int[]{90, 80, 70, 60}));
    }

    @Test
    void testAllSame() {
        assertArrayEquals(new int[]{0, 0, 0},
                DailyTemperatures.dailyTemperatures(new int[]{70, 70, 70}));
    }
}
