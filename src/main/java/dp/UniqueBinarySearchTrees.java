package dp;

/**
 * LeetCode 96, medium. Tags: math, dynamic programming, tree, binary search tree.
 * <p>
 * Given an integer n, return the number of structurally unique BSTs which has exactly n nodes
 * of unique values from 1 to n.
 * <p>
 * Constraints: 1 <= n <= 19 (result fits in int).
 */
public final class UniqueBinarySearchTrees {

    private UniqueBinarySearchTrees() {
    }

    /**
     * Bottom-up DP using the Catalan recurrence.
     * dp[0] = dp[1] = 1; dp[n] = sum(dp[i-1] * dp[n-i]) for i in 1..n.
     *
     * @return number of unique BSTs with n nodes
     */
    public static int numTreesDP(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        if (n >= 1) dp[1] = 1;
        // O(n^2) time, O(n) space
        for (int nodes = 2; nodes <= n; nodes++) {
            for (int root = 1; root <= nodes; root++) {
                dp[nodes] += dp[root - 1] * dp[nodes - root];
            }
        }
        return dp[n];
    }

    /**
     * Direct Catalan number formula: C(n) = C(2n, n) / (n + 1).
     * Computed iteratively as c = c * 2 * (2i + 1) / (i + 2).
     *
     * @return number of unique BSTs with n nodes
     */
    public static int numTreesCatalan(int n) {
        long c = 1;
        // O(n) time, O(1) space
        for (int i = 0; i < n; i++) {
            c = c * 2 * (2 * i + 1) / (i + 2);
        }
        return (int) c;
    }
}
