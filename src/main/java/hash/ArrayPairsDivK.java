package hash;


import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * LeetCode 1497, medium, tags: array, hash table, counting.
 * <p>
 * Given an array of integers arr of even length n and an integer k.
 * <p>
 * We want to divide the array into exactly n / 2 pairs such that the sum of each pair is divisible by k.
 * <p>
 * Return true If you can find a way to do that or false otherwise.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: arr = [1,2,3,4,5,10,6,7,8,9], k = 5
 * Output: true
 * Explanation: Pairs are (1,9),(2,8),(3,7),(4,6) and (5,10).
 * Example 2:
 * <p>
 * Input: arr = [1,2,3,4,5,6], k = 7
 * Output: true
 * Explanation: Pairs are (1,6),(2,5) and(3,4).
 * Example 3:
 * <p>
 * Input: arr = [1,2,3,4,5,6], k = 10
 * Output: false
 * Explanation: You can try all possible pairs to see that there is no way to divide arr into 3 pairs each with sum divisible by 10.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * arr.length == n
 * 1 <= n <= 10^5
 * n is even.
 * -10^9 <= arr[i] <= 10^9
 * 1 <= k <= 10^5
 * <p>
 * Hint 1
 * Keep an array of the frequencies of ((x % k) + k) % k for each x in arr.
 * Hint 2
 * for each i in [0, k - 1] we need to check if freq[i] == freq[k - i]
 * Hint 3
 * Take care of the case when i == k - i and when i == 0
 */
public class ArrayPairsDivK {
    // solution 1, n, k. hashing/counting.
    static class Solution {
        public boolean canArrange(int[] arr, int k) {
            Map<Integer, Integer> remainderCount = new HashMap<>();

            // Store the count of remainders in a map.
            for (int i : arr) {
                int rem = ((i % k) + k) % k;
                remainderCount.put(rem, remainderCount.getOrDefault(rem, 0) + 1);
            }

            for (int i : arr) {
                int rem = ((i % k) + k) % k;

                // If the remainder for an element is 0, then the count
                // of numbers that give this remainder must be even.
                if (rem == 0) {
                    if (remainderCount.get(rem) % 2 == 1) return false;
                }
                // If the remainder rem and k-rem do not have the
                // same count then pairs can not be made.
                else if (
                        !Objects.equals(
                                remainderCount.get(rem),
                                remainderCount.get(k - rem)
                        )
                ) return false;
            }
            return true;
        }
    }

    // solution 2, sorting/2pointer. nlgn, lgn.
    static class Solution2 {

        public boolean canArrange(int[] arr, int k) {
            Integer[] array = new Integer[arr.length];
            for (int i = 0; i < arr.length; ++i) {
                array[i] = arr[i];
            }

            Arrays.sort(array, Comparator.comparingInt(a -> (k + (a % k)) % k));

            int start = 0, end = array.length - 1;
            while (start < end) {
                if (array[start] % k != 0) break;
                if (array[start + 1] % k != 0) return false;
                start = start + 2;
            }

            while (start < end) {
                if ((array[start] + array[end]) % k != 0) return false;
                start++;
                end--;
            }
            return true;
        }

    }
}
