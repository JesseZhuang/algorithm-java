package array;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * LeetCode 215, medium, tags: array, divide and conquer, sorting, heap(priority queue), quick select.
 * <p>
 * Given an integer array nums and an integer k, return the kth largest element in the array.
 * <p>
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 * <p>
 * Can you solve it without sorting?
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [3,2,1,5,6,4], k = 2
 * Output: 5
 * Example 2:
 * <p>
 * Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
 * Output: 4
 * <p>
 * Constraints:
 * <p>
 * 1 <= k <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 */
public class KthLargest {
    // O(NlgK) time, O(K) space, 56ms, 56.97 Mb.
    public int findKthLargestPQ(int[] nums, int k) {
        PriorityQueue<Integer> q = new PriorityQueue();
        for (int n : nums) {
            q.add(n);
            if (q.size() > k) q.remove();
        }
        return q.peek();
    }

    // O(NLgN) time O(1) space (method dependent), 23ms, 57.55 MB.
    public static int findKthLargestS(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }

    public static int findKthLargestQS(int[] nums, int k) {
        shuffle(nums);
        return quickSelect(nums, k, 0, nums.length - 1);
    }

    static int quickSelect(int[] nums, int k, int lo, int hi) {
        int p = partitionNormal(nums, lo, hi);
        if (p == nums.length - k) return nums[p];
        else if (p > nums.length - k) return quickSelect(nums, k, lo, p - 1);
        else return quickSelect(nums, k, p + 1, hi);
    }

    // time limit exceeded for a large array with duplicates
    static int partition3Way(int[] nums, int lo, int hi) {
        int pivot = nums[lo], lt = lo, gt = hi, i = lo + 1;
        while (i <= gt) {
            if (nums[i] < pivot) swap(nums, i++, lt++);
            else if (nums[i] > pivot) swap(nums, i, gt--);
            else i++;
        }
        return lt;
    }

    // 11ms, 56.59Mb. O(n) time, O(1) space. Quick select. With shuffle: 11ms, 56.42Mb.
    static int partitionNormal(int[] nums, int lo, int hi) {
        // a lot of caveats, gt why hi+1, why ++ -- in the while loop (no infinite loop for duplicates)
        int pivot = nums[lo], lt = lo, gt = hi + 1;
        while (true) {
            while (++lt < hi && nums[lt] < pivot) ;
            while (--gt > lo && nums[gt] > pivot) ;
            if (lt >= gt) break;
            swap(nums, lt, gt);
        }
        swap(nums, lo, gt);
        return gt;
    }

    static void shuffle(int[] nums) {
        Random r = new Random();
        for (int i = 1; i < nums.length; i++) {
            int j = r.nextInt(i + 1);
            swap(nums, i, j);
        }
    }

    static void swap(int[] nums, int i, int j) {
        if (i == j) return;
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[][] data = {
                {3, 2, 1, 5, 6, 4},
                {3, 2, 3, 1, 2, 4, 5, 5, 6},
                {3, 3, 3, 3, 3, 3, 3, 3, 3},
        };
        int[] k = {2, 4, 3};
        for (int i = 0; i < k.length; i++) {
            for (int j = 0; j < 1000; j++) {
                if (j == 999) System.out.println("last iteration");
//                System.out.println(Arrays.toString(data[i]));
//                System.out.println(findKthLargestQS(data[i], k[i]));
//                System.out.println(QuickSelect.kthLargest(data[i], 0, data[i].length - 1, k[i]));
//                System.out.println(Arrays.toString(data[i]));
                int r1 = findKthLargestQS(data[i], k[i]), r2 = findKthLargestS(data[i], k[i]);
                if (r1 != r2) System.out.println(r1 + " != " + r2);
            }
        }
    }
}
