package graph;

import struct.graph.Node;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

/**
 * LeetCode 133,medium, tags: hash table, breadth-first search, depth-first search, graph.
 * Given a reference of a node in a connected undirected graph.
 * <p>
 * Return a deep copy (clone) of the graph.
 * <p>
 * Each node in the graph contains a value (int) and a list (List[Node]) of its neighbors.
 * <pre>
 * class Node {
 *   public int val;
 *   public List<Node> neighbors;
 * }
 * </pre>
 * Test case format:
 * <p>
 * For simplicity, each node's value is the same as the node's index (1-indexed).
 * For example, the first node with val == 1, the second node with val == 2, and so on. The graph is represented
 * in the test case using an adjacency list.
 * <p>
 * An adjacency list is a collection of unordered lists used to represent a finite graph. Each list describes the
 * set of neighbors of a node in the graph.
 * <p>
 * The given node will always be the first node with val = 1. You must return the copy of the given node as a
 * reference to the cloned graph.
 * <p>
 * Example 1: https://assets.leetcode.com/uploads/2019/11/04/133_clone_graph_question.png
 * <p>
 * Input: adjList = [[2,4],[1,3],[2,4],[1,3]]
 * Output: [[2,4],[1,3],[2,4],[1,3]]
 * Explanation: There are 4 nodes in the graph.
 * 1st node (val = 1)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
 * 2nd node (val = 2)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
 * 3rd node (val = 3)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
 * 4th node (val = 4)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
 * Example 2: https://assets.leetcode.com/uploads/2020/01/07/graph.png
 * <p>
 * <p>
 * Input: adjList = [[]]
 * Output: [[]]
 * Explanation: Note that the input contains one empty list. The graph consists of only one node with val = 1,
 * and it does not have any neighbors.
 * Example 3:
 * <p>
 * Input: adjList = []
 * Output: []
 * Explanation: This an empty graph, it does not have any nodes.
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the graph is in the range [0, 100].
 * 1 <= Node.val <= 100
 * Node.val is unique for each node.
 * There are no repeated edges and no self-loops in the graph.
 * The Graph is connected and all nodes can be visited starting from the given node.
 */
public class CloneGraph {
    // solution 1, O(V+E, E<n^2) time, O(V) space. 25ms, 42.1Mb.
    public Node cloneGraphDFS(Node node) {
        Map<Integer, Node> valNode = new HashMap<>();
        return dfs(node, valNode); // note different from alg 4, do not check marked or not
    }

    private Node dfs(Node node, Map<Integer, Node> nodes) {
        if (node == null) return null;
        if (nodes.containsKey(node.val)) return nodes.get(node.val);
        Node res = new Node(node.val);
        nodes.put(node.val, res);
        for (Node n : node.neighbors) res.neighbors.add(dfs(n, nodes));
        return res;
    }

    // solution 2, O(V+E) time, O(V) space. 26ms, 42.1Mb.
    public Node cloneGraphBFS(Node node) {
        if (node == null) return null;
        Map<Integer, Node> valNode = new HashMap<>();
        Queue<Node> queue = new ArrayDeque<>(); // nodes from original graph
        queue.add(node);
        Node copy = new Node(node.val);
        valNode.put(node.val, copy);
        while (!queue.isEmpty()) {
            Node cur = queue.remove();
            for (Node n : cur.neighbors) {
                if (!valNode.containsKey(n.val)) {
                    queue.add(n);
                    valNode.put(n.val, new Node(n.val));
                }
                valNode.get(cur.val).neighbors.add(valNode.get(n.val)); // note not cur.neighbors.add
            }
        }
        return copy;
    }
}
