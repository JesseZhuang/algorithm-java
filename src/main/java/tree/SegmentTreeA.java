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

    // initialization O(n) time, O(n) space.
    public SegmentTreeA(int[] nums) {
        // if 0 based heap, size is 2 * [2^ceil(Log2(n))] - 1. ceil(Log2(n): height of tree
        heap = new Node[2 * (int) (Math.pow(2.0, Math.ceil(Math.log(nums.length) / Math.log(2.0)))) - 1];
        build(nums, 1, 0, nums.length - 1);
    }

    /**
     * recursive helper to build the segment tree.
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
            build(nums, 2 * ci, left, mid);
            build(nums, 2 * ci + 1, mid + 1, right);
        }
    }

    private int mid(int left, int right) {
        return left + (right - left) / 2;
    }

    public int rsq(int left, int right) {
        return 0;
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
