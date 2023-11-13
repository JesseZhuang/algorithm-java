package tree;

/**
 * Fenwick Tree or binary indexed tree.
 * Compute sum of first i elements and modify the ith element are both O(LogN) time.
 * Simple solution to calculate sum on the fly is O(N) for getSum and O(1) for update.
 *
 * @see edu.princeton.cs.algs4.FenwickTree
 */
public class BinaryIndexedTree {

    private int BITree[]; // O(n) space

    // O(nlgn) initialization time.
    public BinaryIndexedTree(int arr[]) {
        BITree = new int[arr.length + 1]; // 0th element is dummy
        for (int i = 0; i < arr.length; i++) update(i, arr[i]);
    }

    /**
     * get the sum for elements [0,index] in original array. O(lgn) time.
     * see tree.BITSum.png in src/main/resources
     *
     * @param index index to get sum for [0,index]
     * @return
     */
    public int getSum(int index) {
        int sum = 0; // Initialize result
        index = index + 1;
        while (index > 0) {
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
     * @param val   the delta change
     */
    public void update(int index, int val) {
        index = index + 1; // important, don't force +1 on client of this class
        while (index < BITree.length) {
            BITree[index] += val;
            index += index & -index; // no parenthesis needed
            // adds decimal value related to last 1 bit, 10(0b1010)->12(0b1100), 7(0b0111)->8(0b1000)
        }
    }
}
