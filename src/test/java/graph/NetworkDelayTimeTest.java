package graph;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NetworkDelayTimeTest {

    private final NetworkDelayTime tbt = new NetworkDelayTime();

    @Test
    void testExample1() {
        int[][] times = {{2, 1, 1}, {2, 3, 1}, {3, 4, 1}};
        assertEquals(2, tbt.networkDelayTimeDijkstra(times, 4, 2));
    }

    @Test
    void testExample2() {
        int[][] times = {{1, 2, 1}};
        assertEquals(1, tbt.networkDelayTimeDijkstra(times, 2, 1));
    }

    @Test
    void testUnreachable() {
        int[][] times = {{1, 2, 1}};
        assertEquals(-1, tbt.networkDelayTimeDijkstra(times, 2, 2));
    }

    @Test
    void testSingleNode() {
        int[][] times = {};
        assertEquals(0, tbt.networkDelayTimeDijkstra(times, 1, 1));
    }

    @Test
    void testMultiplePaths() {
        int[][] times = {{1, 2, 1}, {1, 3, 4}, {2, 3, 2}};
        assertEquals(3, tbt.networkDelayTimeDijkstra(times, 3, 1));
    }

    @Test
    void testDisconnected() {
        int[][] times = {{1, 2, 1}, {3, 2, 1}};
        assertEquals(-1, tbt.networkDelayTimeDijkstra(times, 3, 1));
    }
}
