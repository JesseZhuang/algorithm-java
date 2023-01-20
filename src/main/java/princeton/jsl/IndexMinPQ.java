package princeton.jsl;

import java.util.Arrays;
import java.util.NoSuchElementException;

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
     * point index (client provided) to min heap rank, e.g., 1st indexed element is 3rd smallest
     * qp[pq[i]] = i, e.g., i = 1, pq[1] = 3, qp[qp[1]] = 1 (qp[3] = 1). keys[3] is the smallest.
     * pq[qp[i]] = i, e.g., i = 3, qp[3] = 1, pq[qp[3]] = 3 (pq[1] = 3).
     */
    private int[] qp;

    public IndexMinPQ(int N) {
        size = 0;
        maxSize = N;
        keys = (K[]) new Comparable[N + 1];
        pq = new int[N + 1];
        qp = new int[N + 1];
        Arrays.fill(qp, -1); // indicates
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * has client inserted element with this index?
     */
    public boolean contains(int index) {
        return qp[index] != -1;
    }

    public int size() {
        return size;
    }

    public void insert(int index, K key) {
        if (contains(index)) throw new IllegalArgumentException("index already inserted");
        size++;
        keys[index] = key;
        pq[index] = size;
        pq[size] = index;
        swim(size);
    }

    public int minIndex() {
        if (isEmpty()) throw new NoSuchElementException("pq is empty");
        return pq[1];
    }

    public K minKey() {
        if (isEmpty()) throw new NoSuchElementException("pq is empty");
        return keys[minIndex()];
    }

    public K delMin() {
        if (isEmpty()) throw new NoSuchElementException("pq is empty");
        int temp = pq[1];
        exch(1, size--);
        sink(1);
        qp[temp] = -1;
        K res = keys[pq[size + 1]];
        keys[pq[size + 1]] = null;
        return res;
    }

    public K keyOf(int i) {
        if (i < 0 || i >= maxSize) throw new IllegalArgumentException("invalid index");
        if (!contains(i)) throw new NoSuchElementException("index is not in the priority queue");
        else return keys[i];
    }

    // one use case is in Dijkistra SP, distance updated to be a smaller one
    public void changeKey(int i, K key) {
        if (i < 0 || i >= maxSize) throw new IllegalArgumentException();
        if (!contains(i)) throw new NoSuchElementException("index is not in the priority queue");
        keys[i] = key;
        swim(qp[i]); // if key decreased
        sink(qp[i]); // if key increased
    }

    public void delete(int index) {
        if (index < 0 || index >= maxSize) throw new IllegalArgumentException("invalid index");
        if (!contains(index)) throw new NoSuchElementException("index is not in the priority queue");
        int rank = qp[index];
        exch(rank, size--);
        swim(rank);
        sink(rank);
        keys[index] = null;
        qp[index] = -1;
    }

    private void swim(int k) {
        while (k > 1 && greater(k / 2, k)) {
            exch(k, k / 2);
            k /= 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= size) {
            int j = 2 * k;
            if (j < size && greater(j, j + 1)) j++; // find the smaller of the two(j,j+1) to exchange
            if (!greater(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    private void exch(int i, int j) {
        int temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
        qp[pq[i]] = i; // maintain invariants
        qp[pq[j]] = j;
    }

    private boolean greater(int i, int j) {
        return keys[pq[i]].compareTo(keys[pq[j]]) > 0;
    }
}
