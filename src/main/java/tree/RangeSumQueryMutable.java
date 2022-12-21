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

    int[] BITree;// binary indexed tree, 1 based
    int[] nums;// copy of original array

    private SegmentTreeAR segmentTreeAR;

    public RangeSumQueryMutable(int[] nums) {
        segmentTreeAR = new SegmentTreeAR(nums);
        BITree = new int[nums.length + 1];
        this.nums = nums;
        for (int i = 0; i < nums.length; i++) updateBIT(i, nums[i]);
    }

    public void update(int index, int val) {
        segmentTreeAR.update(index, val);
        int delta = val - nums[index];
        nums[index] = val;
        updateBIT(index, delta);
    }


    public int sumRangeSegT(int left, int right) {
        return segmentTreeAR.sumRange(left, right);
    }


    private void updateBIT(int index, int delta) {
        index++;
        while (index < BITree.length) {
            BITree[index] += delta;
            index += index & (-index);
        }
    }

    // 83 ms, 71 Mb. O(n) time to build tree. O(LgN) time for update/rangeSum.
    public int sumRangeBIT(int left, int right) {
        return getSum(right) - getSum(left - 1);
    }

    private int getSum(int index) {
        index++;
        int sum = 0;
        while (index > 0) {
            sum += BITree[index];
            index -= index & (-index);
        }
        return sum;
    }

    // 124 ms, 72 Mb without the object overhead. build tree: O(n) time O(n) space. update/sumQ: O(lgn) time.
    static class SegmentTreeAR {
        private int[] heap;
        private int n;

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

    // array iterative version. 170ms, 136.5Mb.
    static class SegmentTreeA {
        int[] tree;
        int n;

        public SegmentTreeA(int[] nums) {
            if (nums.length > 0) {
                n = nums.length;
                tree = new int[n * 2];
                buildTree(nums);
            }
        }

        private void buildTree(int[] nums) {
            for (int i = n, j = 0; i < 2 * n; i++, j++) tree[i] = nums[j]; // leaf nodes
            for (int i = n - 1; i > 0; --i) tree[i] = tree[i * 2] + tree[i * 2 + 1]; // parent nodes
        }

        void update(int pos, int val) {
            pos += n;
            tree[pos] = val;
            while (pos > 0) {
                int left = pos;
                int right = pos;
                if (pos % 2 == 0) {
                    right = pos + 1;
                } else {
                    left = pos - 1;
                }
                // parent is updated after child is updated
                tree[pos / 2] = tree[left] + tree[right];
                pos /= 2;
            }
        }

        public int sumRange(int l, int r) {
            l += n; // get leaf with value 'l'
            r += n; // get leaf with value 'r'
            int sum = 0;
            while (l <= r) {
                if ((l % 2) == 1) {
                    sum += tree[l];
                    l++;
                }
                if ((r % 2) == 0) {
                    sum += tree[r];
                    r--;
                }
                l /= 2;
                r /= 2;
            }
            return sum;
        }
    }

    // see resources/tree.lc307_RSQ_Sqrt.png
    // 110 ms, 71.8 Mb. Time: initiation O(n), update O(1), rsq O(sqrt(n)). Space O(sqrt(n)).
    public static class SquareRoot {
        int[] sums;
        int len; // block length
        int[] nums;

        public SquareRoot(int[] nums) {
            this.nums = nums;
            len = (int) Math.ceil(Math.sqrt(nums.length));
            sums = new int[len];
            for (int i = 0; i < nums.length; i++) sums[i / len] += nums[i];
        }

        public int sumRange(int i, int j) {
            int sum = 0;
            int startBlock = i / len;
            int endBlock = j / len;
            if (startBlock == endBlock) for (int k = i; k <= j; k++) sum += nums[k];
            else {
                for (int k = i; k <= (startBlock + 1) * len - 1; k++) sum += nums[k];
                for (int k = startBlock + 1; k <= endBlock - 1; k++) sum += sums[k];
                for (int k = endBlock * len; k <= j; k++) sum += nums[k];
            }
            return sum;
        }

        public void update(int i, int val) {
            int sumsIndex = i / len;
            sums[sumsIndex] = sums[sumsIndex] - nums[i] + val;
            nums[i] = val;
        }
    }

}
