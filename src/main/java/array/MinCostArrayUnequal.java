package array;

/**
 * LeetCode 2499, Hard. Tags: array, hash table.
 * You are given two 0-indexed integer arrays nums1 and nums2, of equal length n.
 * <p>
 * In one operation, you can swap the values of any two indices of nums1. The cost of this operation is
 * the sum of the indices.
 * <p>
 * Find the minimum total cost of performing the given operation any number of times such that nums1[i] != nums2[i]
 * for all 0 <= i <= n - 1 after performing all the operations.
 * <p>
 * Return the minimum total cost such that nums1 and nums2 satisfy the above condition.
 * In case it is not possible, return -1.
 * <p>
 * Example 1:
 * <p>
 * Input: nums1 = [1,2,3,4,5], nums2 = [1,2,3,4,5]
 * Output: 10
 * Explanation:
 * One of the ways we can perform the operations is:
 * - Swap values at indices 0 and 3, incurring cost = 0 + 3 = 3. Now, nums1 = [4,2,3,1,5]
 * - Swap values at indices 1 and 2, incurring cost = 1 + 2 = 3. Now, nums1 = [4,3,2,1,5].
 * - Swap values at indices 0 and 4, incurring cost = 0 + 4 = 4. Now, nums1 =[5,3,2,1,4].
 * We can see that for each index i, nums1[i] != nums2[i]. The cost required here is 10.
 * Note that there are other ways to swap values, but it can be proven that it is not possible to
 * obtain a cost less than 10.
 * Example 2:
 * <p>
 * Input: nums1 = [2,2,2,1,3], nums2 = [1,2,2,3,3]
 * Output: 10
 * Explanation:
 * One of the ways we can perform the operations is:
 * - Swap values at indices 2 and 3, incurring cost = 2 + 3 = 5. Now, nums1 = [2,2,1,2,3].
 * - Swap values at indices 1 and 4, incurring cost = 1 + 4 = 5. Now, nums1 = [2,3,1,2,2].
 * The total cost needed here is 10, which is the minimum possible.
 * Example 3:
 * <p>
 * Input: nums1 = [1,2,2], nums2 = [1,2,2]
 * Output: -1
 * Explanation:
 * It can be shown that it is not possible to satisfy the given conditions irrespective of
 * the number of operations we perform.
 * Hence, we return -1.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * n == nums1.length == nums2.length
 * 1 <= n <= 105
 * 1 <= nums1[i], nums2[i] <= n
 */
public class MinCostArrayUnequal {

    // 4ms 59.8Mb. O(N) time, O(1) space.
    public static long minimumTotalCost1(int[] nums1, int[] nums2) {
        long res = 0;
        int[] map = new int[nums1.length + 1]; // map to keep what values need swap
        int swaps = 0, mostFreqVal = 0;
        for (int i = 0; i < nums1.length; i++) {
            if (nums1[i] == nums2[i]) {
                swaps++; // equal count, swap needed
                if (++map[nums1[i]] > map[mostFreqVal]) mostFreqVal = nums1[i];
                res += i; // i must be swapped
            }
        }
        if (swaps >= 2 * map[mostFreqVal]) return res; // swaps must >= 2 * map[mostFreqVal]
        for (int i = 0; i < nums1.length; i++) {
            // can swap mostFreqVal with one that satisfy all conditions below
            if (nums1[i] != nums2[i] && nums1[i] != mostFreqVal && nums2[i] != mostFreqVal) {
                res += i;
                swaps++;
                if (swaps >= 2 * map[mostFreqVal]) return res;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(minimumTotalCost1(new int[]{2, 2, 2, 1, 3}, new int[]{1, 2, 2, 3, 3}));// 10
        System.out.println(minimumTotalCost1(new int[]{1, 2, 2}, new int[]{1, 2, 2})); // -1
        System.out.println(minimumTotalCost1(new int[]{1}, new int[]{1})); // -1
        System.out.println(minimumTotalCost1(new int[]{1, 2, 3}, new int[]{1, 2, 3})); // 3
        System.out.println(minimumTotalCost1(new int[]{1, 2, 2, 2, 2, 4, 5}, new int[]{1, 2, 2, 2, 2, 5, 4})); // -1
        System.out.println(minimumTotalCost1(new int[]{1, 2, 2, 2, 2, 4, 5, 6},
                new int[]{1, 2, 2, 2, 2, 6, 4, 5})); // 28, mostFreq:2 count:4, sum 0..7 == 28
    }

    // 4ms 59.7 Mb.
    public long minimumTotalCost2(int[] nums1, int[] nums2) {
        int[] freq = new int[nums1.length + 1];
        long ans = 0;
        int mostFrequent = 0, cnt = 0;
        for (int i = 0; i < nums1.length; ++i) {
            ++freq[nums1[i]];
            ++freq[nums2[i]];
            if (nums1[i] == nums2[i]) {
                ans += i;
                if (nums1[i] == mostFrequent || cnt == 0) {
                    mostFrequent = nums1[i];
                    ++cnt;
                } else --cnt;
            }
        }
        for (int i = 0; i < nums1.length && cnt > 0; ++i) {
            if (nums1[i] != nums2[i] && nums1[i] != mostFrequent && nums2[i] != mostFrequent) {
                ans += i;
                --cnt;
            }
        }
        return freq[mostFrequent] > nums1.length ? -1 : ans;
    }
}
