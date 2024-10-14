package bit;

import java.util.List;

/**
 * Biweekly 141, Q1 and Q2.
 * <p>
 * You are given an array nums of n prime integers. Construct array of length n such that bitwise OR of ans[i] and
 * ans[i]+1 is equal to nums[i]. Additionally, you must minimize each value of ans[i]. If not possible, set to -1.
 * <p>
 * Constraints:
 * 1 <= nums.length <=100, n
 * 2 <= nums[i] <= 1000, value k
 * <p>
 * Constraints Q2:
 * 1 <= nums.length <=100, n
 * * 2 <= nums[i] <= 10^9, value k
 */
@SuppressWarnings("unused")
public class MinBitwise {

    // n*ilog2(k), 1 not considering result space, otherwise n.
    // another solution try [0, num). nk, 1
    static class Solution {
        public int[] minBitwiseArray(List<Integer> nums) {
            int[] res = new int[nums.size()];
            for (int i = 0; i < nums.size(); i++) {
                int ans = -1, n = nums.get(i);
                // alternatively can try k in [1<<30..1], try erasing bit one at [30,0] position from n
                for (int k = Integer.highestOneBit(n); k > 0; k >>= 1) {
                    // same as (for int k=ilog2(n); k>=0; k--), erase with mask 1<<k instead of k
                    if ((n & k) == 0) continue;
                    int t = n ^ k; // erase 1 bit from left (most significant -> least)
                    if ((t | t + 1) == n) {
                        ans = t;
                        break;
                    }
                }
                res[i] = ans;
            }
            return res;
        }
    }

    // nk, 1.
    static class Solution2 {
        public int[] minBitwiseArray(List<Integer> nums) {
            int[] res = new int[nums.size()];
            for (int i = 0; i < nums.size(); i++) {
                int ans = -1, n = nums.get(i);
                for (int k = n / 2; k < n; k++) {
                    if ((k | k + 1) == n) {
                        ans = k;
                        break;
                    }
                }
                res[i] = ans;
            }
            return res;
        }
    }
}
