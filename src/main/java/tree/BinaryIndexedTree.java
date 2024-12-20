package tree;

/**
 * Fenwick Tree or binary indexed tree. Fenwick trees are an online data structure, meaning that you can add elements
 * to the end, just like an array, and it will still work. Segment trees do not have this property by default.
 * <p>
 * Compute sum of first i elements and modify the ith element are both O(LogN) time.
 * Simple solution to calculate sum on the fly is O(N) for getSum and O(1) for update.
 *
 * @see edu.princeton.cs.algs4.FenwickTree
 */
public class BinaryIndexedTree {

    private int BITree[]; // O(n) space


    public BinaryIndexedTree(int[] arr) {
        int l = arr.length + 1; // important, used as boundary in for loops below, bug: l=nums.length
        BITree = new int[l]; // 0th element is dummy
//        for (int i = 0; i < arr.length; i++) update(i, arr[i]); // O(nlgn) initialization time.
        // O(n) init time
        System.arraycopy(arr, 0, BITree, 1, l - 1);
        for (int i = 1; i < l; i++) { // update
            int p = i + (i & -i); // parenthesis important
            if (p < l) BITree[p] += BITree[i];
        }
    }

    /**
     * get the sum for elements [0,index] in original array. O(lgn) time.
     * see tree.BITSum.png in src/main/resources and @Tushar Roy on youtube
     * 0: dummy
     * 1: 0+2^0, starting index, sum of number of elements, sum[0]
     * 2: 0+2^1, sum[0,1]
     * 3: 2^1+2^0, sum[2]
     * 4: 0+2^2, sum[0,3]
     * to get sum[0,6] == sum tree[7,6,4], i.e., sum [6]+[4,5]+[0,3]
     *
     * @param index index to get sum for [0,index]
     * @return sum for [0,index]
     */
    public int getSum(int index) {
        int sum = 0; // Initialize result
        index = index + 1;
        while (index > 0) { // no =, 0 is dummy
            sum += BITree[index];
            index -= index & -index; // Move index to parent node
            // 12(0b1100)->8(0b1000), 10(0b1010)->8, remove last set bit
        }
        return sum;
    }

    /**
     * update index and all parents, O(lgn) time.
     * see tree.BITUpdate.png in src/main/resources
     *
     * @param index the index to update
     * @param delta the delta change
     */
    public void update(int index, int delta) {
        index = index + 1; // important, don't force +1 on client of this class
        while (index < BITree.length) {
            BITree[index] += delta;
            index += index & -index; // no parenthesis needed
            // adds decimal value related to last 1 bit, 10(0b1010)->12(0b1100), 7(0b0111)->8(0b1000)
        }
    }

    /**
     * range sum [i,j] inclusive, O(lgn) time.
     *
     * @param i left bound
     * @param j right bound
     * @return sum inclusive
     */
    public int rsq(int i, int j) {
        return getSum(j) - getSum(i - 1);
    }
}
