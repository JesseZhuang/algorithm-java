package dp;

import struct.ListNode;
import util.IntArrayUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * LeetCode 300, medium, tags: array, binary search, dynamic programmin.
 * Given an integer array nums, return the length of the longest strictly increasing subsequence.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [10,9,2,5,3,7,101,18]
 * Output: 4
 * Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
 * Example 2:
 * <p>
 * Input: nums = [0,1,0,3,2,3]
 * Output: 4
 * Example 3:
 * <p>
 * Input: nums = [7,7,7,7,7,7,7]
 * Output: 1
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 2500
 * -10^4 <= nums[i] <= 10^4
 * <p>
 * <p>
 * Follow up: Can you come up with an algorithm that runs in O(n log(n)) time complexity?
 * Reference: https://en.wikipedia.org/wiki/Patience_sorting
 */
public class LongestIncreasingSubSequence {

    /**
     * Solution 1, binary search.
     * O(NLgN) time, O(N) space. 5ms 41.8 Mb.
     * For uniformly random deck, the expected number of piles is
     * approximately 2 n 1/2 and the standard deviation is approximately n 1/6.
     */
    public int lengthOfLISBS(int[] nums) {
        List<Integer> piles = new ArrayList<>(nums.length);
        for (int num : nums) {
            int p = Collections.binarySearch(piles, num);
            if (p < 0) p = ~p; // not found will return -insertion point-1, negate to insertion point
            if (p == piles.size()) { // == not <= important
                piles.add(num); // add num not p
            } else {
                piles.set(p, num);
            }
        }
        return piles.size();
    }

    /**
     * return the longest increasing subarray.
     */
    public List<Integer> longestIncreasingSequence(int[] nums) {
        List<ListNode> piles = new ArrayList<>(nums.length);
        for (int num : nums) {
            ListNode listNode = new ListNode(num);
            int pile = Collections.binarySearch(piles, listNode, Comparator.comparingInt(node -> node.val));
            if (pile < 0) pile = ~pile;
            if (pile != 0) listNode.next = piles.get(pile - 1);
            if (pile == piles.size()) piles.add(listNode);
            else piles.set(pile, listNode);
        }
        return extractLIS(piles);
    }

    private List<Integer> extractLIS(List<ListNode> piles) {
        List<Integer> result = new ArrayList<>(piles.size());
        for (ListNode curr = piles.isEmpty() ? null : piles.get(piles.size() - 1); curr != null; curr = curr.next)
            result.add(curr.val);
        Collections.reverse(result);
        return result;
    }

    /**
     * Solution 2, binary indexed tree.
     * Let f[x] be the length of the longest increase subsequence, where all number in the subsequence <= x.
     * This is the max element in indices [1..x] if we build the Binary Indexed Tree (BIT)
     * <p>
     * 3ms 42.3 Mb. O(NLgN) time, O(max) space, can reduce to O(N) space by dedupe.
     */
    public int lengthOfLISBIT(int[] nums) {
        MaxBIT bit = new MaxBIT(20001); //-10^4 <= nums[i] <= 10^4 total 20001 numbers
        int offset = 10000; // shift to [0, 20000]
        for (int num : nums) { // dp
            int subLongest = bit.get(offset + num - 1);
            bit.update(offset + num, subLongest + 1);
        }
        return bit.get(20000);
    }

    /**
     * Combine dp with Fenwick (Binary Indexed) Tree.
     * Does not support range max query because max does not have an inverse operation like sum.
     */
    class MaxBIT {
        int tree[];

        MaxBIT(int size) {
            tree = new int[size + 1]; // leave tree[0] unused
        }

        int get(int index) {
            index += 1;
            int res = 0;
            while (index > 0) {
                res = Math.max(res, tree[index]);
                index -= index & (-index);
            }
            return res;
        }

        void update(int index, int val) {
            index += 1;
            while (index < tree.length) {
                tree[index] = Math.max(tree[index], val);
                index += index & (-index);
            }
        }
    }

    // O(N^2) time, O(N) space. 85ms, 42 Mb.
    int lengthOfLISDP(int[] nums) {
        int[] dp = new int[nums.length]; // LIS count with nums[i] as the end element
        Arrays.fill(dp, 1);
        for (int i = 0; i < nums.length; ++i)
            for (int j = 0; j < i; ++j)
                if (nums[i] > nums[j] && dp[i] < dp[j] + 1) dp[i] = dp[j] + 1;
        return IntArrayUtil.maxOfArrayWithStream(dp);
    }
}
