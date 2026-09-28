package dp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MinCostTicketsTest {
    MinCostTickets.Solution sol;

    @BeforeEach
    void setUp() {
        sol = new MinCostTickets.Solution();
    }

    @Test
    void testExample1() {
        assertEquals(11, sol.mincostTickets(new int[]{1, 4, 6, 7, 8, 20}, new int[]{2, 7, 15}));
    }

    @Test
    void testExample2() {
        assertEquals(17, sol.mincostTickets(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 30, 31}, new int[]{2, 7, 15}));
    }

    @Test
    void testSingleDay() {
        assertEquals(2, sol.mincostTickets(new int[]{1}, new int[]{2, 7, 15}));
    }

    @Test
    void testSevenConsecutiveDays() {
        assertEquals(7, sol.mincostTickets(new int[]{1, 2, 3, 4, 5, 6, 7}, new int[]{2, 7, 15}));
    }

    @Test
    void testThirtyDaysPassCheaper() {
        int[] days = new int[30];
        for (int i = 0; i < 30; i++) days[i] = i + 1;
        assertEquals(15, sol.mincostTickets(days, new int[]{2, 7, 15}));
    }

    @Test
    void testSparseDays() {
        assertEquals(6, sol.mincostTickets(new int[]{1, 100, 200}, new int[]{2, 7, 15}));
    }

    @Test
    void testOneDayAlwaysCheapest() {
        assertEquals(5, sol.mincostTickets(new int[]{1, 2, 3, 4, 5}, new int[]{1, 10, 100}));
    }

    @Test
    void testAllSameCost() {
        int[] days = new int[10];
        for (int i = 0; i < 10; i++) days[i] = i + 1;
        assertEquals(5, sol.mincostTickets(days, new int[]{5, 5, 5}));
    }

    @Test
    void testLastDay365() {
        assertEquals(10, sol.mincostTickets(new int[]{1, 365}, new int[]{5, 50, 200}));
    }
}
