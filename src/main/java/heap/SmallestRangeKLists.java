package heap;

import java.util.*;

/**
 * LeetCode 632, hard, tags: array, hash table, greedy, sliding window, sorting, heap.
 * <p>
 * You have k lists of sorted integers in non-decreasing order. Find the smallest range that includes at least
 * one number from each of the k lists.
 * <p>
 * We define the range [a, b] is smaller than range [c, d] if b - a < d - c or a < c if b - a == d - c.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [[4,10,15,24,26],[0,9,12,20],[5,18,22,30]]
 * Output: [20,24]
 * Explanation:
 * List 1: [4, 10, 15, 24,26], 24 is in range [20,24].
 * List 2: [0, 9, 12, 20], 20 is in range [20,24].
 * List 3: [5, 18, 22, 30], 22 is in range [20,24].
 * Example 2:
 * <p>
 * Input: nums = [[1,2,3],[1,2,3],[1,2,3]]
 * Output: [1,1]
 * <p>
 * Constraints:
 * <p>
 * nums.length == k
 * 1 <= k <= 3500
 * 1 <= nums[i].length <= 50, consider this O(1)
 * -10^5 <= nums[i][j] <= 10^5
 * nums[i] is sorted in non-decreasing order.
 */
public class SmallestRangeKLists {

    public static void main(String[] args) {
        List<List<Integer>> nums = new ArrayList<>();
        nums.add(Arrays.asList(4, 10, 15, 24, 26));
        nums.add(Arrays.asList(0, 9, 12, 20));
        nums.add(Arrays.asList(5, 18, 22, 30));
        System.out.println(Arrays.toString(new SmallestRangeKLists().smallestRange(nums)));
    }

    // heap, O(klgk) time, O(k) space. 35ms, 48.95Mb.
    public int[] smallestRange(List<List<Integer>> nums) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
        int max = Integer.MIN_VALUE, left = (int) -1e5, right = (int) 1e5;
        for (int r = 0; r < nums.size(); r++) {
            int v = nums.get(r).get(0);
            pq.add(new int[]{r, 0, v});
            if (v > max) max = v;
        }
        // [[4,10,15,24,26],[0,9,12,20],[5,18,22,30]] [0,5]->[20,24]
        while (pq.size() == nums.size()) { // not !pq.isEmpty()
            int[] cur = pq.remove();
            int r = cur[0], c = cur[1], v = cur[2];
            if (max - v < right - left) {
                left = v;
                right = max;
            }
            if (c + 1 == nums.get(r).size()) break; // completed one row, done
            v = nums.get(r).get(c + 1);
            if (v > max) max = v; // so that [next v,max] can cover 1 element from each
            // left,right,max,v [pq]; -1e5,1e5,5->9,0 [4,5,9];  0,5,9->10,4, [5,9,10]; 0,5,10->18,5, [9,10,18];
            // 0,5,18->18,9, [10,12,18]; 0,5,18->18,10, [12,15,18]; 0,5,18->20,12, [15,18,20]
            // 0,5,20->24,15, [18,20,24]; 0,5,24->24,18, [20,22,24]; 20,24,24->, 20, [22,24] break
            pq.add(new int[]{r, c + 1, v});
        }
        return new int[]{left, right};
    }
}
