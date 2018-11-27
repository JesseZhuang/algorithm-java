package list;

import struct.ListNode;

import java.util.HashSet;

/**
 * LeetCode 160. Easy.
 * <p>
 * Write a program to find the node at which the intersection of two singly linked lists begins.
 * <pre>
 * For example, the following two linked lists:
 *
 * A:          a1 → a2
 * ↘
 * c1 → c2 → c3
 * ↗
 * B:     b1 → b2 → b3
 * </pre>
 * <p>
 * begin to intersect at node c1.
 * <p>
 * Notes:
 * <p><ul>
 * <li>If the two linked lists have no intersection at all, return {@code null} .
 * <li>The linked lists must retain their original structure after the function returns.
 * <li>You may assume there are no cycles anywhere in the entire linked structure.
 * <li>Your code should preferably run in O(n) time and use only O(1) memory.
 * </ul>
 * <b>Summary</b>:
 * <ul>
 * <li>Wrap around, O(n) time, O(1) space.
 * <li>Start from same length, O(n) time, O(1) space. Need to get size first, extra iteration.
 * </ul>
 */
public class IntersectionTwoLinkedLists {

    /**
     * wrong, return the tail of the overlapping region, 1 time n+m probably not possible
     */
    @Deprecated
    public ListNode getIntersectionNodeWrong2(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode a = headA;
        ListNode b = headB;
        while (a != b && !(a.next == null && b.next == null)) {
            a = a.next == null ? a : a.next;
            b = b.next == null ? b : b.next;
        }
        return a == b ? a : null;
    }

    /**
     * iterative O(n) time, O(1) space, this algorithm will wrap around and meet if two lists are of different lengths,
     * traversing 2(n+m). If same length, works better than algorithm 2.
     */
    public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode a = headA, b = headB;
        // if a & b have different len, then we will stop the loop after second iteration
        while (a != b) {
            // for the end of first iteration, we just reset the pointer to the head of the other LinkedList
            a = a == null ? headB : a.next;
            b = b == null ? headA : b.next;
        }
        return a;
    }

    /**
     * This algorithm is worse when lengths are equal. For length A: a+n, Length B: b+n (n is overlapping part, a!=b).
     * Time 2(a+b+n), same as algorithm 1.
     */
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        int lenA = headA.size(), lenB = headB.size();
        // move headA and headB to the same start point
        while (lenA > lenB) {
            headA = headA.next;
            lenA--;
        }
        while (lenA < lenB) {
            headB = headB.next;
            lenB--;
        }
        // find the intersection until end
        while (headA != headB) {
            headA = headA.next;
            headB = headB.next;
        }
        return headA;
    }

    /**
     * Default implementation of equals check ==, default hash code return the address of the object. Iterative version
     * with a HashSet. Recursive helper version, need to pass the HashSet along. O(n) space in worst case.
     * <p>
     * This algorithm is incorrect if ListNode's equals method considers two separate instances 1->2->NULL and
     * 1->2->NULL to be equal.
     */
    public ListNode getIntersectionNodeSpace(ListNode headA, ListNode headB) {

        HashSet<ListNode> seen = new HashSet<>();
        while (headA != null || headB != null) {
            if (headA != null) {
                if (!seen.add(headA)) return headA;
                else headA = headA.next;
            }
            if (headB != null) {
                if (!seen.add(headB)) return headB;
                else headB = headB.next;
            }
        }
        return null;
    }

    /**
     * Assuming the two lists have lengths lA, lB, a brute force search would take lA * lB time. And this is also the
     * minimum number of compares needed (considering no intersection case). Below iterative version exceeded time
     * limit.
     */
    public ListNode getIntersectionNodeBF(ListNode headA, ListNode headB) {
        while (headA != null) {
            ListNode cB = headB; // needs to start at headB in every outer loop
            while (cB != null) {
                if (headA == cB) return cB;
                cB = cB.next;
            }
            headA = headA.next;
        }
        return null;
    }

}

