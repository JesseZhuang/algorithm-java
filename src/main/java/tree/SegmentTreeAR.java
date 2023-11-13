package tree;

import java.util.function.BiFunction;

/**
 * segment tree array recursive (AR) version with lazy propagation.
 */
public class SegmentTreeAR {
    static class Node {
        int sum;
        int min;
        int max;
        int lazy;
    }

    enum Operation {
        MIN(Math::min), MAX(Math::max), SUM(Integer::sum);

        BiFunction<Integer, Integer, Integer> op;

        Operation(BiFunction<Integer, Integer, Integer> op) {
            this.op = op;
        }

        private int apply(int a, int b) {
            return op.apply(a, b);
        }
    }

    private Node[] heap;
    private int n;

    // initialization O(n) time, O(n) space.
    public SegmentTreeAR(int[] nums) {
        n = nums.length;
        // 0 based heap, size is 2 * [2^ceil(Log2(n))] - 1. ceil(Log2(n): height of tree
        // 1 based heap, size is 2 * [2^floor(Log2(n))] + 1, O(n) space
        heap = new Node[2 * (int) (Math.pow(2.0, Math.ceil(Math.log(nums.length) / Math.log(2.0)))) - 1];
        build(nums, 0, 0, n - 1);
    }

    /**
     * recursive helper to build the segment tree. O(n) time build tree because merge operation is constant time.
     *
     * @param nums  original array
     * @param ci    index of current node in segment tree
     * @param left  left boundary, inclusive
     * @param right right boundary, inclusive
     */
    private void build(int[] nums, int ci, int left, int right) {
        heap[ci] = new Node();
        if (left == right) heap[ci].sum = heap[ci].min = heap[ci].max = nums[left];
        else {
            int mid = mid(left, right);
            build(nums, 2 * ci + 1, left, mid);
            build(nums, 2 * ci + 2, mid + 1, right);
            updateCI(ci);
        }
    }

    private void updateCI(int ci) {
        heap[ci].sum = heap[2 * ci + 1].sum + heap[2 * ci + 2].sum;
        heap[ci].min = Math.min(heap[2 * ci + 1].min, heap[2 * ci + 2].min);
        heap[ci].max = Math.max(heap[2 * ci + 1].max, heap[2 * ci + 2].max);
    }

    private int mid(int left, int right) {
        return left + (right - left) / 2;
    }

    /**
     * get range sum in O(LgN) time.
     *
     * @param left  left boundary
     * @param right right boundary
     * @return the range sum
     */
    public int rSumQ(int left, int right) {
        return rq(0, left, right, 0, n - 1, Operation.SUM);
    }

    private int rq(int ci, int left, int right, int sLeft, int sRight, Operation op) {
        if (left <= sLeft && right >= sRight) { // query range contains current search range
            switch (op) {
                case SUM:
                    return heap[ci].sum;
                case MAX:
                    return heap[ci].max;
                case MIN:
                    return heap[ci].min;
            }
        }
        if (left > sRight || right < sLeft) { // query range is outside
            switch (op) {
                case SUM:
                    return 0;
                case MAX:
                    return Integer.MIN_VALUE;
                case MIN:
                    return Integer.MAX_VALUE;
            }
        }
        int mid = mid(sLeft, sRight);
        propagate(ci, sLeft, mid, sRight);
        return op.apply(rq(2 * ci + 1, left, right, sLeft, mid, op),
                rq(2 * ci + 2, left, right, mid + 1, sRight, op));
    }

    public int rMinQ(int left, int right) {
        return rq(0, left, right, 0, n - 1, Operation.MIN);
    }

    public int rMaxQ(int left, int right) {
        return rq(0, left, right, 0, n - 1, Operation.MAX);
    }

    /**
     * Perform range update. Assume no lazy propagation, k elements to update, O(kLgN) time.
     * With lazy propagation, O(1) - O(kLgN) time.
     *
     * @param left  left boundary
     * @param right right boundary
     * @param delta the delta change to all array elements in range
     */
    public void update(int left, int right, int delta) {
        update(0, left, right, delta, 0, n - 1);
    }

    private void update(int ci, int left, int right, int delta, int sLeft, int sRight) {
        if (left > sRight || right < sLeft) return; // update range is out of search range
        if (left <= sLeft && right >= sRight) { // update range contains search range
            save(ci, delta, sLeft, sRight);
            return;
        }
        // update range intersects with search range
        int mid = mid(sLeft, sRight);
        propagate(ci, sLeft, mid, sRight);
        update(2 * ci + 1, left, right, delta, sLeft, mid);
        update(2 * ci + 2, left, right, delta, mid + 1, sRight);
        updateCI(ci);
    }

    /**
     * Saving the delta for now, to be propagated later.
     *
     * @param delta the delta change to save.
     * @param left  the left boundary for current heap node, inclusive
     * @param right the right boundary for the current heap node, inclusive
     */
    private void save(int ci, int delta, int left, int right) {
        heap[ci].sum += delta * (right - left + 1);
        heap[ci].min += delta;
        heap[ci].max += delta;
        if (left != right) heap[ci].lazy += delta; // set lazy if not at leaf
    }

    private void propagate(int ci, int left, int mid, int right) {
        if (heap[ci].lazy != 0) {
            save(2 * ci + 1, heap[ci].lazy, left, mid);
            save(2 * ci + 2, heap[ci].lazy, mid + 1, right);
            heap[ci].lazy = 0;
        }
    }
}
