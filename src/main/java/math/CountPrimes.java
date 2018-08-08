package math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;

/**
 * LeetCode 204. Easy.
 * <p>
 * Count the number of prime numbers less than a non-negative number, n.
 * <p>
 * Tags: Hash Table, Math.
 * <p>
 * <b>Summary:</b>
 * <p>
 * <ul>
 * <li>trial division, dividing n by m [1,n^1/2] or primes in this range if
 * already known, number of primes less than n^1/2 is roughly (n^1/2)/ln(n^1/2),
 * so running time O(n^1.5), O(1) space.
 * <li>sieve of Eratosthenes, running time O(nLogLog n), space O(n), segmented
 * version space O(n^0.5/log n), skip even numbers is useful optimization.
 * <li>sieve of Atkin, O(n) running time, same space as Eratosthenes, but does
 * not outperform a Sieve of Eratosthenes with maximum practical wheel
 * factorization(more constant factor).
 * <li>primality tests deterministic (AKS O(log^(6+epsilon) n)) and elliptical
 * curve (O(log^(5+epsilon) n)) and probabilistic ones.
 * </ul>
 * <p>
 * <a href>https://primes.utm.edu/howmany.html</a>
 * <p>
 * <a href>https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes</a>
 */
public class CountPrimes {

    /**
     * 29 ms
     */
    public int countPrimesErato(int n) {
        if (n < 3) return 0;
        int count = n - 2;
        boolean[] isPrime = new boolean[n];
        Arrays.fill(isPrime, true);
        for (int i = 2; i * i < n; i++)
            if (isPrime[i]) {
                for (int j = i * i; j < n; j += i) {
                    if (isPrime[j]) {
                        isPrime[j] = false;
                        count--;
                    }
                }
            }
        return count;
    }

    /**
     * 14 ms
     */
    public int countPrimesSkipEven(int n) {
        if (n < 3) return 0;

        boolean[] notPrime = new boolean[n];
        int count = n / 2; // skip even numbers
        for (int i = 3; i * i < n; i += 2) {
            if (notPrime[i]) continue;
            for (int j = i * i; j < n; j += 2 * i) {
                if (!notPrime[j]) {
                    --count;
                    notPrime[j] = true;
                }
            }
        }
        return count;
    }

    /**
     * 22 ms
     */
    public int countPrimesBitSet(int n) {
        if (n < 3) return 0;
        BitSet bs = new BitSet(n);
        // bs.set(0); bs.set(1); set true for 0 and 1
        int count = n / 2;
        for (int i = 3; i * i < n; i += 2) {
            if (bs.get(i)) continue;
            for (int j = i * i; j < n; j += 2 * i) {
                if (!bs.get(j)) {
                    --count;
                    bs.set(j);
                }
            }
        }
        return count;
    }

    public List<Integer> primes(int n) {
        List<Integer> primes = new ArrayList<>();
        primes.add(2);
        if (n < 3) return primes;

        boolean[] notPrime = new boolean[n];
        int i;
        for (i = 3; i * i < n; i += 2) {
            if (notPrime[i]) continue;
            else primes.add(i);
            for (int j = i * i; j < n; j += 2 * i) if (!notPrime[j]) notPrime[j] = true;
        }
        for (; i < n; i += 2) if (!notPrime[i]) primes.add(i);
        return primes;
    }

    public static void main(String[] args) {
        CountPrimes countPrimes = new CountPrimes();
        System.out.println(countPrimes.primes(10));
        System.out.println(countPrimes.primes(100));
    }

}
