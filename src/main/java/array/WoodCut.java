package array;

/**
 * LintCode 153, hard, tags: binary search.
 * <p>
 * Given n pieces of wood with length L[i] (integer array). Cut them into small pieces to guarantee you could have
 * equal or more than k pieces with the same length. What is the longest length you can get from the n pieces of wood?
 * Given L & k, return the maximum length of the small pieces.
 * <p>
 * The unit of length is centimeter.The length of the woods are all positive integers,you couldn't cut wood into
 * float length.If you couldn't get >= k pieces, return 0.
 * <p>
 * Example
 * Example 1
 * <p>
 * Input:
 * L = [232, 124, 456]
 * k = 7
 * Output: 114
 * Explanation: We can cut it into 7 pieces if any piece is 114 long, however we can't cut it into 7 pieces if
 * any piece is 115 long.
 * And for the 124 logs, the excess can be discarded and not used in its entirety.
 * Example 2
 * <p>
 * Input:
 * L = [1, 2, 3]
 * k = 7
 * Output: 0
 * Explanation: It is obvious we can't make it.
 * Challenge
 * O(n log Len), where Len is the longest length of the wood.
 */
@SuppressWarnings("unused")
public class WoodCut {
    public static class Solution {
        int[] L;
        int k;

        /**
         * @param L Given n pieces of wood with length L[i]
         * @param k An integer
         * @return The maximum length of the small pieces
         */
        public int woodCut(int[] L, int k) {
            this.L = L;
            this.k = k;
            int maxL = 0;
            for (int l : L) maxL = Math.max(maxL, l);
            int l = 1, r = maxL, res = 0;
            while (l <= r) {
                int mid = l + (r - l) / 2;
                if (check(mid)) {
                    res = mid;
                    l = mid + 1;
                } else r = mid - 1;
            }
            return res;
        }

        private boolean check(int mid) {
            int count = 0;
            for (int l : L) count += l / mid;
            return count >= k;
        }
    }
}
