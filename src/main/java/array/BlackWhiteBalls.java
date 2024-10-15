package array;

/**
 * LeetCode 2938, medium, tags: two pointers, string, array, greedy.
 * <p>
 * There are n balls on a table, each ball has a color black or white.
 * <p>
 * You are given a 0-indexed binary string s of length n, where 1 and 0 represent black and white balls, respectively.
 * <p>
 * In each step, you can choose two adjacent balls and swap them.
 * <p>
 * Return the minimum number of steps to group all the black balls to the right and all the white balls to the left.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "101"
 * Output: 1
 * Explanation: We can group all the black balls to the right in the following way:
 * - Swap s[0] and s[1], s = "011".
 * Initially, 1s are not grouped together, requiring at least 1 step to group them to the right.
 * Example 2:
 * <p>
 * Input: s = "100"
 * Output: 2
 * Explanation: We can group all the black balls to the right in the following way:
 * - Swap s[0] and s[1], s = "010".
 * - Swap s[1] and s[2], s = "001".
 * It can be proven that the minimum number of steps needed is 2.
 * Example 3:
 * <p>
 * Input: s = "0111"
 * Output: 0
 * Explanation: All the black balls are already grouped to the right.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= n == s.length <= 10^5
 * s[i] is either '0' or '1'.
 * <p>
 * Hint 1
 * Every 1 in the string s should be swapped with every 0 on its right side.
 * Hint 2
 * Iterate right to left and count the number of 0 that have already occurred, whenever you iterate on 1
 * add that counter to the answer.
 */
@SuppressWarnings("unused")
public class BlackWhiteBalls {
    // counter, n, 1. need to move white ball by swapping past all black balls left of it.
    // swap == cnt of black balls on the left
    static class Solution1 {
        public long minimumSteps(String s) {
            long res = 0;
            for (int i = 0, cnt = 0; i < s.length(); i++)
                if (s.charAt(i) == '1') cnt++;
                else res += cnt;
            return res;
        }
    }

    // two pointer, n, 1.
    static class Solution2 {
        public long minimumSteps(String s) {
            long res = 0;
            for (int i = 0, j = 0; i < s.length(); i++) // j pointer to idx where the white ball needs to swap to
                if (s.charAt(i) == '0') res += i - j++;
            return res;
        }
    }
}
