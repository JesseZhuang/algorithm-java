package math;

/**
 * LeetCode 69, easy, tags: math, binary search.
 * <p>
 * Given a non-negative integer x, return the square root of x rounded down to the nearest integer.
 * The returned integer should be non-negative as well.
 * <p>
 * You must not use any built-in exponent function or operator.
 * <p>
 * For example, do not use pow(x, 0.5) in c++ or x ** 0.5 in python.
 * <p>
 * Example 1:
 * <p>
 * Input: x = 4
 * Output: 2
 * Explanation: The square root of 4 is 2, so we return 2.
 * Example 2:
 * <p>
 * Input: x = 8
 * Output: 2
 * Explanation: The square root of 8 is 2.82842..., and since we round it down to the nearest integer, 2 is returned.
 * <p>
 * Constraints:
 * <p>
 * 0 <= x <= 2^31 - 1
 * Hints:
 * Try exploring all integers. (Credits: @annujoshi)
 * Use the sorted property of integers to reduced the search space. (Credits: @annujoshi)
 * https://en.wikipedia.org/wiki/Integer_square_root#Using_only_integer_division
 */
public class Sqrt {
    // 1ms, 39.8Mb. binary search O(lgx) time, O(1) space.
    public int mySqrtBS(int x) {
        int left = 1, right = x, res = 0;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (mid <= x / mid) {
                left = mid + 1;
                res = mid;
            } else right = mid - 1;
        }
        return res;
    }

    // 1ms, 40.5 Mb, Newton's method, O(lgx) time, O(1) space.
    public int mySqrtNewton(int x) {
        if (x == 0) return 0;
        long res = x; // otherwise overflow x = 2147483647, expected 46340, actual -1073741824
        while (res > x / res) res = (res + x / res) / 2;
        return (int) res;
    }
}
