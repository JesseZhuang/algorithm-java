package array;

/**
 * LeetCode 4. Hard. Tags: Array, Binary Search, Divide and Conquer.
 * <p>
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 * <p>
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 * <p>
 * You may assume nums1 and nums2 cannot be both empty.
 * <pre>
 * Example 1:
 *
 * nums1 = [1, 3]
 * nums2 = [2]
 * </pre>
 * The median is 2.0
 * <pre>
 * Example 2:
 *
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * </pre>
 * The median is (2 + 3)/2 = 2.5.
 * <p>
 * <b>Summary:</b>
 * <p>
 * For 1 array of length N, 2N+1 possible split positions to split the array into two halves. For median,
 * split is always at Nth position. Split position can be a '#' or an array element in example below.
 * L = array[(N-1)/2], R = array[N/2], L == R when N is odd.
 * <pre>
 * [6 9 13 18], N = 4  ->   [# 6 # 9 # 13 # 18 #], L = array[(4-1)/2] = 9, R = array[4/2] = 13
 * [6 9 11 13 18], N = 5 ->   [# 6 # 9 # 11 # 13 # 18 #], L = array[(5-1)/2] = 11, R = array[5/2] = 11
 * </pre>
 * For both even or odd cases above, we can have L = array[(N-1)/2], R = array[N/2]
 * <ul>
 * <li>binary search, O(log(min(m,n)) time, O(1) space, 2 ms 100%, 46.2 Mb 92.36%.
 */
public class MedianTwoSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int N1 = nums1.length, N2 = nums2.length;
        if (N1 < N2) return findMedianSortedArrays(nums2, nums1);
        int lo = 0, hi = 2 * N2;
        while (lo <= hi) {
            int mid2 = lo + (hi - lo) / 2;
            int mid1 = N1 + N2 - mid2;  // Calculate Cut 1 accordingly

            //imagine that both arrays actually have two extra elements, INT_MIN at A[-1] and INT_MAX at A[N]
            double L1 = (mid1 == 0) ? Integer.MIN_VALUE : nums1[(mid1 - 1) / 2];
            double L2 = (mid2 == 0) ? Integer.MIN_VALUE : nums2[(mid2 - 1) / 2];
            double R1 = (mid1 == N1 * 2) ? Integer.MAX_VALUE : nums1[(mid1) / 2];
            double R2 = (mid2 == N2 * 2) ? Integer.MAX_VALUE : nums2[(mid2) / 2];

            // L1 <= R1 && L1 <= R2 && L2 <= R1 && L2 <= R2 -> L1 <= R2 and L2 <= R1
            if (L1 > R2) lo = mid2 + 1;        // A1's lower half is too big; need to move C1 left (C2 right)
            else if (L2 > R1) hi = mid2 - 1;    // A2's lower half too big; need to move C2 left.
            else return (Math.max(L1, L2) + Math.min(R1, R2)) / 2;    // Otherwise, that's the right cut.
        }
        return -1;
    }
}
