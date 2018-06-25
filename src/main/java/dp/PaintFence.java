package dp;

/**
 * <p>
 * LeetCode 276. Easy.
 * <p>
 * There is a fence with n posts, each post can be painted with one of the k
 * colors. You have to paint all the posts such that no more than two adjacent
 * fence posts have the same color. Return the total number of ways you can
 * paint the fence.
 * <p>
 * Tag: Dynamic Programming.
 * <p>
 * <b>Summary:</b>
 * <p>
 * <ul>
 * <li><b>DP, O(N) time, O(1) space. 0 ms, 15%.</b>
 * <li>Permutation sum, basically same with dp.
 * </ul>
 *
 * @author Zexi Jesse Zhuang
 */
public class PaintFence {

    public int numWaysDP(int n, int k) {
        if (n == 0) return 0;
        if (n == 1) return k;
        int sameColor = k; //past two posts same color (P2S)
        int diffColor = k * (k - 1); //past two posts different color (P2D)
        for (int i = 3; i <= n; i++) {
            int temp = diffColor; //save diffColor
            // if P2S, current post cannot be that color, k-1 possibilities to become P2D
            // if P2D, current post also k-1 possibilities to become P2D
            diffColor = (sameColor + diffColor) * (k - 1);
            // to become P2S
            sameColor = temp;
        }
        // System.out.println("same " + sameColor + " diff " + diffColor);
        return sameColor + diffColor;
    }

    @Deprecated
    public int numWaysPerm2(int n, int k) {
        if (n == 0) return 0;
        if (n == 1) return k;
        if (k == 2) return (int) (Math.pow(2, n) - (n - 1) * (n - 2));
        // each post k possibilities
        int allPossiblePermutations = pow(k, n);
        int atLeastTwoPostsSameColor = (n - 2) * k / (k - 2) * (int) Math.pow(k - 1, n - 2);
        int moreThanTwoPostsSameColor = k / (k - 2) * (1 - (int) Math.pow(k - 1, n - 2)) / (1 - (k - 1));
        // still not correct
        return allPossiblePermutations - atLeastTwoPostsSameColor + moreThanTwoPostsSameColor;
    }

    @Deprecated
    public int numWaysPermWrong(int n, int k) {
        // failed for 4, 2, can have 1122 or 2211
        if (n == 0) return 0;
        if (n == 1) return k;
        return (int) (k * Math.pow(k - 1, n - 1)
                + (n - 1) * k * Math.pow(k - 1, n - 2));
    }

    @Deprecated
    public int numWaysPerm(int n, int k) {
        if (n == 0) return 0;
        if (n == 1) return k;
        if (k == 2) return (int) (Math.pow(2, n) - (n - 1) * (n - 2));
        // missed cases where more than one triplet, quadruples, ...
        return pow(k, n) - (((n - 2) * pow(k - 1, n - 2)
                - (1 - pow(k - 1, n - 2)) / (1 - (k - 1))) * k / (k - 2));
    }

    private int pow(int base, int power) {
        int res = 1;
        if (power == 0) return res;
        while (power > 0) {
            if ((power & 1) == 1) res = base * res;
            power >>= 1;
            base *= base;
        }
        return res;
    }
}
