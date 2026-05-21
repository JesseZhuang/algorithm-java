package heap;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TotalCostHireKWorkersTest {

    TotalCostHireKWorkers.Solution1 sol1 = new TotalCostHireKWorkers.Solution1();
    TotalCostHireKWorkers.Solution2 sol2 = new TotalCostHireKWorkers.Solution2();

    @Test
    void testExample1() {
        int[] costs = {17, 12, 10, 2, 7, 2, 11, 20, 8};
        assertEquals(11, sol1.totalCost(costs, 3, 4));
        assertEquals(11, sol2.totalCost(costs, 3, 4));
    }

    @Test
    void testExample2() {
        int[] costs = {1, 2, 4, 1};
        assertEquals(4, sol1.totalCost(costs, 3, 3));
        assertEquals(4, sol2.totalCost(costs, 3, 3));
    }

    @Test
    void testSingleElement() {
        int[] costs = {5};
        assertEquals(5, sol1.totalCost(costs, 1, 1));
        assertEquals(5, sol2.totalCost(costs, 1, 1));
    }

    @Test
    void testAllEqual() {
        int[] costs = {3, 3, 3, 3};
        assertEquals(6, sol1.totalCost(costs, 2, 2));
        assertEquals(6, sol2.totalCost(costs, 2, 2));
    }

    @Test
    void testOverlappingCandidates() {
        int[] costs = {10, 1, 10, 1};
        assertEquals(2, sol1.totalCost(costs, 2, 3));
        assertEquals(2, sol2.totalCost(costs, 2, 3));
    }

    @Test
    void testHireAll() {
        int[] costs = {2, 1, 3};
        assertEquals(6, sol1.totalCost(costs, 3, 1));
        assertEquals(6, sol2.totalCost(costs, 3, 1));
    }

    @Test
    void testCandidatesLargerThanArray() {
        int[] costs = {5, 4, 3, 2, 1};
        assertEquals(6, sol1.totalCost(costs, 3, 10));
        assertEquals(6, sol2.totalCost(costs, 3, 10));
    }
}
