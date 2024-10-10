package stack;

/**
 * LeetCode 1963, medium, tags: two pointers, string, stack, greedy.
 * <p>
 * You are given a 0-indexed string s of even length n. The string consists of exactly n / 2 opening
 * brackets '[' and n / 2 closing brackets ']'.
 * <p>
 * A string is called balanced if and only if:
 * <p>
 * It is the empty string, or
 * It can be written as AB, where both A and B are balanced strings, or
 * It can be written as [C], where C is a balanced string.
 * You may swap the brackets at any two indices any number of times.
 * <p>
 * Return the minimum number of swaps to make s balanced.
 * <p>
 * Example 1:
 * <p>
 * Input: s = "][]["
 * Output: 1
 * Explanation: You can make the string balanced by swapping index 0 with index 3.
 * The resulting string is "[[]]".
 * Example 2:
 * <p>
 * Input: s = "]]][[["
 * Output: 2
 * Explanation: You can do the following to make the string balanced:
 * - Swap index 0 with index 4. s = "[]][][".
 * - Swap index 1 with index 5. s = "[[][]]".
 * The resulting string is "[[][]]".
 * Example 3:
 * <p>
 * Input: s = "[]"
 * Output: 0
 * Explanation: The string is already balanced.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * n == s.length
 * 2 <= n <= 10^6
 * n is even.
 * s[i] is either '[' or ']'.
 * The number of opening brackets '[' equals n / 2, and the number of closing brackets ']' equals n / 2.
 * <p>
 * Hint 1
 * Iterate over the string and keep track of the number of opening and closing brackets on each step.
 * Hint 2
 * If the number of closing brackets is ever larger, you need to make a swap.
 * Hint 3
 * Swap it with the opening bracket closest to the end of s.
 */
@SuppressWarnings("unused")
public class MinSwapsBalance {
    // n, 1. int to track stack size. (unmatched left brackets count+1)/2
    static class Solution {
        public int minSwaps(String s) {
            int ss = 0; // stack size
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == '[') ss++;
                else if (ss > 0) ss--;
            }
            return (ss + 1) / 2;
        }
    }
}
