package queue;

/**
 * LeetCode 641, medium, tags: design, array, linked list, queue.
 * <p>
 * Design your implementation of the circular double-ended queue (deque).
 * <p>
 * Implement the MyCircularDeque class:
 * <p>
 * MyCircularDeque(int k) Initializes the deque with a maximum size of k.
 * boolean insertFront() Adds an item at the front of Deque. Returns true if the operation is successful,
 * or false otherwise.
 * boolean insertLast() Adds an item at the rear of Deque. Returns true if the operation is successful,
 * or false otherwise.
 * boolean deleteFront() Deletes an item from the front of Deque. Returns true if the operation is successful,
 * or false otherwise.
 * boolean deleteLast() Deletes an item from the rear of Deque. Returns true if the operation is successful,
 * or false otherwise.
 * int getFront() Returns the front item from the Deque. Returns -1 if the deque is empty.
 * int getRear() Returns the last item from Deque. Returns -1 if the deque is empty.
 * boolean isEmpty() Returns true if the deque is empty, or false otherwise.
 * boolean isFull() Returns true if the deque is full, or false otherwise.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["MyCircularDeque", "insertLast", "insertLast", "insertFront", "insertFront", "getRear", "isFull",
 * "deleteLast", "insertFront", "getFront"]
 * [[3], [1], [2], [3], [4], [], [], [], [4], []]
 * Output
 * [null, true, true, true, false, 2, true, true, true, 4]
 * <p>
 * Explanation
 * MyCircularDeque myCircularDeque = new MyCircularDeque(3);
 * myCircularDeque.insertLast(1);  // return True
 * myCircularDeque.insertLast(2);  // return True
 * myCircularDeque.insertFront(3); // return True
 * myCircularDeque.insertFront(4); // return False, the queue is full.
 * myCircularDeque.getRear();      // return 2
 * myCircularDeque.isFull();       // return True
 * myCircularDeque.deleteLast();   // return True
 * myCircularDeque.insertFront(4); // return True
 * myCircularDeque.getFront();     // return 4
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= k <= 1000
 * 0 <= value <= 1000
 * At most 2000 calls will be made to insertFront, insertLast, deleteFront, deleteLast, getFront, getRear,
 * isEmpty, isFull.
 */
@SuppressWarnings("unused")
public class CircularDeque {
    // linked list, 5ms, 44.38mb. 1, k.
    static class Solution1 {
        class Node {
            public int val;
            public Node next;
            public Node prev;

            public Node(int val, Node next, Node prev) {
                this.val = val;
                this.next = next;
                this.prev = prev;
            }
        }

        class MyCircularDeque {

            Node head;
            Node rear;
            int size;
            int capacity;

            public MyCircularDeque(int k) {
                size = 0;
                capacity = k;
            }

            public boolean insertFront(int value) {
                if (isFull()) return false;
                if (head == null) {
                    // first element in list
                    head = new Node(value, null, null);
                    rear = head;
                } else {
                    // add new head
                    Node newHead = new Node(value, head, null);
                    head.prev = newHead;
                    head = newHead;
                }
                size++;
                return true;
            }

            public boolean insertLast(int value) {
                if (isFull()) return false;
                if (head == null) {
                    // first element in list
                    head = new Node(value, null, null);
                    rear = head;
                } else {
                    // add new element to end
                    rear.next = new Node(value, null, rear);
                    rear = rear.next;
                }
                size++;
                return true;
            }

            public boolean deleteFront() {
                if (isEmpty()) return false;
                if (size == 1) {
                    head = null;
                    rear = null;
                } else head = head.next;
                size--;
                return true;
            }

            public boolean deleteLast() {
                if (isEmpty()) return false;
                if (size == 1) {
                    head = null;
                    rear = null;
                } else rear = rear.prev;
                size--;
                return true;
            }

            public int getFront() {
                if (isEmpty()) return -1;
                return head.val;
            }

            public int getRear() {
                if (isEmpty()) return -1;
                return rear.val;
            }

            public boolean isEmpty() {
                return size == 0;
            }

            public boolean isFull() {
                return size == capacity;
            }
        }
    }

    // array, 4ms, 44.8mb. 1, k.
    static class Solution2 {
        class MyCircularDeque {
            int[] array;
            int front;
            int rear;
            int size;
            int capacity;

            public MyCircularDeque(int k) {
                array = new int[k];
                size = 0;
                capacity = k;
                front = 0;
                rear = k - 1;
            }

            public boolean insertFront(int value) {
                if (isFull()) return false;
                front = (front - 1 + capacity) % capacity;
                array[front] = value;
                size++;
                return true;
            }

            public boolean insertLast(int value) {
                if (isFull()) return false;
                rear = (rear + 1) % capacity;
                array[rear] = value;
                size++;
                return true;
            }

            public boolean deleteFront() {
                if (isEmpty()) return false;
                front = (front + 1) % capacity;
                size--;
                return true;
            }

            public boolean deleteLast() {
                if (isEmpty()) return false;
                rear = (rear - 1 + capacity) % capacity;
                size--;
                return true;
            }

            public int getFront() {
                if (isEmpty()) return -1;
                return array[front];
            }

            public int getRear() {
                if (isEmpty()) return -1;
                return array[rear];
            }

            public boolean isEmpty() {
                return size == 0;
            }

            public boolean isFull() {
                return size == capacity;
            }
        }
    }
}
