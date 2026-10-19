package dp;

public final class QuestionsBrainpower {
    private QuestionsBrainpower() {}

    /** Backward DP. Time O(n), space O(n). */
    public static long mostPoints(int[][] questions) {
        int length = questions.length;
        long[] dp = new long[length + 1];
        for (int index = length - 1; index >= 0; index--) {
            int next = Math.min(length, index + questions[index][1] + 1);
            dp[index] = Math.max(dp[index + 1], questions[index][0] + dp[next]);
        }
        return dp[0];
    }
}
