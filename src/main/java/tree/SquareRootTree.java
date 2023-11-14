package tree;

/**
 * Square root tree. Used in LeetCoe 307 range sum query.
 *
 * @see resources/tree.lc307_RSQ_Sqrt.png
 */
public class SquareRootTree {
    int[] sums;
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
        sums = new int[len];
        for (int i = 0; i < nums.length; i++) sums[i / len] += nums[i];
    }

    /**
     * range sum, O(sqrt(n)) time.
     *
     * @param i left bound, inclusive
     * @param j right bound, inclusive
     * @return the sum
     */
    public int sumRange(int i, int j) {
        int sum = 0;
        int startBlock = i / len;
        int endBlock = j / len;
        if (startBlock == endBlock) for (int k = i; k <= j; k++) sum += nums[k];
        else {
            for (int k = i; k <= (startBlock + 1) * len - 1; k++) sum += nums[k];
            for (int k = startBlock + 1; k <= endBlock - 1; k++) sum += sums[k]; // use block for middle section
            for (int k = endBlock * len; k <= j; k++) sum += nums[k];
        }
        return sum;
    }

    /**
     * update value of array, O(1) time
     *
     * @param i   the index
     * @param val the value
     */
    public void update(int i, int val) {
        int sumsIndex = i / len;
        sums[sumsIndex] = sums[sumsIndex] - nums[i] + val;
        nums[i] = val;
    }
}
