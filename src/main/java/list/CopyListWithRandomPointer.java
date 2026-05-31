package list;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 138. Medium. Tags: hash table, linked list.
 * <p>
 * A linked list of length n is given such that each node contains an additional random pointer, which could point
 * to any node in the list, or null. Construct a deep copy of the list.
 * <p>
 * Constraints:
 * <ul>
 * <li>0 <= n <= 1000
 * <li>-10^4 <= Node.val <= 10^4
 * <li>Node.random is null or is pointing to some node in the linked list.
 * </ul>
 */
public final class CopyListWithRandomPointer {

    private CopyListWithRandomPointer() {
    }

    /** Node definition for linked list with random pointer. */
    public static class Node {
        public int val;
        public Node next;
        public Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    /**
     * Hash Map approach. Uses a map from original nodes to their copies.
     *
     * @param head head of the original list
     * @return head of the deep-copied list
     */
    public static Node copyRandomListHashMap(Node head) {
        if (head == null) return null; // O(1)
        Map<Node, Node> map = new HashMap<>(); // O(n) space
        Node cur = head;
        while (cur != null) { // O(n) time — first pass: create all copy nodes
            map.put(cur, new Node(cur.val));
            cur = cur.next;
        }
        cur = head;
        while (cur != null) { // O(n) time — second pass: wire next and random pointers
            map.get(cur).next = map.get(cur.next);
            map.get(cur).random = map.get(cur.random);
            cur = cur.next;
        }
        return map.get(head);
    }

    /**
     * Interleaving approach. Weaves copy nodes between originals, then separates.
     * O(n) time, O(1) space (excluding the output list itself).
     *
     * @param head head of the original list
     * @return head of the deep-copied list
     */
    public static Node copyRandomListInterleave(Node head) {
        if (head == null) return null; // O(1)
        // Step 1: interleave copy nodes — O(n) time, O(1) space
        Node cur = head;
        while (cur != null) {
            Node copy = new Node(cur.val); // O(1) space per node (output)
            copy.next = cur.next;
            cur.next = copy;
            cur = copy.next;
        }
        // Step 2: assign random pointers for copy nodes — O(n) time
        cur = head;
        while (cur != null) {
            if (cur.random != null) {
                cur.next.random = cur.random.next; // copy's random = original's random's copy
            }
            cur = cur.next.next;
        }
        // Step 3: separate the two lists — O(n) time, O(1) space
        Node dummy = new Node(0);
        Node copyCur = dummy;
        cur = head;
        while (cur != null) {
            Node copy = cur.next;
            copyCur.next = copy;
            copyCur = copy;
            cur.next = copy.next; // restore original list
            cur = cur.next;
        }
        return dummy.next;
    }
}
