package array;

import java.util.List;
import java.util.TreeMap;

/**
 * Shortest seek time first disk seek algorithm.
 */
@SuppressWarnings("unused")
public class SSTS {
    TreeMap<Integer, Integer> pCnt; // position -> cnt
    int s; // starting posiion

    public SSTS(int S) {
        this.s = S;
    }

    public int ssts(List<Integer> P) {
        return 1;
    }
}
