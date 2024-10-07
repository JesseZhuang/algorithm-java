package string;

import array.Permutation;

import java.util.stream.Collectors;

/**
 * LeetCode 3309, medium, weekly 418 Q1.
 * You are given an array of integers nums of size 3.
 * <p>
 * Return the maximum possible number whose binary representation can be formed by concatenating the binary
 * representation of all elements in nums in some order.
 * <p>
 * Note that the binary representation of any number does not contain leading zeros.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3]
 * <p>
 * Output: 30
 * <p>
 * Explanation:
 * <p>
 * Concatenate the numbers in the order [3, 1, 2] to get the result "11110", which is the binary representation of 30.
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [2,8,16]
 * <p>
 * Output: 1296
 * <p>
 * Explanation:
 * <p>
 * Concatenate the numbers in the order [2, 8, 16] to get the result "10100010000", which is the binary
 * representation of 1296.
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * nums.length == 3
 * 1 <= nums[i] <= 127
 * <p>
 * Hint 1
 * How many possible concatenation orders are there?
 */
@SuppressWarnings("unused")
public class MaxBinaryConcat {
    public int maxGoodNumber(int[] nums) {
        var p = new Permutation().permuteI(nums);
        // list of int -> to binary string then concat, then parse back to int, take max
        return p.stream().map(l -> l.stream().map(Integer::toBinaryString).collect(Collectors.joining()))
                .mapToInt(s -> Integer.parseInt(s, 2)).max().orElseThrow();
    }
}
