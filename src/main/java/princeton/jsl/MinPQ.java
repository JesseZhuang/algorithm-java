package princeton.jsl;

import java.util.Comparator;

/**
 * @param <Key> the generic type for key.
 */
public class MinPQ<Key extends Comparable<Key>> {
    Key[] pq;
    Comparator<Key> comparator;
    int size;

    public MinPQ(int N) {
        pq = (Key[]) new Object[N + 1];
        size = 0;
    }

    public MinPQ(int N, Comparator<Key> comparator) {
        this(N);
        this.comparator = comparator;
    }

    // construct pq in O(N) time
    public MinPQ(Key[] keys) {
        this(keys.length);
        for (int i = 1; i <= keys.length; i++) pq[i] = keys[i - 1];
        for (int i = keys.length / 2; i >= 1; i--) sink(i);
    }

    void insert(Key key) throws Exception {
        if (size == pq.length - 1) {
            throw new Exception("pq is full");
        }
        pq[++size] = key;
        swim(size);
    }

    Key delMin() throws Exception {
        if (size == 0) {
            throw new Exception("pq is empty");
        }
        Key result = pq[1];
        swap(1, size--);
        sink(1);
        return result;
    }

    void swap(int i, int j) {
        Key temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    void sink(int j) {// children are 2*j and 2*j+1
        while (2 * j <= size) {
            int k = 2 * j;
            if (k + 1 <= size && greater(k, k + 1)) {
                k++;
            }
            if (!greater(j, k)) {
                break;
            }
            swap(k, j);
            j = k;
        }
    }

    void swim(int j) {// parent j/2
        while (j / 2 >= 1 && greater(j / 2, j)) {
            swap(j, j / 2);
            j = j / 2;
        }
    }

    boolean greater(int i, int j) {
        if (comparator == null) {
            return pq[i].compareTo(pq[j]) > 0;
        } else {
            return comparator.compare(pq[i], pq[j]) > 0;
        }
    }
}
