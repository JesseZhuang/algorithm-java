package binary_search;

import java.util.Arrays;

/**
 * LeetCode 2300, medium, tags: array, binary search, sorting.
 * <p>
 * You are given two positive integer arrays spells and potions, of length n and m respectively, where spells[i]
 * represents the strength of the ith spell and potions[j] represents the strength of the jth potion.
 * You are also given an integer success. A spell and potion pair is considered successful if the product of their
 * strengths is at least success.
 * <p>
 * Return an integer array pairs of length n where pairs[i] is the number of potions that will form a successful
 * pair with the ith spell.
 * <p>
 * Example 1:
 * Input: spells = [5,1,3], potions = [1,2,3,4,5], success = 7
 * Output: [4,0,3]
 * <p>
 * Example 2:
 * Input: spells = [3,1,2], potions = [8,5,8], success = 16
 * Output: [2,0,2]
 * <p>
 * Constraints:
 * n == spells.length
 * m == potions.length
 * 1 <= n, m <= 10^5
 * 1 <= spells[i], potions[j] <= 10^5
 * 1 <= success <= 10^10
 */
public final class SuccessfulPairs {

    private SuccessfulPairs() {
    }

    /**
     * Sort + Binary Search. O((m + n) log m) time, O(n) space (for result, sort is in-place).
     * For each spell, binary search for the smallest potion such that spell * potion >= success.
     * Use long to avoid overflow since success can be up to 10^10.
     */
    public static int[] successfulPairs(int[] spells, int[] potions, long success) {
        Arrays.sort(potions); // O(m log m)
        int n = spells.length, m = potions.length;
        int[] result = new int[n];
        for (int i = 0; i < n; i++) { // O(n log m)
            // find minimum potion value: ceil(success / spell)
            long minPotion = (success + spells[i] - 1) / spells[i]; // ceiling division
            // binary search for first index >= minPotion
            int lo = 0, hi = m;
            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;
                if (potions[mid] >= minPotion) {
                    hi = mid;
                } else {
                    lo = mid + 1;
                }
            }
            result[i] = m - lo;
        }
        return result;
    }
}
