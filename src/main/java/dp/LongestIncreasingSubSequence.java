package dp;

import struct.ListNode;

import java.util.ArrayList;
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
}
