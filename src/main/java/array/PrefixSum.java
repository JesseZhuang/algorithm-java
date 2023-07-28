package array;

public class PrefixSum {
    private int[] sums; // O(n) space.

    /**
     * construct prefix sum. O(n) time.
     *
     * @param nums original array
     */
    public PrefixSum(int[] nums) {
        sums = new int[nums.length];
        sums[0] = nums[0];
        for (int i = 1; i < nums.length; i++) sums[i] = nums[i] + sums[i - 1];
    }

    /**
     * range sum query, O(1) time
     *
     * @param i left boundary, inclusive
     * @param j right boundary, inclusive
     * @return the range sum between i, j [i,j]
     */
    public int rsq(int i, int j) {
        if (i == 0) return sums[j];
        return sums[j] - sums[i - 1];
    }

}
