package tree;

/**
 * Segment Tree, refer to geeks for geeks. One based heap array 2n space, iterative version.
 * https://codeforces.com/blog/entry/18051
 */
public class SegmentTreeAI {
    int[] tree;
    int n;

    /**
     * construction O(n) time, O(2n) space.
     *
     * @param nums original array.
     */
    public SegmentTreeAI(int[] nums) {
        if (nums.length > 0) {
            n = nums.length;
            tree = new int[n * 2];
            buildTree(nums);
        }
    }

    private void buildTree(int[] nums) {
        for (int i = n, j = 0; i < 2 * n; i++, j++) tree[i] = nums[j]; // leaf nodes [n,2n)
        for (int i = n - 1; i > 0; --i) tree[i] = tree[i * 2] + tree[i * 2 + 1]; // parent nodes (0,n-1], 1 based
    }

    /**
     * update/change a value of one array element, O(lgn) time.
     *
     * @param pos, index
     * @param val, value
     */
    void update(int pos, int val) {
        pos += n;
        tree[pos] = val;
        while (pos > 1) {
            pos >>= 1;
            tree[pos] = tree[2 * pos] + tree[2 * pos + 1]; // update parent
        }
    }

    /**
     * range sum query, inclusive, O(lgn) time.
     *
     * @param l left bound, inclusive
     * @param r right bound, inclusive
     * @return the sum
     */
    public int sumRange(int l, int r) {
        l += n; // get leaf with value 'l'
        r += n; // get leaf with value 'r'
        int sum = 0;
        for (l += n, r += n; l <= r; l >>= 1, r >>= 1) {
            if ((l & 1) == 1) sum += tree[l++];
            if ((r & 1) == 0) sum += tree[r--];
        }
        return sum;
    }
}
