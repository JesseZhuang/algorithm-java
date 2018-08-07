package list;

import struct.ListNode;

/**
 * LeetCode 237. Easy.
 * <p>
 * Write a function to delete a node (except the tail) in a singly linked list,
 * given only access to that node.
 * <p>
 * Supposed the linked list is 1 -> 2 -> 3 -> 4 and you are given the third node
 * with value 3, the linked list should become 1 -> 2 -> 4 after calling your
 * function.
 * <p>
 * Note:
 * <p>
 * <ul>
 * <li>The linked list will have at least two elements.
 * <li>All of the nodes' values will be unique.
 * <li>The given node will not be the tail and it will always be a valid node of the linked list.
 * <li>Do not return anything from your function.
 * </ul>
 * <p>
 * <b>Summary</b>:
 * <p>
 * <ul>
 * <li>O(1) time, zero space.
 * </ul>
 */
public class DeleteNodeLinkedList {
    public void deleteNode(ListNode node) {
        if (node == null)
            return;
        // this line replaces the 3rd node's val to 4
        node.val = node.next.val;
        /*
         * This line changes the 3rd node's next to refer to the 5th node instead of the 4th node.
         * So the actual node got deleted is the 4th node.
         */
        node.next = node.next.next;
    }

    /**
     * node = node.next does not work because the passed in object reference points
     * the node 3, you changed the node to points to 4 but java is pass by value, so
     * that passed in node will always point to the node 3.
     */
    public static void doesNotWork(ListNode node) {
        node = node.next;
    }

    public static void main(String[] args) {
        DeleteNodeLinkedList deleteNodeLinkedList = new DeleteNodeLinkedList();

        ListNode head = ListNode.createFromArray(new int[]{0, 1, 2, 3, 4, 5});
        System.out.println("Original list: " + head);
        doesNotWork(head);
        System.out.println(head);
        /*
         * you can no longer find the third node after this deletion since there
         * is no reference to it.
         */
        System.out.println("---Delete node with 3---");
        deleteNodeLinkedList.deleteNode(head.next.next.next);
        System.out.println(head);

        ListNode fourth = head.next.next.next;
        System.out.println("fourth node: " + fourth);
        deleteNodeLinkedList.deleteNode(fourth);
        System.out.println("---Delete fourth node---");
        System.out.println(head);
        System.out.println("fourth node " + fourth);
    }
}
