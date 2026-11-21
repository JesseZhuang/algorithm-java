package string;

/**
 * <a href="https://leetcode.com/problems/minimum-deletions-to-make-string-balanced/">LeetCode 1653</a>.
 */
public final class MinimumDeletionsToMakeStringBalanced {
    private MinimumDeletionsToMakeStringBalanced() {}

    /**
     * One-pass dynamic programming. Time O(n), Space O(1).
     */
    public static int minimumDeletions(String s) {
        int deletions = 0;
        int bCount = 0;

        for (int i = 0; i < s.length(); i++) { // O(n) total: each character is processed once.
            char c = s.charAt(i);
            if (c == 'b') {
                bCount++;
            } else {
                // Delete this 'a', or delete all earlier 'b's.
                deletions = Math.min(deletions + 1, bCount);
            }
        }
        return deletions;
    }

    /**
     * Try every split between an all-'a' prefix and all-'b' suffix. Time O(n), Space O(n).
     */
    public static int minimumDeletionsBySplit(String s) {
        int n = s.length();
        int[] suffixACount = new int[n + 1]; // O(n) extra space for suffix counts.

        for (int i = n - 1; i >= 0; i--) { // O(n): build counts from right to left.
            suffixACount[i] = suffixACount[i + 1] + (s.charAt(i) == 'a' ? 1 : 0);
        }

        int prefixBCount = 0;
        int minDeletions = n;
        for (int split = 0; split <= n; split++) { // O(n): evaluate all n + 1 splits.
            minDeletions = Math.min(minDeletions, prefixBCount + suffixACount[split]);
            if (split < n && s.charAt(split) == 'b') {
                prefixBCount++;
            }
        }
        return minDeletions;
    }
}
