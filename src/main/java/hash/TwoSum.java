package hash;

import edu.princeton.cs.algs4.Merge;
import util.IntArrayUtil;

import java.util.Arrays;
import java.util.HashMap;

import static util.IntArrayUtil.boxIntArray;

/**
 * LeetCode 1. Easy.
 * <p>
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 * <p>
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * <p>
 * Example:
 * <p>
 * Given nums = [2, 7, 11, 15], target = 9,
 * <p>
 * Because nums[0] + nums[1] = 2 + 7 = 9,
 * return [0, 1].
 * <p>
 * <b>Summary:</b>
 * <p>
 * <ul>
 * <li><b>HashMap, O(N) time, O(N) space.</b>
 * <li>QuickSort then Binary Search(NlgN)/Two Pointer Search(N), O(NlgN) time, O(1) space.
 * <li>MergeSort then Binary Search(NlgN)/Two Pointer Search(N), O(NlgN) time, O(1) space.
 * <li>Radix array, O(R) space, O(n) time very fast, Subset Sum, wikipedia,
 */
public class TwoSum {

    public int[] twoSumRadixArray(int[] nums, int target) {
        int min = 0, max = 0;
        // first loop to find max and min integers
        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                min = nums[i];
                max = min;
            } else {
                if (nums[i] < min) min = nums[i];
                if (nums[i] > max) max = nums[i];
            }
        }
        // valid range for input integers relative to target
        int sMin = Math.max(min, target - max);
        int sMax = Math.min(max, target - min);
        // array to keep indices of valid input integers initialize with -1
        int radixSize = 1 + sMax - sMin; // Radix size R
        int[] arr = new int[radixSize];
        for (int i = 0; i < arr.length; i++) arr[i] = -1;

        // second loop
        int offset = -sMin;
        for (int i = 0; i < nums.length; i++) {
            // Skip if integer is not from a valid range
            if (nums[i] > sMax || nums[i] < sMin) continue;
            // if found valid X1 and corresponding element of indices array is still -1
            // then mark its pair X2 = target - X1 in indices array
            if (arr[nums[i] + offset] == -1) arr[target - nums[i] + offset] = i;
            else return new int[]{arr[nums[i] + offset], i};
        }
        return new int[]{0, 0};
    }

    public int[] twoSumMap(int[] nums, int target) {
        int[] result = new int[2];
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(target - nums[i])) {
                if (!map.containsKey(nums[i])) map.put(nums[i], i);
            } else return new int[]{map.get(target - nums[i]), i};
        }
        return result;
    }

    public int[] twoSumJavaQuickSort(int[] nums, int target) {
        int i = 0, j = 0;
        int[] copy = new int[nums.length];
        System.arraycopy(nums, 0, copy, 0, nums.length);
        Arrays.sort(copy);
        while (i < copy.length && (j = IntArrayUtil.binarySearchRange(
                copy, i + 1, copy.length - 1, target - copy[i])) == -1) i++;
        int[] res = new int[2];
        if (i == copy.length) return res;
        int count = 0;
        for (int k = 0; k < nums.length; k++) if (nums[k] == copy[i] || nums[k] == copy[j]) res[count++] = k;
        return res;
    }

    public int[] twoSumMergeSort1(int[] nums, int target) {
        int[] sortedIndexes = Merge.indexSort(boxIntArray(nums));
        return twoSum2Pointer(nums, target, sortedIndexes);
    }

    public int[] twoSumMergeSort2(int[] nums, int target) {
        int[] originalIndexes = mergeSort(nums);
        int[] indexes = twoSum2Pointer(nums, target);
        if (nums[indexes[0]] == nums[indexes[1]])
            return new int[]{originalIndexes[indexes[1]], originalIndexes[indexes[0]]};
        return new int[]{originalIndexes[indexes[0]], originalIndexes[indexes[1]]};
    }

    /**
     * Merge sort and return original indexes.
     *
     * @param n array to be sorted.
     * @return the original indexes of the sorted array.
     */
    public int[] mergeSort(int[] n) {
        int[] aux = new int[n.length];
        int[] mem = new int[n.length]; // remember position indexes
        int[] memAux = new int[n.length];
        for (int i = 0; i < n.length; i++) mem[i] = i;
        mergeSort(n, aux, mem, memAux, 0, n.length - 1);
        return mem;
    }

    private void mergeSort(int[] n, int[] aux, int[] mem, int[] memAux, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        mergeSort(n, aux, mem, memAux, lo, mid);
        mergeSort(n, aux, mem, memAux, mid + 1, hi);
        // merge step
        for (int k = lo; k <= hi; k++) {
            aux[k] = n[k];
            memAux[k] = mem[k];
        }
        for (int i = lo, j = mid + 1, k = lo; k <= hi; k++) {
            if (i > mid) {
                n[k] = aux[j];
                mem[k] = memAux[j++];
            } else if (j > hi) {
                n[k] = aux[i];
                mem[k] = memAux[i++];
            } else if (aux[i] < aux[j]) {
                n[k] = aux[i];
                mem[k] = memAux[i++];
            } else {
                n[k] = aux[j];
                mem[k] = memAux[j++];
            }
        }
    }

    private int[] twoSum2Pointer(int[] numbers, int target) {
        int[] result = new int[2];
        int lo = 0, hi = numbers.length - 1;
        while (lo < hi) {
            int sum = numbers[lo] + numbers[hi];
            if (sum > target) hi--;
            else if (sum < target) lo++;
            else return new int[]{lo, hi};
        }
        return result;
    }

    private int[] twoSum2Pointer(int[] numbers, int target, int[] sortedIndexes) {
        int[] result = new int[2];
        int lo = 0, hi = numbers.length - 1;
        while (lo < hi) {
            int sum = numbers[sortedIndexes[lo]] + numbers[sortedIndexes[hi]];
            if (sum > target) hi--;
            else if (sum < target) lo++;
            else return new int[]{lo, hi};
        }
        return result;
    }

}
