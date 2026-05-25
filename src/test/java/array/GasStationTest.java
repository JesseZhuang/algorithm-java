package array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GasStationTest {

    GasStation gasStation = new GasStation();

    @Test
    void testExample1() {
        int[] gas = {1, 2, 3, 4, 5};
        int[] cost = {3, 4, 5, 1, 2};
        assertEquals(3, gasStation.canCompleteCircuit(gas, cost));
    }

    @Test
    void testExample2() {
        int[] gas = {2, 3, 4};
        int[] cost = {3, 4, 3};
        assertEquals(-1, gasStation.canCompleteCircuit(gas, cost));
    }

    @Test
    void testSingleStationPass() {
        int[] gas = {5};
        int[] cost = {3};
        assertEquals(0, gasStation.canCompleteCircuit(gas, cost));
    }

    @Test
    void testSingleStationFail() {
        int[] gas = {3};
        int[] cost = {5};
        assertEquals(-1, gasStation.canCompleteCircuit(gas, cost));
    }

    @Test
    void testSingleStationExact() {
        int[] gas = {4};
        int[] cost = {4};
        assertEquals(0, gasStation.canCompleteCircuit(gas, cost));
    }

    @Test
    void testAllZeros() {
        int[] gas = {0, 0, 0};
        int[] cost = {0, 0, 0};
        assertEquals(0, gasStation.canCompleteCircuit(gas, cost));
    }

    @Test
    void testLargeValues() {
        int[] gas = {10000, 0, 0, 0};
        int[] cost = {0, 5000, 3000, 2000};
        assertEquals(0, gasStation.canCompleteCircuit(gas, cost));
    }
}
