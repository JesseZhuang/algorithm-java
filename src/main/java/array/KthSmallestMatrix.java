package array;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * LeetCode 378, medium, tags: array, binary search, sorting, heap, matrix.
 * <p>
 * Given an n x n matrix where each of the rows and columns is sorted in ascending order, return the kth smallest
 * element in the matrix.
 * <p>
 * Note that it is the kth smallest element in the sorted order, not the kth distinct element.
 * <p>
 * You must find a solution with a memory complexity better than O(n2).
 * <p>
 * Example 1:
 * <p>
 * Input: matrix = [[1,5,9],[10,11,13],[12,13,15]], k = 8
 * Output: 13
 * Explanation: The elements in the matrix are [1,5,9,10,11,12,13,13,15], and the 8th smallest number is 13
 * Example 2:
 * <p>
 * Input: matrix = [[-5]], k = 1
 * Output: -5
 * <p>
 * <p>
 * Constraints:
 * <p>
 * n == matrix.length == matrix[i].length
 * 1 <= n <= 300
 * -10^9 <= matrix[i][j] <= 10^9
 * All the rows and columns of matrix are guaranteed to be sorted in non-decreasing order.
 * 1 <= k <= n^2
 * <p>
 * <p>
 * Follow up:
 * <p>
 * Could you solve the problem with a constant memory (i.e., O(1) memory complexity)?
 * <p>
 * binary search.
 * <p>
 * Could you solve the problem in O(n) time complexity? The solution may be too advanced for an interview but
 * you may find reading this paper fun.
 * <p>
 * See StefanPochmann's implementation in LeetCode: saddle back search, median of medians.
 */
public class KthSmallestMatrix {
    int m, n;

    // solution 1, binary search, nlgr time, 1 space. 0ms, 47.56Mb.
    public int kthSmallestBS(int[][] matrix, int k) {
        m = matrix.length;
        n = matrix[0].length; // For general, the mleeatrix need not be a square
        int left = matrix[0][0], right = matrix[m - 1][n - 1], ans = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (countLessOrEqual(matrix, mid) >= k) {
                ans = mid; // mid may have duplicate, [[1,3,7],[5,10,12],[6,10,15]] countLessOrEqual(10)==7 > 6
                right = mid - 1;
            } else left = mid + 1;
        }
        return ans;
    }

    int countLessOrEqual(int[][] matrix, int x) {
        int cnt = 0, c = n - 1; // start with the rightmost column
        for (int r = 0; r < m; ++r) {
            while (c >= 0 && matrix[r][c] > x) --c;  // decrease column until matrix[r][c] <= x
            cnt += (c + 1);
        }
        return cnt;
    }

    // solution 2, min heap, 20ms, 45.92Mb. klgk time, k space.
    public int kthSmallest2(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length, ans = -1;
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));
        for (int r = 0; r < Math.min(m, k); ++r)
            minHeap.offer(new int[]{matrix[r][0], r, 0});

        for (int i = 1; i <= k; ++i) {
            int[] top = minHeap.poll();
            int r = top[1], c = top[2];
            ans = top[0];
            if (c + 1 < n) minHeap.offer(new int[]{matrix[r][c + 1], r, c + 1});
        }
        return ans;
    }

    // solution 3, max heap, mnlgk time, k space. 16ms, 45.95Mb.
    public int kthSmallest3(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> Integer.compare(o2, o1));
        for (int r = 0; r < m; ++r) {
            for (int c = 0; c < n; ++c) {
                maxHeap.offer(matrix[r][c]);
                if (maxHeap.size() > k) maxHeap.poll();
            }
        }
        return maxHeap.poll();
    }
}
