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
    // solution 1, merge sort, nlgn time, n space, 55ms, 64.43MB.
    public List<Integer> countSmaller1(int[] nums) {
        return Arrays.stream(Inversions.smallerCounts(nums)).boxed().collect(Collectors.toList());
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

    public static void main(String[] args) {
        int[][] tests = {
                {5, 2, 6, 1}, // 2,1,1,0
                {-1, -1}, // 0,0
                {1, 2, 7, 8, 5}, // 0,0,1,1,0
                {3, 4, 1, 2}, // 2,2,0,0
        };
        for (int[] n : tests) {
            System.out.println(Arrays.toString(smallerCounts(n)));
            System.out.println(edu.princeton.cs.algs4.Inversions.count(n)); // total of above, 4, 0, 2, 4
        }
    }
}
