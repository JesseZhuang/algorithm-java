package graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * LeetCode 332. Hard. Tags: Graph, DFS, Eulerian Path.
 * <p>
 * Given a list of airline tickets represented as [from, to] pairs, reconstruct the itinerary starting from "JFK"
 * using all tickets exactly once. If multiple valid itineraries exist, return the lexicographically smallest one.
 * <p>
 * Uses Hierholzer's algorithm for finding an Eulerian path.
 */
public final class ReconstructItinerary {

    private ReconstructItinerary() {
    }

    /**
     * Solution 1: Recursive DFS (Hierholzer's algorithm).
     * Time O(E log E) — each edge visited once, PriorityQueue operations are O(log E).
     * Space O(E) — adjacency list + recursion stack.
     */
    public static List<String> findItinerary(List<List<String>> tickets) {
        Map<String, PriorityQueue<String>> graph = new HashMap<>(); // O(E) space for adjacency list
        for (List<String> ticket : tickets) {
            graph.computeIfAbsent(ticket.get(0), k -> new PriorityQueue<>()) // PriorityQueue gives lexical order
                    .add(ticket.get(1));
        }
        List<String> result = new ArrayList<>();
        dfs(graph, "JFK", result);
        Collections.reverse(result); // post-order reversal gives correct Eulerian path
        return result;
    }

    private static void dfs(Map<String, PriorityQueue<String>> graph, String airport, List<String> result) {
        PriorityQueue<String> neighbors = graph.get(airport);
        while (neighbors != null && !neighbors.isEmpty()) {
            dfs(graph, neighbors.poll(), result); // O(log E) poll from PriorityQueue
        }
        result.add(airport); // post-order: add after all outgoing edges exhausted
    }

    /**
     * Solution 2: Iterative stack (Hierholzer's algorithm).
     * Time O(E log E) — same complexity as recursive version.
     * Space O(E) — explicit stack replaces recursion stack.
     */
    public static List<String> findItinerary2(List<List<String>> tickets) {
        Map<String, PriorityQueue<String>> graph = new HashMap<>(); // O(E) space for adjacency list
        for (List<String> ticket : tickets) {
            graph.computeIfAbsent(ticket.get(0), k -> new PriorityQueue<>())
                    .add(ticket.get(1));
        }
        Deque<String> stack = new ArrayDeque<>(); // O(E) space for explicit stack
        List<String> result = new ArrayList<>();
        stack.push("JFK");
        while (!stack.isEmpty()) {
            String curr = stack.peek();
            PriorityQueue<String> neighbors = graph.get(curr);
            if (neighbors != null && !neighbors.isEmpty()) {
                stack.push(neighbors.poll()); // O(log E) poll, push next smallest destination
            } else {
                result.add(stack.pop()); // post-order: add when no more outgoing edges
            }
        }
        Collections.reverse(result); // reverse post-order to get correct path
        return result;
    }
}
