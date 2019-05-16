package graph;

import edu.princeton.cs.algs4.CC;
import edu.princeton.cs.algs4.Graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Based on algs4 CC. Add new API for the components (list of vertices in that component).
 */
public class ConnectedComponents {
    public static List<List<Integer>> components(Graph g) {
        CC cc = new CC(g);
        List<List<Integer>> components = new ArrayList<>(cc.count());
        for(int i = 0; i < cc.count(); i++) components.add(new ArrayList<>());
        for(int v = 0; v < g.V(); v++) components.get(cc.id(v)).add(v);
        return components;
    }
}
