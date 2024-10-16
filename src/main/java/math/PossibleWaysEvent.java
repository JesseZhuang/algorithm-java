package math;

/**
 * LeetCode Biweekly 141, Q4, hard.
 * <p>
 * Given integers n, x, y. An event is held for n performers. Each is assigned to one of the
 * x stages. All performers assigned to the same stage will perform together as a band.
 * Though some stages might remain empty.
 * <p>
 * After all performances are completed, the jury will award each band a score in the range [1,y]. Return the total
 * number of possible ways the event can take place. Since the answer may be very large, return modulo 10^9+7.
 * <p>
 * Note that two events are considered different if either:
 * - any performer is assigned to a different stage.
 * - any band is awarded a different score.
 * <p>
 * See LeetCode 1692, distribute candies.
 */
@SuppressWarnings("unused")
public class PossibleWaysEvent {
    // nx+x, x.
    static class Solution {
        public int numberOfWays(int n, int x, int y) {
            int MOD = (int) (1e9 + 7);
            long[] dp = new long[x + 1];
            dp[0] = 1;
            for (int i = 1; i <= n; i++) { // performer
                long[] ndp = new long[x + 1];
                for (int j = 1; j <= x; j++) { // assign to one of the band/stage
                    ndp[j] = (ndp[j] + dp[j] * j) % MOD; // join one of the existing band
                    ndp[j] = (ndp[j] + dp[j - 1] * (x - j + 1)) % MOD; // join a stage create a new band
                }
                dp = ndp;
            }
            long res = 0, v = 1;
            for (int i = 1; i <= x; i++) {
                v = (v * y) % MOD;
                res = (res + v * dp[i]) % MOD;
            }
            return (int) res;
        }
    }
}
