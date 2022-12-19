package tree;

/**
 * segment tree array version.
 */
public class SegmentTreeA {
    static class Node {
        int sum;
        int min;
        int max;
        int lazy;
    }

    private Node[] heap;
    private int n;

    // initialization O(n) time, O(n) space.
    public SegmentTreeA(int[] nums) {
        n = nums.length;
        // 0 based heap, size is 2 * [2^ceil(Log2(n))] - 1. ceil(Log2(n): height of tree
        // 1 based heap, size is 2 * [2^floor(Log2(n))] + 1, O(n) space
        heap = new Node[2 * (int) (Math.pow(2.0, Math.ceil(Math.log(nums.length) / Math.log(2.0)))) - 1];
        build(nums, 0, 0, n - 1);
    }

    /**
     * recursive helper to build the segment tree. O(n) time build tree.
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
            heap[ci].sum = heap[2 * ci + 1].sum + heap[2 * ci + 2].sum;
            heap[ci].min = Math.min(heap[2 * ci + 1].min, heap[2 * ci + 2].min);
            heap[ci].max = Math.min(heap[2 * ci + 1].max, heap[2 * ci + 2].max);
        }
    }

    private int mid(int left, int right) {
        return left + (right - left) / 2;
    }

    public int rsq(int left, int right) {
        return rsq(0, left, right, 0, n - 1);
    }

    private int rsq(int ci, int left, int right, int sLeft, int sRight) {
        if (left <= sLeft && right >= sRight) return heap[ci].sum; // query range contains search range
        if (left > sRight || right < sLeft) return 0; // query range is outside
        int mid = mid(left, right);
        return rsq(2 * ci + 1, left, right, sLeft, mid) + rsq(2 * ci + 2, left, right, mid + 1, sRight);
    }

    public int rMinQ(int left, int right) {
        return 0;
    }

    public int rMaxQ(int left, int right) {
        return 0;
    }

    public void update(int left, int right, int delta) {

    }
}
