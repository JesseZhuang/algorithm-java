package graph;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EventualSafeStatesTest {

    @Test
    void testExample1() {
        int[][] graph = {{1, 2}, {2, 3}, {5}, {0}, {5}, {}, {}};
        assertEquals(List.of(2, 4, 5, 6), EventualSafeStates.eventualSafeNodes(graph));
        assertEquals(List.of(2, 4, 5, 6), EventualSafeStates.eventualSafeNodesBFS(graph));
    }

    @Test
    void testExample2() {
        int[][] graph = {{1, 2, 3, 4}, {1, 2}, {3, 4}, {0, 4}, {}};
        assertEquals(List.of(4), EventualSafeStates.eventualSafeNodes(graph));
        assertEquals(List.of(4), EventualSafeStates.eventualSafeNodesBFS(graph));
    }

    @Test
    void testAllTerminal() {
        int[][] graph = {{}, {}, {}};
        assertEquals(List.of(0, 1, 2), EventualSafeStates.eventualSafeNodes(graph));
        assertEquals(List.of(0, 1, 2), EventualSafeStates.eventualSafeNodesBFS(graph));
    }

    @Test
    void testSingleNode() {
        int[][] graph = {{}};
        assertEquals(List.of(0), EventualSafeStates.eventualSafeNodes(graph));
        assertEquals(List.of(0), EventualSafeStates.eventualSafeNodesBFS(graph));
    }

    @Test
    void testSelfLoop() {
        int[][] graph = {{0}};
        assertEquals(List.of(), EventualSafeStates.eventualSafeNodes(graph));
        assertEquals(List.of(), EventualSafeStates.eventualSafeNodesBFS(graph));
    }

    @Test
    void testTwoNodeCycle() {
        int[][] graph = {{1}, {0}};
        assertEquals(List.of(), EventualSafeStates.eventualSafeNodes(graph));
        assertEquals(List.of(), EventualSafeStates.eventualSafeNodesBFS(graph));
    }

    @Test
    void testChain() {
        int[][] graph = {{1}, {2}, {}};
        assertEquals(List.of(0, 1, 2), EventualSafeStates.eventualSafeNodes(graph));
        assertEquals(List.of(0, 1, 2), EventualSafeStates.eventualSafeNodesBFS(graph));
    }

    @Test
    void testMixed() {
        int[][] graph = {{1}, {2}, {0}, {4}, {}};
        assertEquals(List.of(3, 4), EventualSafeStates.eventualSafeNodes(graph));
        assertEquals(List.of(3, 4), EventualSafeStates.eventualSafeNodesBFS(graph));
    }
}
