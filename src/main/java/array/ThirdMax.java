package array;

import util.IntArrayUtil;

import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeSet;

/**
 * LeetCode 414 Easy. Tags: Array, Sorting.
 * Given an integer array nums, return the third distinct maximum number in this array. If the third maximum
 * does not exist, return the maximum number.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [3,2,1]
 * Output: 1
 * Explanation:
 * The first distinct maximum is 3.
 * The second distinct maximum is 2.
 * The third distinct maximum is 1.
 * Example 2:
 * <p>
 * Input: nums = [1,2]
 * Output: 2
 * Explanation:
 * The first distinct maximum is 2.
 * The second distinct maximum is 1.
 * The third distinct maximum does not exist, so the maximum (2) is returned instead.
 * Example 3:
 * <p>
 * Input: nums = [2,2,3,1]
 * Output: 1
 * Explanation:
 * The first distinct maximum is 3.
 * The second distinct maximum is 2 (both 2's are counted together since they have the same value).
 * The third distinct maximum is 1.
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 104
 * -231 <= nums[i] <= 231 - 1
 * Find third (k th) distinct maximum in the array.
 * <p>
 * kth distinct Maximum
 * <p>
 * <ul>
 *     <ui>sort, O(NLgN) time, O(1) space</ui>
 *     <ui>min heap, O(NLgk + kLgk) time, O(k) space</ui>
 *     <ui>tree set, O(NLgk + Lgk) time, O(k) space</ui>
 *     <ui>quick select, set to dedupe, O(N) time, O(N) space</ui>
 * </ul>
 */
public class ThirdMax {
    /**
     * O(1) space, O(N) time.
     */
    public static int thirdMaxCandies(int[] candyNumbers) {
        int max = -1;
        int secondMax = -1;
        int thirdMax = -1;
        for (int i = 0; i < candyNumbers.length; i++) {
            if (candyNumbers[i] > max) {
                thirdMax = secondMax;
                secondMax = max;
                max = candyNumbers[i];
            } else if (candyNumbers[i] > secondMax && candyNumbers[i] < max) {
                thirdMax = secondMax;
                secondMax = candyNumbers[i];
            } else if (candyNumbers[i] > thirdMax && candyNumbers[i] < secondMax) {
                thirdMax = candyNumbers[i];
            }
        }
        if (thirdMax > -1) {
            return thirdMax;
        } else {
            return max;
        }
    }

    public int thirdMaxSort(int[] nums) {
        Arrays.sort(nums);
        // Reverse array to make it non-increasing.
        for (int index = 0; index < nums.length / 2; ++index) {
            int temp = nums[index];
            nums[index] = nums[nums.length - 1 - index];
            nums[nums.length - 1 - index] = temp;
        }
        int elemCounted = 1;
        int prevElem = nums[0];
        for (int index = 1; index < nums.length; ++index) {
            // Current element is different from previous.
            if (nums[index] != prevElem) {
                elemCounted += 1;
                prevElem = nums[index];
            }
            // If we have counted 3 numbers then return current number.
            if (elemCounted == 3) {
                return nums[index];
            }
        }
        // We never counted 3 distinct numbers, return largest number.
        return nums[0];
    }

    public int thirdMaxMinHeap(int[] nums) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        Set<Integer> taken = new HashSet<>();
        for (int index = 0; index < nums.length; ++index) {
            // If current number was already taken, skip it.
            if (taken.contains(nums[index])) {
                continue;
            }
            // If min heap already has three numbers in it.
            // Pop the smallest if current number is bigger than it.
            if (minHeap.size() == 3) {
                if (minHeap.peek() < nums[index]) {
                    taken.remove(minHeap.poll());
                    minHeap.add(nums[index]);
                    taken.add(nums[index]);
                }
            }
            // If min heap does not have three number we can push it.
            else {
                minHeap.add(nums[index]);
                taken.add(nums[index]);
            }
        }
        // 'nums' has only one distinct element it will be the maximum.
        if (minHeap.size() == 1) return minHeap.peek();
            // 'nums' has two distinct elements.
        else if (minHeap.size() == 2) {
            int firstNum = minHeap.poll();
            return Math.max(firstNum, minHeap.peek());
        }
        return minHeap.peek();
    }

    public int thirdMaxTreeSet(int[] nums) {
        TreeSet<Integer> sortedNums = new TreeSet<>();
        for (int num : nums) {
            if (sortedNums.contains(num)) continue;
            if (sortedNums.size() == 3) {
                // And the smallest element is smaller than current element.
                if (sortedNums.first() < num) {
                    // Then remove the smallest element and push the current element.
                    sortedNums.pollFirst();
                    sortedNums.add(num);
                }
            }
            // Otherwise push the current element of nums array.
            else {
                sortedNums.add(num);
            }
        }
        // If sorted set has three elements return the smallest among those 3.
        if (sortedNums.size() == 3) {
            return sortedNums.first();
        }
        // Otherwise return the biggest element of nums array.
        return sortedNums.last();
    }

    /**
     * O(N) time, O(N) space. Uses quick select.
     */
    public static int thirdMax2(int[] candyNumbers) {
        Set<Integer> set = new HashSet<>();
        int max = -1;
        for (int num : candyNumbers) {
            if (num > max) max = num;
            set.add(num);
        }
        candyNumbers = IntArrayUtil.unBoxIntegerArray(set.toArray(new Integer[0]));
        if (set.size() < 3) return max;
        else return QuickSelect.kthLargest(candyNumbers, 0, candyNumbers.length - 1, 3);
    }

    public static void main(String[] args) {
        int[][] candies = new int[][]{
                {3, 2, 1}, {1, 2}, {4, 3, 3, 2}, {}, {1, 2, 3}
                // 1,        2,      2,          -1,  1
        };
        for (int i = 0; i < candies.length; i++) {
            System.out.println(thirdMaxCandies(candies[i]));
            System.out.println("dedup with quick select");
            System.out.println(thirdMax2(candies[i]));
            System.out.println("done index: " + i);
        }

    }
}
