package tree;

/**
 * segment tree, 0 based array, recursive.
 */
public class SegmentTreeAR {
    private int[] heap;
    private int n;

    /**
     * O(n) time, O(4n, 2 *[2^ceil(Log2(n))] - 1) space.
     *
     * @param nums original array
     */
    public SegmentTreeAR(int[] nums) {
        n = nums.length;
        heap = new int[2 * (int) (Math.pow(2.0, Math.ceil(Math.log(nums.length) / Math.log(2.0)))) - 1];
        build(nums, 0, 0, n - 1);
    }

    private void build(int[] nums, int ci, int left, int right) {
        if (left == right) heap[ci] = nums[left];
        else {
            int mid = mid(left, right);
            build(nums, 2 * ci + 1, left, mid);
            build(nums, 2 * ci + 2, mid + 1, right);
            updateCI(ci);
        }
    }

    private void updateCI(int ci) {
        heap[ci] = heap[2 * ci + 1] + heap[2 * ci + 2];
    }

    private int mid(int left, int right) {
        return left + (right - left) / 2;
    }

    public void update(int index, int val) {
        update(index, 0, 0, n - 1, val);
    }

    private void update(int index, int ci, int left, int right, int val) {
        if (left == right) {
            heap[ci] = val; // note not heap[left] = val, bug
            return;
        }
        int mid = mid(left, right);
        if (index <= mid) update(index, 2 * ci + 1, left, mid, val);
        else update(index, 2 * ci + 2, mid + 1, right, val);
        updateCI(ci);
    }

    public int sumRange(int left, int right) {
        return rq(0, left, right, 0, n - 1);
    }

    private int rq(int ci, int left, int right, int sLeft, int sRight) {
        if (left <= sLeft && right >= sRight) { // query range contains current search range
            return heap[ci];
        }
        if (left > sRight || right < sLeft) { // query range is outside
            return 0;
        }
        int mid = mid(sLeft, sRight);
        return rq(2 * ci + 1, left, right, sLeft, mid) +
                rq(2 * ci + 2, left, right, mid + 1, sRight);
    }
}
