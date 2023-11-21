package tree;

/**
 * Segment Tree, refer to geeks for geeks. One based heap array 2n space, iterative version.
 * https://codeforces.com/blog/entry/18051
 */
public class SegmentTreeAI {
    int[] tree;
    int len;

    /**
     * construction O(n) time, O(2n) space.
     *
     * @param nums original array.
     */
    public SegmentTreeAI(int[] nums) {
        if (nums.length > 0) {
            len = nums.length;
            tree = new int[len * 2]; // [1,2n) index will be in use
            for (int i = len; i < 2 * len; i++) tree[i] = nums[i - len]; // leaf nodes [n,2n)
            for (int i = len - 1; i > 0; --i) tree[i] = tree[i * 2] + tree[i * 2 + 1]; // parent nodes (0,n-1], 1 based
        }
    }

    /**
     * update/change a value of one array element, O(lgn) time.
     *
     * @param i,   index
     * @param val, value
     */
    void update(int i, int val) {
        i += len;
        tree[i] = val;
        while (i > 1) { // stop at 1
            i >>= 1;
            tree[i] = tree[2 * i] + tree[2 * i + 1]; // update parent
        }
    }

    /**
     * range sum query, inclusive, O(lgn) time.
     * comparing to binary indexed tree, easier for range sum
     *
     * @param l left bound, inclusive
     * @param r right bound, inclusive
     * @return the sum
     */
    public int sumRange(int l, int r) {
        int res = 0;
        for (l += this.len, r += this.len; l <= r; l >>= 1, r >>= 1) {
            if ((l & 1) == 1) res += tree[l++]; // if on right branch, add and increment
            if ((r & 1) == 0) res += tree[r--]; // if on left branch, add and increment
        }
        return res;
    }
}
