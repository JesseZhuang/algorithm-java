package graph;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CheapestFlightsWithinKStopsTest {

    @Test
    void testExample1() {
        int[][] flights = {{0, 1, 100}, {1, 2, 100}, {2, 0, 100}, {1, 3, 600}, {2, 3, 200}};
        assertEquals(700, CheapestFlightsWithinKStops.findCheapestPriceBF(4, flights, 0, 3, 1));
        assertEquals(700, CheapestFlightsWithinKStops.findCheapestPriceBFS(4, flights, 0, 3, 1));
    }

    @Test
    void testExample2() {
        int[][] flights = {{0, 1, 100}, {1, 2, 100}, {0, 2, 500}};
        assertEquals(200, CheapestFlightsWithinKStops.findCheapestPriceBF(3, flights, 0, 2, 1));
        assertEquals(200, CheapestFlightsWithinKStops.findCheapestPriceBFS(3, flights, 0, 2, 1));
    }

    @Test
    void testZeroStops() {
        int[][] flights = {{0, 1, 100}, {1, 2, 100}, {0, 2, 500}};
        assertEquals(500, CheapestFlightsWithinKStops.findCheapestPriceBF(3, flights, 0, 2, 0));
        assertEquals(500, CheapestFlightsWithinKStops.findCheapestPriceBFS(3, flights, 0, 2, 0));
    }

    @Test
    void testNoPath() {
        int[][] flights = {{0, 1, 100}};
        assertEquals(-1, CheapestFlightsWithinKStops.findCheapestPriceBF(3, flights, 0, 2, 1));
        assertEquals(-1, CheapestFlightsWithinKStops.findCheapestPriceBFS(3, flights, 0, 2, 1));
    }

    @Test
    void testDirectFlight() {
        int[][] flights = {{0, 1, 50}};
        assertEquals(50, CheapestFlightsWithinKStops.findCheapestPriceBF(2, flights, 0, 1, 0));
        assertEquals(50, CheapestFlightsWithinKStops.findCheapestPriceBFS(2, flights, 0, 1, 0));
    }

    @Test
    void testKLimitsCheaperPath() {
        int[][] flights = {{0, 1, 100}, {1, 2, 100}, {2, 3, 100}, {1, 3, 500}};
        assertEquals(600, CheapestFlightsWithinKStops.findCheapestPriceBF(4, flights, 0, 3, 1));
        assertEquals(600, CheapestFlightsWithinKStops.findCheapestPriceBFS(4, flights, 0, 3, 1));
        assertEquals(300, CheapestFlightsWithinKStops.findCheapestPriceBF(4, flights, 0, 3, 2));
        assertEquals(300, CheapestFlightsWithinKStops.findCheapestPriceBFS(4, flights, 0, 3, 2));
    }
}
