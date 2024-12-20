package bit;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * LeetCode 762, easy, tags: math, bit manipulation.
 * <p>
 * Given two integers left and right, return the count of numbers in the inclusive range [left, right] having a prime
 * number of set bits in their binary representation.
 * <p>
 * Recall that the number of set bits an integer has is the number of 1's present when written in binary.
 * <p>
 * For example, 21 written in binary is 10101, which has 3 set bits.
 * <p>
 * Example 1:
 * <p>
 * Input: left = 6, right = 10
 * Output: 4
 * Explanation:
 * 6  -> 110 (2 set bits, 2 is prime)
 * 7  -> 111 (3 set bits, 3 is prime)
 * 8  -> 1000 (1 set bit, 1 is not prime)
 * 9  -> 1001 (2 set bits, 2 is prime)
 * 10 -> 1010 (2 set bits, 2 is prime)
 * 4 numbers have a prime number of set bits.
 * Example 2:
 * <p>
 * Input: left = 10, right = 15
 * Output: 5
 * Explanation:
 * 10 -> 1010 (2 set bits, 2 is prime)
 * 11 -> 1011 (3 set bits, 3 is prime)
 * 12 -> 1100 (2 set bits, 2 is prime)
 * 13 -> 1101 (3 set bits, 3 is prime)
 * 14 -> 1110 (3 set bits, 3 is prime)
 * 15 -> 1111 (4 set bits, 4 is not prime)
 * 5 numbers have a prime number of set bits.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= left <= right <= 10^6
 * 0 <= right - left <= 10^4
 */
public class PrimeNSBinBR {
    // 10ms, 39.4Mb. O(n) time, O(1) space.
    // another method uses mask 665772 == 0b10100010100010101100 for the primes below
    // return IntStream.range(L, R+1).map(i -> 665772 >> Integer.bitCount(i) & 1).sum();
    public int countPrimeSetBits(int left, int right) {
        Set<Integer> primes = new HashSet<>(Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19)); // floor(log_2(10^6)) == 19
        int res = 0;
        while (left <= right)
            if (primes.contains(Integer.bitCount(left++))) res++;
        return res;
    }

    // 4ms, 39.4Mb.
    public int countPrimeSetBits2(int L, int R) {
        int res = 0;
        for (int x = L; x <= R; ++x)
            if (isSmallPrime(Integer.bitCount(x))) res++;
        return res;
    }

    public boolean isSmallPrime(int x) {
        return (x == 2 || x == 3 || x == 5 || x == 7 ||
                x == 11 || x == 13 || x == 17 || x == 19);
    }
}
