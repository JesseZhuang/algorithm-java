package struct;

import java.util.List;
import java.util.Objects;

/**
 * A singly linked list node containing an {@code int}.
 */
public class ListNode {

    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }

    /**
     * @return string representation of the linked list starting from this node.
     */
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        ListNode current = this;
        while (current != null) {
            res.append(current.val).append("->");
            current = current.next;
        }
        res.append("NULL");
        return res.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListNode listNode = (ListNode) o;
        return val == listNode.val &&
                Objects.equals(next, listNode.next);
    }

    @Override
    public int hashCode() {
        return Objects.hash(val, next);
    }

    /**
     * Constructing a linked list from a List.
     *
     * @param intList a list of Integers to construct from.
     * @return the head node of the constructed linked list.
     */
    public static ListNode createFromIntList(List<Integer> intList) {
        if (intList == null)
            return null;
        ListNode head = new ListNode(intList.get(0)), temp = head;
        for (int i = 1; i < intList.size(); i++) {
            temp.next = new ListNode(intList.get(i));
            temp = temp.next;
        }
        return head;
    }

    /**
     * Constructing a linked list from an array.
     *
     * @param ints int array to construct from.
     * @return the head node of the constructed linked list.
     */
    public static ListNode createFromArray(int[] ints) {
        if (ints == null || ints.length == 0)
            return null;
        ListNode head = new ListNode(ints[0]), temp = head;
        for (int i = 1; i < ints.length; i++) {
            temp.next = new ListNode(ints[i]);
            temp = temp.next;
        }
        return head;
    }

    /**
     * Reverse the linked list and return the new head node.
     *
     * @return the new head node of the reversed list.
     */
    public ListNode reverse() {
        ListNode newHead = null;
        ListNode head = this;
        while (head != null) {
            ListNode next = head.next;
            head.next = newHead;
            newHead = head;
            head = next;
        }
        return newHead;
    }

    /**
     * Counts how many nodes are in the linked list.
     *
     * @return the size of the linked list.
     */
    public int size() {
        int count = 0;
        ListNode temp = this;
        while (temp != null) {
            temp = temp.next;
            count++;
        }
        return count;
    }

}
