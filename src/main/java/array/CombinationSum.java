package array;

import java.util.*;

/**
 * LeetCode 39, medium, tags: array, backtracking.
 * Given an array of distinct integers candidates and a target integer target, return a list of all
 * unique combinations of candidates where the chosen numbers sum to target. You may return the
 * combinations in any order. The same number may be chosen from candidates an unlimited number of times.
 *
 * Constraints:
 * 1 <= candidates.length <= 30
 * 2 <= candidates[i] <= 40
 * All elements of candidates are distinct.
 * 1 <= target <= 40
 */
public final class CombinationSum {
    private CombinationSum() {}

    /** Solution 1: Backtracking with sort + pruning. O(n^(t/m)) time, O(t/m) recursion depth.
     *  n = candidates.length, t = target, m = min(candidates). */
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<>();
        backtrack(candidates, target, 0, new ArrayList<>(), res);
        return res;
    }

    private static void backtrack(int[] c, int remaining, int start, List<Integer> path, List<List<Integer>> res) {
        if (remaining == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < c.length; i++) { // O(n) branches
            if (c[i] > remaining) break; // prune: sorted
            path.add(c[i]);
            backtrack(c, remaining - c[i], i, path, res); // same i: allow reuse
            path.remove(path.size() - 1);
        }
    }

    /** Solution 2: Iterative DP building combinations. O(n*t*k) time, O(t*C) space. */
    public static List<List<Integer>> combinationSumDP(int[] candidates, int target) {
        @SuppressWarnings("unchecked")
        List<List<Integer>>[] dp = new List[target + 1];
        for (int i = 0; i <= target; i++) dp[i] = new ArrayList<>();
        dp[0].add(new ArrayList<>());
        for (int c : candidates) { // O(n)
            for (int s = c; s <= target; s++) { // O(t)
                for (List<Integer> combo : dp[s - c]) {
                    List<Integer> newCombo = new ArrayList<>(combo);
                    newCombo.add(c);
                    dp[s].add(newCombo);
                }
            }
        }
        return dp[target];
    }
}
