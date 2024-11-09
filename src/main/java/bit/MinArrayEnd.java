package bit;

/**
 * LeetCode 3133, medium, tags:
 * <p>
 * You are given two integers n and x. You have to construct an array of positive integers nums of size n
 * where for every 0 <= i < n - 1, nums[i + 1] is greater than nums[i], and the result of the bitwise AND
 * operation between all elements of nums is x.
 * <p>
 * Return the minimum possible value of nums[n - 1].
 * <p>
 * Example 1:
 * <p>
 * Input: n = 3, x = 4
 * <p>
 * Output: 6
 * <p>
 * Explanation:
 * <p>
 * nums can be [4,5,6] and its last element is 6.
 * <p>
 * Example 2:
 * <p>
 * Input: n = 2, x = 7
 * <p>
 * Output: 15
 * <p>
 * Explanation:
 * <p>
 * nums can be [7,15] and its last element is 15.
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= n, x <= 10^8
 * <p>
 * Hint 1
 * Each element of the array should be obtained by “merging” x and v where v = 0, 1, 2, …(n - 1).
 * Hint 2
 * To merge x with another number v, keep the set bits of x untouched, for all the other bits, fill the set
 * bits of v from right to left in order one by one.
 * Hint 3
 * So the final answer is the “merge” of x and n - 1.
 */
@SuppressWarnings("unused")
public class MinArrayEnd {
    // @lee b(lgn), 1. split all bits of n-1 and fill into empty bits of x.
    static class Solution {
        public long minEnd(int n, int x) { // 3,4
            long res = x;
            n--; // reducing n by 1 to exclude x from the iteration
            for (long mask = 1; n > 0; mask <<= 1) {
                if ((mask & x) == 0) {
                    res |= (n & 1) * mask; // set the bit in result based on the least significant bit of n
                    n >>= 1;
                }
            }
            return res;
        }
    }

    // n,1.
    static class Solution2 {
        public long minEnd(int n, int x) {
            long res = x;
            while (--n > 0)
                res = (res + 1) | x;
            return res;
        }
    }
}
