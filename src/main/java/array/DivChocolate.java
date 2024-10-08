package array;

/**
 * LeetCode 1231, LintCode 1817, hard, tags: array, binary search.
 * <p>
 * You have one chocolate bar that consists of some chunks. Each chunk has its own sweetness given by the
 * array sweetness.
 * <p>
 * You want to share the chocolate with your k friends so you start cutting the chocolate bar into k + 1 pieces
 * using k cuts, each piece consists of some consecutive chunks.
 * <p>
 * Being generous, you will eat the piece with the minimum total sweetness and give the other pieces to your friends.
 * <p>
 * Find the maximum total sweetness of the piece you can get by cutting the chocolate bar optimally.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: sweetness = [1,2,3,4,5,6,7,8,9], k = 5
 * Output: 6
 * Explanation: You can divide the chocolate to [1,2,3], [4,5], [6], [7], [8], [9]
 * Example 2:
 * <p>
 * Input: sweetness = [5,6,7,8,9,1,2,3,4], k = 8
 * Output: 1
 * Explanation: There is only one way to cut the bar into 9 pieces.
 * Example 3:
 * <p>
 * Input: sweetness = [1,2,2,1,2,2,1,2,2], k = 2
 * Output: 5
 * Explanation: You can divide the chocolate to [1,2,2], [1,2,2], [1,2,2]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= k < sweetness.length <= 10^4
 * 1 <= sweetness[i] <= 10^5
 * <p>
 * see {@link CapacityShipPackages}.
 */
@SuppressWarnings("unused")
public class DivChocolate {
    // nlg(sum), 1.
    static class Solution {
        int pieces(int[] sweetness, int mid) {
            int cur = 0, cnt = 0;
            for (int sw : sweetness) {
                cur += sw;
                if (cur >= mid) {
                    cnt++;
                    cur = 0;
                }
            }
            return cnt;
        }

        public int maximizeSweetness(int[] sweetness, int k) {
            int sum = 0;
            for (int s : sweetness) sum += s;
            int left = 0, right = sum;
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (pieces(sweetness, mid) < k + 1) right = mid;
                else left = mid + 1;
            }
            return left - 1; // exit loop mid==k+1, left=mid+1
        }
    }
}
