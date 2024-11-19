package array;

/**
 * LeetCode 1652, easy,
 */
@SuppressWarnings("unused")
public class DefuseBomb {
    // n, 1(n result). rolling sum, think about RabinKarp.
    static class Solution {
        public int[] decrypt(int[] code, int k) {
            int n = code.length, res[] = new int[n];
            if (k == 0) return res;
            int start = 1, end = k, sum = 0; // index [1,k]
            if (k < 0) { // index [n-|k|,n-1]
                start = n - Math.abs(k);
                end = n - 1;
            }
            for (int i = start; i <= end; i++) sum += code[i];
            for (int i = 0; i < n; i++) {
                res[i] = sum;
                sum -= code[(start++) % n];
                sum += code[(end++ + 1) % n];
            }
            return res;
        }
    }
}
