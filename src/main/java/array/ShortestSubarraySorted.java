package array;

/**
 * LeetCode 1574, medium.
 */
@SuppressWarnings("unused")
public class ShortestSubarraySorted {
    // n, 1.
    static class Solution {
        public int findLengthOfShortestSubarray(int[] A) {
            int r = A.length - 1;
            while (r > 0 && A[r] >= A[r - 1]) r--; // first non-sorted index
            int res = r, l = 0; // can remove A[0,r-1]
            while (l < r && (l == 0 || A[l - 1] <= A[l])) {
                // find r such that removing [l+1,r-1] will make the array sorted
                while (r < A.length && A[l] > A[r]) r++;
                res = Math.min(res, r - l - 1);
                l++;
            }
            return res;
        }
    }
}
