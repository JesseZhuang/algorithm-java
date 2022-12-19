package array;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 303, easy, tags: array, design, prefix sum.
 * Given an integer array nums, find the sum of the elements between indices i
 * and j (i ≤ j), inclusive.
 * <p>
 * Example: Given nums = [-2, 0, 3, -5, 2, -1]
 * <p>
 * sumRange(0, 2) -> 1<br>
 * sumRange(2, 5) -> -1<br>
 * sumRange(0, 5) -> -3
 * <p>
 * Note: You may assume that the array does not change. There are many calls to
 * sumRange function.
 * <p>
 * <b>Summary:</b>
 * <p>
 * <ul>
 * <li>no need to save 2 dimensional data. That kind of computation is quadratic.
 * <li>Only need to save all accumulative sums from 0, sum range of 3-5 can be
 * computed as sum 0-5 minus sum 0-2, O(n) time pre-compute, O(1) query return,
 * O(n) space.
 * </ul>
 */
public class RangeSum {
    // Your object will be instantiated and called as such:
    // NumArray numArray = new NumArray(nums);
    // numArray.sumRange(0, 1);
    // numArray.sumRange(1, 2);

    private int[] sums;


    public RangeSum(int[] nums) {
        this.sums = new int[nums.length + 1];// prefix sum
        for (int i = 0; i < nums.length; i++) sums[i + 1] = nums[i] + sums[i];
    }

    public int sumRange(int i, int j) {
        // return Arrays.stream(nums, i, j + 1).sum();
        return sums[j + 1] - sums[i];
    }

    private List<Integer> accumSum;

    public RangeSum() {
        accumSum = new ArrayList<>();
        accumSum.add(0); // adding a dummy to simplify code
    }

    public int get(int index) {
//        if (accumSum.isEmpty()) throw new IllegalStateException("no integers available");
//        int size = accumSum.size();
//        if (index >= accumSum.size()) {
//            throw new IllegalArgumentException("index is out of bound");
//        }
//        if (size == 1) {
//            return accumSum.get(index);
//        } else return accumSum.get(index) - accumSum.get(index - 1);
        if (accumSum.size() == 1) throw new IllegalStateException("no integers available");
        if (index + 1 >= accumSum.size()) throw new IllegalArgumentException("index is out of bound");
        return accumSum.get(index + 1) - accumSum.get(index);
    }

    public void add(int n) {
//        int size = accumSum.size();
//        int prevSum = size == 0 ? 0 : accumSum.get(size - 1);
//        int newSum = prevSum + n;
//        accumSum.add(newSum);
        accumSum.add(accumSum.get(accumSum.size() - 1) + n);
    }

    // i, j are non negative and j > i
    public int sum(int i, int j) {
//        if (accumSum.isEmpty()) return 0;
//        if (i == 0) {
//            return accumSum.get(j);
//        } else return accumSum.get(j) - accumSum.get(i - 1);
        if (accumSum.isEmpty()) return 0;
        return accumSum.get(j + 1) - accumSum.get(i);
    }
}
