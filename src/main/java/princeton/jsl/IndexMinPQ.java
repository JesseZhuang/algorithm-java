package princeton.jsl;

/**
 * Indexed min heap. supports changing key weight, index does not change.
 *
 * @param <K> generic key type
 */
public class IndexMinPQ<K extends Comparable<K>> {
    private int maxSize;
    private int size;
    private K[] keys; // indexed keys, each key associated with an index
    private int[] pq; // keys[pq[1]] is min, keys[pq[...]] is a min heap
    /**
     * point index to min heap rank, e.g., 1st indexed element is 3rd smallest
     * qp[pq[i]] = i, e.g., i = 1, pq[1] = 3, qp[qp[1]] = 1 (qp[3] = 1).
     * pq[qp[i]] = i, e.g., i = 3, qp[3] = 1, pq[qp[3]] = 3 (pq[1] = 3).
     */
    private int[] qp;
}
