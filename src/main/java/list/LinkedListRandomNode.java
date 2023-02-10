package list;

import struct.ListNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * LeetCode 382, medium, tags: linked list, math, reservoir sampling, randomized.
 * <p>
 * Given a singly linked list, return a random node's value from the linked list. Each node must have the same probability of being chosen.
 * <p>
 * Implement the Solution class:
 * <p>
 * Solution(ListNode head) Initializes the object with the head of the singly-linked list head.
 * int getRandom() Chooses a node randomly from the list and returns its value. All the nodes of the list should be equally likely to be chosen.
 * <p>
 * Example 1: https://assets.leetcode.com/uploads/2021/03/16/getrand-linked-list.jpg
 * <p>
 * Input
 * ["Solution", "getRandom", "getRandom", "getRandom", "getRandom", "getRandom"]
 * [[[1, 2, 3]], [], [], [], [], []]
 * Output
 * [null, 1, 3, 2, 2, 3]
 * <p>
 * Explanation
 * Solution solution = new Solution([1, 2, 3]);
 * solution.getRandom(); // return 1
 * solution.getRandom(); // return 3
 * solution.getRandom(); // return 2
 * solution.getRandom(); // return 2
 * solution.getRandom(); // return 3
 * // getRandom() should return either 1, 2, or 3 randomly. Each element should have equal probability of returning.
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the linked list will be in the range [1, 104].
 * -104 <= Node.val <= 104
 * At most 104 calls will be made to getRandom.
 * <p>
 * Follow up:
 * <p>
 * What if the linked list is extremely large and its length is unknown to you?
 * Could you solve this efficiently without using extra space?
 */
public class LinkedListRandomNode {
}

// 12ms, 44.2 Mb, reservoir sampling.
class SolutionRS {

    ListNode head;
    Random r;

    public SolutionRS(ListNode head) { // O(1) time and space
        this.head = head;
        r = new Random();
    }

    public int getRandom() { // O(N) time O(1) space
        int scope = 1, res = 0;
        ListNode cur = head;
        while (cur != null) {
            if (Math.random() < 1.0 / scope) res = cur.val;
            scope++;
            cur = cur.next;
        }
        return res;
    }

    public int getRandom2() {
        ListNode cur = head;
        int res = cur.val;
        for (int scope = 1; cur.next != null; scope++) {
            cur = cur.next; // // replace with 1/(scope+1) probability
            if (r.nextInt(scope + 1) == scope) res = cur.val; // [0,scope]
        }
        return res;
    }
}

// 9ms, 44.6 MB. save in list.
class Solution {

    List<Integer> list;

    public Solution(ListNode head) { // O(N) time and space
        list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
    }

    public int getRandom() { // O(1) time and space
        return list.get((int) (Math.random() * list.size()));
    }

}
