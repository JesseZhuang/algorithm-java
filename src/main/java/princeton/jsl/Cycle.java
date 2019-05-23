package princeton.jsl;

import java.util.ArrayList;
import java.util.List;

public class Cycle {
    private List<Integer> cycle;
    private boolean[] marked;
    private int[] edgeTo;

    public Cycle(Graph G) {
        if (hasSelfLoop(G)) return;
        if (hasParallelEdges(G)) return;
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        for (int v = 0; v < G.V(); v++)
            if (!marked[v] && cycle == null) dfs(G, -1, v);
    }

    private boolean hasSelfLoop(Graph G) {
        for (int v = 0; v < G.V(); v++) {
            for (int w : G.adj(v)) {
                if (v == w) {
                    cycle = new ArrayList<>();
                    cycle.add(v);
                    cycle.add(v);
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hasParallelEdges(Graph G) {
        marked = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++) {
            // check for parallel edges incident to v
            for (int w : G.adj(v)) {
                if (marked[w]) {
                    cycle = new ArrayList<>();
                    cycle.add(v);
                    cycle.add(w);
                    cycle.add(v);
                    return true;
                }
                marked[w] = true;
            }
            // reset so marked[v] = false for all v
            for (int w : G.adj(v)) {
                marked[w] = false;
            }
        }
        return false;
    }

    private void dfs(Graph G, int u, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (cycle != null) return;
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, v, w);
            }
            // check for cycle (but disregard reverse of edge leading to v)
            else if (w != u) {
                cycle = new ArrayList<>();
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.add(x);
                }
                cycle.add(w);
                cycle.add(v);
            }
        }
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }
}
