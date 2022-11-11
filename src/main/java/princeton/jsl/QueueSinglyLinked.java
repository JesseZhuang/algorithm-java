package princeton.jsl;

import java.util.NoSuchElementException;

/**
 * Queue with singly linked list. Static inner class.
 *
 * @param <E> element generic type
 */
public class QueueSinglyLinked<E> {
    /**
     * Node struct.
     *
     * @param <E> static inner class cannot infer the generic type for parent class.
     */
    private static class Node<E> {
        E element;
        Node<E> next;
    }

    private Node<E> first;
    private Node<E> last;
    private int count;

    public QueueSinglyLinked(){
        count = 0;
    }

    public void add(E element) {
        Node<E> oldLast = last; // don't forget E
        last = new Node<E>();
        last.element = element;
        if (isEmpty()) first = last;
        else oldLast.next = last;
        count++;
    }

    public E remove() {
        if (isEmpty()) throw new NoSuchElementException("queue is empty");
        E element = first.element;
        first = first.next;// no need oldFirst
        if (isEmpty()) last = null; // otherwise last still pointing to the dangling first node
        count--;
        return element;
    }

    public int size() {
        return count;
    }

    public boolean isEmpty() {
        return count == 0;
    }
}
