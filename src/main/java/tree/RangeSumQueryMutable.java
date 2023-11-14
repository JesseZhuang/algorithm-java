package tree;

/**
 * LeetCode 307, medium. Tags: Binary indexed tree, array, design, segment tree.
 * Given an integer array nums, handle multiple queries of the following types:
 * <p>
 * Update the value of an element in nums.
 * Calculate the sum of the elements of nums between indices left and right inclusive where left <= right.
 * Implement the NumArray class:
 * <p>
 * NumArray(int[] nums) Initializes the object with the integer array nums.
 * void update(int index, int val) Updates the value of nums[index] to be val.
 * int sumRange(int left, int right) Returns the sum of the elements of nums between indices left and right inclusive
 * (i.e. nums[left] + nums[left + 1] + ... + nums[right]).
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["NumArray", "sumRange", "update", "sumRange"]
 * [[[1, 3, 5]], [0, 2], [1, 2], [0, 2]]
 * Output
 * [null, 9, null, 8]
 * <p>
 * Explanation
 * NumArray numArray = new NumArray([1, 3, 5]);
 * numArray.sumRange(0, 2); // return 1 + 3 + 5 = 9
 * numArray.update(1, 2);   // nums = [1, 2, 5]
 * numArray.sumRange(0, 2); // return 1 + 2 + 5 = 8
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 3 * 104
 * -100 <= nums[i] <= 100
 * 0 <= index < nums.length
 * -100 <= val <= 100
 * 0 <= left <= right < nums.length
 * At most 3 * 104 calls will be made to update and sumRange.
 */
public class RangeSumQueryMutable {

    // solution 1, BIT, 70ms, 71Mb. init O(n) time, O(n) space. update/sumQ: O(lgn) time.

    // solution 3 (AR), 124 ms, 72 Mb. build tree: O(n) time O(n) space. update/sumQ: O(lgn) time.

    // solution 2, 110 ms, 71.8 Mb. Time: initiation O(n), update O(1), rsq O(sqrt(n)). Space O(n+sqrt(n)).

}

class NumArrayBIT {

    int[] tree;

    public NumArrayBIT(int[] nums) {
        int l = nums.length + 1;
        tree = new int[l];
        for (int i = 1; i < l; i++) tree[i] = nums[i - 1];
        for (int i = 1; i < l; i++) {
            int p = i + (i & -i);
            if (p < l) tree[p] += tree[i];
        }
    }

    public void update(int index, int val) {
        int i = index + 1;
        int prev = getSum(index) - getSum(index - 1), delta = val - prev;
        while (i < tree.length) {
            tree[i] += delta;
            i += i & -i;
        }
    }

    public int sumRange(int left, int right) {
        return getSum(right) - getSum(left - 1);
    }

    int getSum(int i) {
        i += 1;
        int res = 0;
        while (i > 0) {
            res += tree[i];
            i -= i & -i;
        }
        return res;
    }
}
