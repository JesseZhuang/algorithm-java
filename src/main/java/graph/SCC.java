package graph;

import edu.princeton.cs.algs4.GabowSCC;
import edu.princeton.cs.algs4.KosarajuSharirSCC;
import edu.princeton.cs.algs4.TarjanSCC;

import java.util.ArrayList;
import java.util.List;

public class SCC {

    public static List<List<Integer>> components(KosarajuSharirSCC scc, int nV) {
        int[] id = new int[nV];
        for(int v = 0; v < nV; v++) id[v] = scc.id(v);
        return components(id, nV, scc.count());
    }

    public static List<List<Integer>> components(GabowSCC scc, int nV) {
        int[] id = new int[nV];
        for(int v = 0; v < nV; v++) id[v] = scc.id(v);
        return components(id, nV, scc.count());
    }

    public static List<List<Integer>> components(TarjanSCC scc, int nV) {
        int[] id = new int[nV];
        for(int v = 0; v < nV; v++) id[v] = scc.id(v);
        return components(id, nV, scc.count());
    }

    private static List<List<Integer>> components(int[] id, int nV, int count) {
        List<List<Integer>> result = new ArrayList<>(count);
        for (int i = 0; i < count; i++) result.add(i, new ArrayList<>());
        for(int v = 0; v < nV; v++) result.get(id[v]).add(v);
        return result;
    }

}
