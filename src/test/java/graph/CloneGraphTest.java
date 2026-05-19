package graph;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import struct.graph.Node;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class CloneGraphTest {
    static CloneGraph tbt;

    @BeforeAll
    static void setup() {
        tbt = new CloneGraph();
    }

    @Test
    void testFourNodeCycle() {
        // adjList = [[2,4],[1,3],[2,4],[1,3]]
        int[][] adjList = {{2, 4}, {1, 3}, {2, 4}, {1, 3}};
        Node graph = buildGraph(adjList);
        verifyClone(graph, adjList);
    }

    @Test
    void testSingleNodeNoNeighbors() {
        // adjList = [[]]
        int[][] adjList = {{}};
        Node graph = buildGraph(adjList);
        verifyClone(graph, adjList);
    }

    @Test
    void testNullInput() {
        assertNull(tbt.cloneGraphDFS(null));
        assertNull(tbt.cloneGraphBFS(null));
    }

    @Test
    void testTwoConnectedNodes() {
        // adjList = [[2],[1]]
        int[][] adjList = {{2}, {1}};
        Node graph = buildGraph(adjList);
        verifyClone(graph, adjList);
    }

    @Test
    void testFullyConnected4Nodes() {
        // adjList = [[2,3,4],[1,3,4],[1,2,4],[1,2,3]]
        int[][] adjList = {{2, 3, 4}, {1, 3, 4}, {1, 2, 4}, {1, 2, 3}};
        Node graph = buildGraph(adjList);
        verifyClone(graph, adjList);
    }

    private void verifyClone(Node original, int[][] expectedAdj) {
        Node cloneDFS = tbt.cloneGraphDFS(original);
        assertNotNull(cloneDFS);
        assertNotSame(original, cloneDFS);
        assertEquals(toAdjList(original), toAdjList(cloneDFS));

        Node cloneBFS = tbt.cloneGraphBFS(original);
        assertNotNull(cloneBFS);
        assertNotSame(original, cloneBFS);
        assertEquals(toAdjList(original), toAdjList(cloneBFS));
    }

    /**
     * Build graph from adjacency list. Node values are 1-indexed.
     */
    private Node buildGraph(int[][] adjList) {
        int n = adjList.length;
        Node[] nodes = new Node[n + 1];
        for (int i = 1; i <= n; i++) nodes[i] = new Node(i);
        for (int i = 1; i <= n; i++) {
            for (int neighbor : adjList[i - 1]) {
                nodes[i].neighbors.add(nodes[neighbor]);
            }
        }
        return nodes[1];
    }

    /**
     * Convert graph to adjacency list map (val -> sorted neighbor vals) via BFS.
     */
    private Map<Integer, List<Integer>> toAdjList(Node node) {
        Map<Integer, List<Integer>> result = new TreeMap<>();
        if (node == null) return result;
        Queue<Node> queue = new ArrayDeque<>();
        Set<Integer> visited = new HashSet<>();
        queue.add(node);
        visited.add(node.val);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            List<Integer> neighbors = new ArrayList<>();
            for (Node n : cur.neighbors) {
                neighbors.add(n.val);
                if (visited.add(n.val)) queue.add(n);
            }
            Collections.sort(neighbors);
            result.put(cur.val, neighbors);
        }
        return result;
    }
}
