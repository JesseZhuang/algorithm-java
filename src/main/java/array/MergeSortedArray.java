package array;

import java.util.Arrays;

/**
 * LeetCode 88. Easy.
 * <p>
 * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
 * <p>
 * Note:
 * <p>
 * The number of elements initialized in nums1 and nums2 are m and n respectively.
 * You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements
 * from nums2.
 * Example:
 * <p>
 * Input:
 * <p>
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 * <p>
 * Output: [1,2,2,3,5,6]
 * <p>
 * <b>Summary:</b>
 * <p>
 * <ul>
 * <li><b>fill backwards, O(n) time, O(1) space.</b>
 * <li>copy, O(n) time, O(n) space.
 * </ul>
 */
public class MergeSortedArray {

    /** good idea to start backwards O(m+n) time, in place*/
    public void merge1(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1, j = n - 1, k = m + n - 1;
        while (j >= 0) {
            if (i < 0 || nums2[j] > nums1[i]) nums1[k--] = nums2[j--];
            else nums1[k--] = nums1[i--];
        }

    }

    /** O(m+n) extra space, distribute and copy back, time 2*(m+n) */
    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        int[] temp = new int[m + n];
        for (int i = 0, j = 0, k = 0; k < m + n; k++) {
            // if (i == m) temp[k] = nums1[i++]; simple (typo) bug
            // if (j == n) temp[k] = nums2[j++];
            if (i == m) temp[k] = nums2[j++];
            else if (j == n) temp[k] = nums1[i++]; // failed [0] 0 [1] 1
            else if (nums1[i] < nums2[j]) temp[k] = nums1[i++];
                // if (nums1[i] < nums2[j]) temp[k] = nums1[i++]; // the else is necessary
            else temp[k] = nums2[j++];
        }
        for (int k = 0; k < m + n; k++) {
            nums1[k] = temp[k];
        }
    }

    /** just for fun */
    public void mergeFun(int[] nums1, int m, int[] nums2, int n) {
        System.arraycopy(nums2, 0, nums1, m, n);
        Arrays.sort(nums1);
    }
}
