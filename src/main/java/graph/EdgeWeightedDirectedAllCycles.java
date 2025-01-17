package graph;

import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.Stack;

import java.util.Collection;
import java.util.HashSet;

/**
 * Compute all cycles instead of stopping at the first one.
 */
public class EdgeWeightedDirectedAllCycles {
    private boolean[] marked;             // marked[v] = has vertex v been marked?
    private DirectedEdge[] edgeTo;        // edgeTo[v] = previous edge on path to v
    private boolean[] onStack;            // onStack[v] = is vertex on the stack?
    private Collection<Stack<DirectedEdge>> cycles;    // directed cycle (or null if no such cycle)

    /**
     * Determines whether the edge-weighted digraph <tt>G</tt> has a directed cycle and,
     * if so, finds all cycles.
     *
     * @param G the edge-weighted digraph
     */
    public EdgeWeightedDirectedAllCycles(EdgeWeightedDigraph G) {
        marked = new boolean[G.V()];
        onStack = new boolean[G.V()];
        edgeTo = new DirectedEdge[G.V()];
        cycles = new HashSet<>();
        for (int v = 0; v < G.V(); v++)
            if (!marked[v]) dfs(G, v);

        // check that digraph has a cycle
        assert check(G);
    }

    // check that algorithm computes either the topological order or finds a directed cycle
    private void dfs(EdgeWeightedDigraph G, int v) {
        onStack[v] = true;
        marked[v] = true;
        Stack<DirectedEdge> cycle;
        for (DirectedEdge e : G.adj(v)) {
            int w = e.to();

            //found new vertex, so recur
            if (!marked[w]) {
                edgeTo[w] = e;
                dfs(G, w);
            }

            // trace back directed cycle
            else if (onStack[w]) {
                cycle = new Stack<>();
                while (e.from() != w) {
                    cycle.push(e);
                    e = edgeTo[e.from()];
                }
                cycle.push(e);
                cycles.add(cycle);
            }
        }

        onStack[v] = false;
    }

    /**
     * Does the edge-weighted digraph have a directed cycle?
     *
     * @return <tt>true</tt> if the edge-weighted digraph has a directed cycle,
     * <tt>false</tt> otherwise
     */
    public boolean hasCycle() {
        return !cycles.isEmpty();
    }

    /**
     * Returns a directed cycle if the edge-weighted digraph has a directed cycle,
     * and <tt>null</tt> otherwise.
     *
     * @return a directed cycle (as an iterable) if the edge-weighted digraph
     * has a directed cycle, and <tt>null</tt> otherwise
     */
    public Collection<Stack<DirectedEdge>> cycles() {
        return cycles;
    }


    // certify that digraph is either acyclic or has a directed cycle
    private boolean check(EdgeWeightedDigraph G) {

        // edge-weighted digraph is cyclic
        if (hasCycle()) {
            for (Iterable<DirectedEdge> cycle : cycles) {
                // verify cycle
                DirectedEdge first = null, last = null;
                for (DirectedEdge e : cycle) {
                    if (first == null) first = e;
                    if (last != null) {
                        if (last.to() != e.from()) {
                            System.err.printf("cycle edges %s and %s not incident\n", last, e);
                            return false;
                        }
                    }
                    last = e;
                }

                if (last.to() != first.from()) {
                    System.err.printf("cycle edges %s and %s not incident\n", last, first);
                    return false;
                }
            }
        }
        return true;
    }
}
