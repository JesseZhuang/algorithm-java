package stack;

import java.util.ArrayDeque;

/**
 * <p>LeetCode 232. Easy.
 * <p>Implement the following operations of a queue using stacks.
 * <ul>
 * <li>enqueue(x) -- Push element x to the back of queue.
 * <li>dequeue() -- Removes the element from in front of queue.
 * <li>peek() -- Get the front element.
 * <li>empty() -- Return whether the queue is empty.
 * </ul>
 * <p>
 * Notes:
 * <ul>
 * <li>You must use only standard operations of a stack -- which means only
 * {@code enqueue to top}, {@code peek/dequeue from top}, {@code size}, and
 * {@code is empty} operations are valid.
 * <li>Depending on your language, stack may not be supported natively. You may
 * simulate a stack by using a list or deque (double-ended queue), as long as
 * you use only standard operations of a stack.
 * <li>You may assume that all operations are valid (for example, no dequeue or peek
 * operations will be called on an empty queue).
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["MyQueue", "push", "push", "peek", "pop", "empty"]
 * [[], [1], [2], [], [], []]
 * Output
 * [null, null, null, 1, 1, false]
 * <p>
 * Explanation
 * MyQueue myQueue = new MyQueue();
 * myQueue.push(1); // queue is: [1]
 * myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
 * myQueue.peek(); // return 1
 * myQueue.pop(); // return 1, queue is [2]
 * myQueue.empty(); // return false
 * <p>
 * Constraints:
 * <p>
 * 1 <= x <= 9
 * At most 100 calls will be made to push, pop, peek, and empty.
 * All the calls to pop and peek are valid.
 * <p>
 * Follow-up: Can you implement the queue such that each operation is amortized O(1) time complexity? In other words,
 * performing n operations will take overall O(n) time even if one of those operations may take longer.
 * </ul>
 * <b>Summary:</b>
 * <ul>
 * <li>two stacks, transfer when peek() or dequeue() is called. Amortized transfer time.
 * <li>one stack storage, one temp transfer. Each transfer is O(n).
 * </ul>
 */
public class ImplementQueueUsingStacks {


    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue();
        myQueue.enqueue(1);
        myQueue.enqueue(2);
        System.out.println("dequeue-ing ... " + myQueue.dequeue() + " removed.");
        System.out.println("peeking ... " + myQueue.peek() + " peeked.");
        System.out.println("myQueue is empty? : " + myQueue.empty());
    }

    @SuppressWarnings({"ConstantConditions", "unused"})
    private static class MyQueue {

        /**
         * use ArrayDeque to simulate the stack
         */
        private ArrayDeque<Integer> input;
        /**
         * using two stacks is better, amortized time
         */
        private ArrayDeque<Integer> output;

        /**
         * Initialize your data structure here.
         */
        public MyQueue() {
            this.input = new ArrayDeque<>();
            this.output = new ArrayDeque<>();
        }

        /**
         * Add element x to the back of queue.
         */
        public void enqueue(int x) {
            input.push(x);
        }

        /**
         * Removes the element from in front of queue and returns that element.
         */
        public int dequeue() {
            peek();
            return output.pop();
        }

        /**
         * Get the front element. Amortized time.
         */
        public int peek() {
            if (output.isEmpty()) while (!input.isEmpty()) output.push(input.pop());
            return output.peek();
        }

        /**
         * Returns whether the queue is empty.
         */
        public boolean empty() {
            return input.isEmpty() && output.isEmpty();
        }

        public void enqueue2(int x) {
            ArrayDeque<Integer> transfer = new ArrayDeque<>();
            while (!this.empty()) transfer.push(this.input.pop());
            this.input.push(x);
            while (!transfer.isEmpty()) this.input.push(transfer.pop());
        }

        public int dequeue2() {
            return input.pop();
        }

        public int peek2() {
            return input.peek();
        }

        public boolean empty2() {
            return input.isEmpty();
        }
    }
}
