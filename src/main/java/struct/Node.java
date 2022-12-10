package struct;

import java.util.List;
import java.util.Objects;

/**
 * Generic version of singly linked list node.
 *
 * @param <T> generic type for the value.
 */
public class Node<T> {
    public T val;
    public Node<T> next;

    public Node(T x) {
        val = x;
    }

    /**
     * @return string representation of the linked list starting from this node.
     */
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        Node<T> current = this;
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
        Node<T> listNode = (Node<T>) o;
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
    public static Node<Integer> createFromIntList(List<Integer> intList) {
        if (intList == null)
            return null;
        Node<Integer> head = new Node<>(intList.get(0)), temp = head;
        for (int i = 1; i < intList.size(); i++) {
            temp.next = new Node<>(intList.get(i));
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
    public static Node<Integer> createFromArray(int[] ints) {
        if (ints == null || ints.length == 0)
            return null;
        Node<Integer> head = new Node<>(ints[0]), temp = head;
        for (int i = 1; i < ints.length; i++) {
            temp.next = new Node<>(ints[i]);
            temp = temp.next;
        }
        return head;
    }

    /**
     * Reverse the linked list and return the new head node.
     *
     * @return the new head node of the reversed list.
     */
    public Node<T> reverse() {
        Node<T> newHead = null;
        Node<T> head = this;
        while (head != null) {
            Node<T> next = head.next;
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
        Node<T> temp = this;
        while (temp != null) {
            temp = temp.next;
            count++;
        }
        return count;
    }
}
