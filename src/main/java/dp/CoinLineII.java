package dp;

/**
 * LintCode 395, medium, tags: dp, game theory.
 * <p>
 * There are n coins with different value in a line. Two players take turns to take one or two coins from left
 * side until there are no more coins left. The player who take the coins with the most value wins.
 * <p>
 * Could you please decide the first player will win or lose?
 * <p>
 * If the first player wins, return true, otherwise return false.
 * <p>
 * Only $39.9 for the "Twitter Comment System Project Practice" within a limited time of 7 days!
 * <p>
 * WeChat Notes Twitter for more information（WeChat ID jiuzhangfeifei）
 * <p>
 * Example 1:
 * <p>
 * Input: [1, 2, 2]
 * Output: true
 * Explanation: The first player takes 2 coins.
 * Example 2:
 * <p>
 * Input: [1, 2, 4]
 * Output: false
 * Explanation: Whether the first player takes 1 coin or 2, the second player will gain more value.
 */
@SuppressWarnings("unused")
public class CoinLineII {
    // dp, n, 1.
    // sum[i] = values[i] + values[i+1] + ... + values[n-1]
    // f[i] = max( // max payer1 for coins[i,n-1]
    //    values[i] + (sum[i+1] - f[i+1]),                  // player1 take 1 coin
    //    values[i] + values[i+1] + (sum[i+2] - f[i+2])	    // player1 take 2 coins
    // );
}
