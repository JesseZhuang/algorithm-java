package math;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * LeetCode 202. Easy.
 * <p>
 * Write an algorithm to determine if a number is "happy".
 * <p>
 * A happy number is a number defined by the following process: Starting with
 * any positive integer, replace the number by the sum of the squares of its
 * digits, and repeat the process until the number equals 1 (where it will
 * stay), or it loops endlessly in a cycle which does not include 1. Those
 * numbers for which this process ends in 1 are happy numbers.
 * <p>
 * Example: 19 is a happy number
 * <p>
 * 1^2 + 9^2 = 82 8^2 + 2^2 = 68 6^2 + 8^2 = 100 1^2 + 0^2 + 0^2 = 1
 * <p>
 * Tags: Hash Table, Math.
 * <p>
 * <b>Summary:</b>
 * <p>
 * <ul>
 * <li><b>Floyd Cycle detection algorithm. Slow and fast pointers. Less
 * space.</b>
 * <li>Naive loop, O(n) time, O(1) space.
 * <li>Naive loop, hard code 4, 16, 37, 58, 89, 145, 42, 20, 4 as non happy
 * numbers. Unhappy numbers always include 4.
 * </ul>
 */
public class HappyNumber {
    public boolean isHappyNL2(int n) {
        if (n <= 0) return false;
        // can use n > 6 since [2,6] are all non happy
        while (n != 4) {
            int ss = 0;
            while (n >= 10) {
                ss += (n % 10) * (n % 10);
                n /= 10;
            }
            ss += n * n;
            if (ss == 1) return true;
            n = ss;
        }
        return false;
    }

    // since the unhappy numbers always includes 4
    // could hard code 4, 16, 37, 58, 89, 145, 42, 20
    public boolean isHappyNL(int n) {
        if (n <= 0) return false;
        int[] unhappySieve = {4, 16, 37, 58, 89, 145, 42, 20};
        Set<Integer> unhappyNums = Arrays.stream(unhappySieve).boxed()
                .collect(Collectors.toSet());
        while (unhappyNums.add(n)) {
            int ss = 0;
            while (n >= 10) {
                ss += (n % 10) * (n % 10);
                n /= 10;
            }
            ss += n * n;
            if (ss == 1) return true;
            n = ss;
            // System.out.println("round " + n);
        }
        return false;
    }

    private int digitSquareSum(int n) {
        int sum = 0, tmp;
        while (n > 0) {
            tmp = n % 10;
            sum += tmp * tmp;
            n /= 10;
        }
        return sum;
    }

    public boolean isHappySlowFastPt(int n) {
        int slow, fast;
        slow = fast = n;
        do {
            slow = digitSquareSum(slow);
            fast = digitSquareSum(fast);
            fast = digitSquareSum(fast);
            if (fast == 1) return true;
        } while (slow != fast);
        return false;
    }

    @Deprecated
    public boolean isHappyWrong(int n) {
        if (n <= 0) return false;
        HashSet<Integer> unhappyNums = new HashSet<>();
        while (unhappyNums.add(n)) {
            int ss = 0;
            // does not work for n = 100
            while (n >= 10) {
                ss += (n / 10) * (n / 10);
                n %= 10;
            }
            ss += n * n;
            if (ss == 1) return true;
            n = ss;
        }
        return false;
    }
}
