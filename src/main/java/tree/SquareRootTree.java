package tree;

/**
 * Square root tree. Used in LeetCoe 307 range sum query.
 *
 * @see resources/tree.lc307_RSQ_Sqrt.png
 */
public class SquareRootTree {
    int[] block;
    int len; // block length
    int[] nums;

    /**
     * Construction O(n+sqrt(n)) space. Keep a reference of the input array. O(n) time.
     *
     * @param nums original array.
     */
    public SquareRootTree(int[] nums) {
        this.nums = nums;
        len = (int) Math.ceil(Math.sqrt(nums.length));
        block = new int[len]; // init fields important
        for (int i = 0; i < nums.length; i++) block[i / len] += nums[i];
    }

    /**
     * range sum, O(sqrt(n)) time.
     *
     * @param i left bound, inclusive
     * @param j right bound, inclusive
     * @return the sum
     */
    public int sumRange(int i, int j) {
        int res = 0;
        int start = i / len;
        int end = j / len;
        if (start == end) for (int k = i; k <= j; k++) res += nums[k];
        else {
            for (int k = i; k < (start + 1) * len; k++) res += nums[k];
            for (int k = start + 1; k < end; k++) res += block[k]; // use block for middle section
            for (int k = end * len; k <= j; k++) res += nums[k];
        }
        return res;
    }

    /**
     * update value of array, O(1) time
     *
     * @param i   the index
     * @param val the value
     */
    public void update(int i, int val) {
        int bInd = i / len; // block index
        block[bInd] = block[bInd] - nums[i] + val;
        nums[i] = val;
    }
}
