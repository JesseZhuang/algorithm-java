package tree;

/**
 * Segment Tree, refer to geeks for geeks. One based heap array 2n space, iterative version.
 * https://codeforces.com/blog/entry/18051
 */
public class SegmentTreeAI {
    int[] tree;
    int l;

    /**
     * construction O(n) time, O(2n) space.
     *
     * @param nums original array.
     */
    public SegmentTreeAI(int[] nums) {
        if (nums.length > 0) {
            l = nums.length;
            tree = new int[l * 2];
            for (int i = l; i < 2 * l; i++) tree[i] = nums[i - l]; // leaf nodes [n,2n)
            for (int i = l - 1; i > 0; --i) tree[i] = tree[i * 2] + tree[i * 2 + 1]; // parent nodes (0,n-1], 1 based
        }
    }

    /**
     * update/change a value of one array element, O(lgn) time.
     *
     * @param pos, index
     * @param val, value
     */
    void update(int pos, int val) {
        pos += l;
        tree[pos] = val;
        while (pos > 1) {
            pos >>= 1;
            tree[pos] = tree[2 * pos] + tree[2 * pos + 1]; // update parent
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
        int sum = 0;
        for (l += this.l, r += this.l; l <= r; l >>= 1, r >>= 1) {
            if ((l & 1) == 1) sum += tree[l++]; // if on right branch, add and increment
            if ((r & 1) == 0) sum += tree[r--]; // if on left branch, add and increment
        }
        return sum;
    }
}
