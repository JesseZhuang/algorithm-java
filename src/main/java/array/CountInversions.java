package array;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * LeetCode 315, hard, tags: array, binary search, divide and conquer, binary indexed tree, segment tree, merge sort,
 * ordered set.
 * <p>
 * Given an integer array nums, return an integer array counts where counts[i] is the number of smaller elements to
 * the right of nums[i].
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [5,2,6,1]
 * Output: [2,1,1,0]
 * Explanation:
 * To the right of 5 there are 2 smaller elements (2 and 1).
 * To the right of 2 there is only 1 smaller element (1).
 * To the right of 6 there is 1 smaller element (1).
 * To the right of 1 there is 0 smaller element.
 * Example 2:
 * <p>
 * Input: nums = [-1]
 * Output: [0]
 * Example 3:
 * <p>
 * Input: nums = [-1,-1]
 * Output: [0,0]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 10^5, n
 * -10^4 <= nums[i] <= 10^4, r for range, in this case , 2*10^4 about 1/5 of n
 */
public class CountInversions {
    static final int MAX = 10_000; // 10^4

    // solution 1, fenwick tree, nlgr time, r space. 32 ms, 55.77 Mb.
    // do not mention balanced bst, avl or red black non-trivial to implement, need to tolerate duplicate
    public static List<Integer> countSmallerBIT(int[] nums) {
        int l = nums.length;
        int[] res = new int[l];
        int[] n = new int[l];
        for (int i = 0; i < l; i++) n[i] = nums[i] + MAX; // convert to [0, 2*max]
        BIT tree = new BIT(2 * MAX + 1);
        for (int i = l - 1; i >= 0; i--) {
            tree.update(n[i], 1);
            res[i] = tree.getSum(n[i] - 1);
        }
        return Arrays.stream(res).boxed().collect(Collectors.toList());
    }

    // solution 3, segment tree, nlgr time, r space. 136ms, 59.43Mb.
    public static List<Integer> countSmallerST(int[] nums) {
        int l = nums.length;
        int[] res = new int[l];
        int[] n = new int[l];
        for (int i = 0; i < l; i++) n[i] = nums[i] + MAX; // convert to [0, 2*max]
        SegmentTree tree = new SegmentTree(2 * MAX + 1); // +1 important, see test class edge case
        for (int i = l - 1; i >= 0; i--) {
            tree.update(n[i]);
            res[i] = tree.getSum(n[i] - 1);
        }
        return Arrays.stream(res).boxed().collect(Collectors.toList());
    }

    // solution 2, merge sort, nlgn time, n space, 55ms, 64.43MB.
    public static List<Integer> countSmallerMS(int[] nums) {
        return Arrays.stream(Inversions.smallerCounts(nums)).boxed().collect(Collectors.toList());
    }

}

class SegmentTree {
    int[] tree;
    int n;

    SegmentTree(int n) {
        tree = new int[4 * n];
        this.n = n;
    }

    void update(int num) {
        update(num, 1, 1, 0, n - 1);
//        update(num, 1, 0, 0, n - 1);
    }

    void update(int num, int count, int i, int lo, int hi) { // update range [num,num]
        if (lo == hi) tree[i] += count;
        else { // else important
            int mid = lo + (hi - lo) / 2;
            if (num <= mid) update(num, count, 2 * i, lo, mid); // 1 based
            else update(num, count, 2 * i + 1, mid + 1, hi);
            tree[i] = tree[2 * i] + tree[2 * i + 1];

//            if (num <= mid) update(num, count, 2 * i + 1, lo, mid); // 0 based
//            else update(num, count, 2 * i + 2, mid + 1, hi);
//            tree[i] = tree[2 * i + 1] + tree[2 * i + 2];
        }
    }

    // total count for n[0, num]
    int getSum(int num) {
        return rsq(1, 0, num, 0, n - 1);
//        return rsq(0, 0, num, 0, n - 1);
    }

    int rsq(int i, int left, int right, int lo, int hi) {
        if (left > hi || right < lo) return 0;
        if (left <= lo && right >= hi) return tree[i];
        int mid = lo + (hi - lo) / 2;
        return rsq(2 * i, left, right, lo, mid) + rsq(2 * i + 1, left, right, mid + 1, hi);
//        return rsq(2 * i + 1, left, right, lo, mid) + rsq(2 * i + 2, left, right, mid + 1, hi);
    }
}

class BIT {
    int[] tree;

    BIT(int n) {
        tree = new int[n + 1];
    }

    void update(int i, int count) {
        i += 1;
        while (i < tree.length) {
            tree[i] += count;
            i += i & -i;
        }
    }

    // total count for values [0,i]
    int getSum(int i) {
        int res = 0;
        i += 1;
        while (i > 0) {
            res += tree[i];
            i -= i & -i;
        }
        return res;
    }
}

class Inversions {
    public static int[] smallerCounts(int[] nums) {
        int l = nums.length;
        int[] index = new int[l];
        int[] count = new int[l];
        int[] aux = new int[l];
        for (int i = 0; i < l; i++) index[i] = i;
        sort(nums, aux, index, count, 0, l - 1);
        return count;
    }

    public static void merge(int[] nums, int[] aux, int[] index, int[] count, int lo, int mid, int hi) {
        for (int k = lo; k <= hi; k++) aux[k] = index[k];
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) index[k] = aux[j++];
            else if (j > hi) index[k] = aux[i++];
                // reverse order to avoid quadratic time, merge 3,4 1,2 (quadratic) vs 4,3 2,1 (linear)
                // must compare with the array not being worked on
                // 1,2,7,8,5; 7,2,1,8,5 index (2,1,0,3,4); compare 7,8 take 8 -> (3,1,0,3,4)
            else if (nums[aux[i]] <= nums[aux[j]]) index[k] = aux[j++]; // ignore duplicates
            else {
                index[k] = aux[i++]; // taking from left
                count[index[k]] += hi - j + 1; // count += count of right half, use the working aux array
            }
        }
    }

    public static void sort(int[] nums, int[] aux, int[] index, int[] count, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(nums, aux, index, count, lo, mid);
        sort(nums, aux, index, count, mid + 1, hi);
        merge(nums, aux, index, count, lo, mid, hi);
        // nums[] 5,2,6,1 after merge sort, index[] 2,0,1,3 nums[index[]] is 6,5,2,1 sort in reverse order
    }

}
