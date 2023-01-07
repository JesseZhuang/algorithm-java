package graph;

import edu.princeton.cs.algs4.GabowSCC;
import edu.princeton.cs.algs4.KosarajuSharirSCC;
import edu.princeton.cs.algs4.TarjanSCC;

import java.util.ArrayList;
import java.util.List;

public class SCC {

    // dfs reverse graph, with the reverse post order, dfs again and count
    public static List<List<Integer>> components(KosarajuSharirSCC scc, int nV) {
        int[] id = new int[nV];
        for (int v = 0; v < nV; v++) id[v] = scc.id(v);
        return components(id, nV, scc.count());
    }

    // two stacks, id[] to identify cycle
    public static List<List<Integer>> components(GabowSCC scc, int nV) {
        int[] id = new int[nV];
        for (int v = 0; v < nV; v++) id[v] = scc.id(v);
        return components(id, nV, scc.count());
    }

    // low[] to identify cycle, one stack
    public static List<List<Integer>> components(TarjanSCC scc, int nV) {
        int[] id = new int[nV];
        for (int v = 0; v < nV; v++) id[v] = scc.id(v);
        return components(id, nV, scc.count());
    }

    /**
     * gather the strongly connected components.
     *
     * @param id    the id array from the scc classes.
     * @param nV    number of vertexes
     * @param count number of scc
     * @return the components.
     */
    private static List<List<Integer>> components(int[] id, int nV, int count) {
        List<List<Integer>> result = new ArrayList<>(count);
        for (int i = 0; i < count; i++) result.add(i, new ArrayList<>());
        for (int v = 0; v < nV; v++) result.get(id[v]).add(v);
        return result;
    }

}
