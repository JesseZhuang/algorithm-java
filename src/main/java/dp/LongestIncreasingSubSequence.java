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
 * -104 <= nums[i] <= 104
 * <p>
 * <p>
 * Follow up: Can you come up with an algorithm that runs in O(n log(n)) time complexity?
 * Reference: https://en.wikipedia.org/wiki/Patience_sorting
 */
public class LongestIncreasingSubSequence {

    // O(N^2) time, O(N) space. 85ms, 42 Mb.
    int lengthOfLISDP(int[] nums) {
        int[] dp = new int[nums.length]; // LIS count with nums[i] as the end element
        Arrays.fill(dp, 1);
        for (int i = 0; i < nums.length; ++i)
            for (int j = 0; j < i; ++j)
                if (nums[i] > nums[j] && dp[i] < dp[j] + 1) dp[i] = dp[j] + 1;
        return IntArrayUtil.maxOfArrayWithStream(dp);
    }

    /**
     * O(NLgN) time, O(N) space. 5ms 41.8 Mb.
     * For uniformly random deck, the expected number of piles is
     * approximately 2 n 1/2 and the standard deviation is approximately n 1/6.
     */
    public int lengthOfLISBS(int[] nums) {
        List<Integer> piles = new ArrayList<>(nums.length);
        for (int num : nums) {
            int pile = Collections.binarySearch(piles, num);
            if (pile < 0) pile = ~pile; // not found will return -insertion point-1, negate to insertion point
            if (pile == piles.size()) {
                piles.add(num);
            } else {
                piles.set(pile, num);
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
     * Let f[x] be the length of longest increase subsequence, where all number in the subsequence <= x.
     * This is the max element in indices [1..x] if we build the Binary Indexed Tree (BIT)
     * <p>
     * 3ms 42.3 Mb. O(NLgN) time, O(max) space, can reduce to O(N) space by dedupe.
     */
    public int lengthOfLISBIT(int[] nums) {
        MaxBIT bit = new MaxBIT(20001);
        int offset = 10001; // -10^4 <= nums[i] <= 10^4
        for (int num : nums) {
            int subLongest = bit.getSum(offset + num - 1);
            bit.update(offset + num, subLongest + 1);
        }
        return bit.getSum(20001);
    }

    /**
     * Combine dp with Fenwick Tree.
     */
    class MaxBIT {
        int BITree[];

        MaxBIT(int size) {
            BITree = new int[size + 1];
        }

        int getSum(int index) {
            int sum = 0;
            while (index > 0) {
                sum = Math.max(sum, BITree[index]);
                index -= index & (-index);
            }
            return sum;
        }

        void update(int index, int val) {
            while (index < BITree.length) {
                BITree[index] = Math.max(BITree[index], val);
                index += index & (-index);
            }
        }
    }
}
