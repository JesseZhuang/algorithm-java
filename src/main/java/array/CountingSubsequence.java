package array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://www.spoj.com/problems/SUBSEQ/, can be a follow-up question to range sum query immutable.
 * <p>
 * "47 is the quintessential random number," states the 47 society. And there might be a grain of truth in that.
 * <p>
 * For example, the first ten digits of the Euler's constant are:
 * <p>
 * 2 7 1 8 2 8 1 8 2 8
 * And what's their sum? Of course, it is 47.
 * <p>
 * Try walking around with your eyes open. You may be sure that soon you will start discovering occurrences
 * of the number 47 everywhere.
 * <p>
 * Problem specification
 * You are given a sequence S of integers we saw somewhere in the nature. Your task will be to compute
 * how strongly does this sequence support the above claims.
 * <p>
 * We will call a continuous subsequence of S interesting if the sum of its terms is equal to 47.
 * <p>
 * E.g., consider the sequence S = (24, 17, 23, 24, 5, 47). Here we have two interesting continuous subsequences:
 * the sequence (23, 24) and the sequence (47).
 * <p>
 * Given a sequence S, find the count of its interesting subsequences.
 * <p>
 * Input specification
 * The first line of the input file contains an integer T specifying the number of test cases. Each test case
 * is preceded by a blank line.
 * <p>
 * The first line of each test case contains the length of a sequence N. The second line contains
 * N space-separated integers : the elements of the sequence.
 * <p>
 * Output specification
 * For each test case output a single line containing a single integer : the count of interesting
 * subsequences of the given sentence.
 * <p>
 * Example
 * Input:
 * 2
 * <p>
 * 13
 * 2 7 1 8 2 8 1 8 2 8 4 5 9
 * <p>
 * 7
 * 2 47 10047 47 1047 47 47
 * <p>
 * Output:
 * 3
 * 4
 * Note: the input file will not exceed 4MB.
 */
public class CountingSubsequence {
    PrefixSum prefixSum;
    Map<Integer, List<Integer>> sumCount;
    int arrayLength;

    // O(n) time, O(n) space init.
    public CountingSubsequence(int[] nums) {
        arrayLength = nums.length;
        prefixSum = new PrefixSum(nums);
        sumCount = new HashMap<>();
        for (int i = 0; i < arrayLength; i++) {
            int sum = prefixSum.rsq(0, i);
            sumCount.computeIfAbsent(sum, k -> new ArrayList<>());
            sumCount.get(sum).add(i);
        }
    }

    // O(n) time.
    public int subsequenceCount(int target) {
        int notStartFrom0Count = 0; // count for subsequences not starting from 0 index
        for (int i = 0; i < arrayLength; i++) {
            int diff = prefixSum.rsq(0, i) - target;
            if (sumCount.containsKey(diff))
                for (int j : sumCount.get(diff)) if (j < i) notStartFrom0Count++;
        }
        return sumCount.getOrDefault(target, new ArrayList<>()).size() + notStartFrom0Count;
    }

}
