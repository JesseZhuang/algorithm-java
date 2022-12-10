package list;

import struct.Node;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.function.Consumer;

/**
 * Reversely apply a function to Singly linked list node from last to first without modifying the original list.
 */
public class ReverselyApplyFunction {

    static Consumer<Node> printNode = node -> System.out.println(node);
    static Consumer<Node> printValue = node -> System.out.println(node.val);

    // iterative, space usage in heap not stack
    static void reverselyApplyWithStack(Node head, Consumer consumer) {
        Deque<Node> stack = new LinkedList<>();
        while (head != null) {
            stack.push(head);
            head = head.next;
        }
        while (!stack.isEmpty()) consumer.andThen(printValue).accept(stack.pop());
    }

    // recursive, may stack overflow
    static void reverselyApply(Node head, Consumer consumer) {
        if (head == null) return;
        if (head.next != null) reverselyApply(head.next, consumer);
        consumer.accept(head);
    }

    public static void main(String[] args) {
        Node<Integer> head = Node.createFromIntList(Arrays.asList(1, 2, 3, 4, 5));
        reverselyApply(head, printNode);
        System.out.println("---");
        reverselyApplyWithStack(head, printNode);
    }
}




