package queue;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * <p>LeetCode 225. Easy.
 * <p>Implement the following operations of a stack using queues.
 * <ul>
 * <li>push(x) -- Push element x onto stack.
 * <li>pop() -- Removes the element on top of the stack.
 * <li>top() -- Get the top element.
 * <li>empty() -- Return whether the stack is empty.
 * </ul>
 * <p>Notes: You must use only standard operations of a queue -- which means only
 * {@code enqueue to back}, {@code peek/dequeue from front}, {@code size}, and
 * {@code is empty} operations are valid. Depending on your language, queue may
 * not be supported natively. You may simulate a queue by using a list or deque
 * (double-ended queue), as long as you use only standard operations of a queue.
 * You may assume that all operations are valid (for example, no pop or top
 * operations will be called on an empty stack).
 * <b>Summary:</b>
 * <ul>
 * <li>with one queue, push O(n), other operations constant time.
 * <li>with 3 queues, amortized constant time.
 * </ul>
 */
@SuppressWarnings("unused")
public class ImplementStacksUsingQueues {

    @SuppressWarnings("ConstantConditions")
    private static class MyStack {
        private Queue<Integer> q;

        /**
         * Initialize your data structure here.
         */
        public MyStack() {
            this.q = new LinkedList<>();
        }

        /**
         * Push element x onto stack.
         */
        public void push(int x) {
            q.add(x);
            for (int i = 0; i < q.size() - 1; i++) q.add(q.poll());
        }

        /**
         * Removes the element on top of the stack and returns that element.
         */
        public int pop() {
            return q.poll();
        }

        /**
         * Get the top element.
         */
        public int top() {
            return q.peek();
        }

        /**
         * Returns whether the stack is empty.
         */
        public boolean empty() {
            return q.isEmpty();
        }
    }

    @SuppressWarnings("ConstantConditions")
    private static class MyStack2 {
        private ArrayDeque<Integer> q1, q2, tail;

        /**
         * Initialize your data structure here.
         */
        public MyStack2() {
            this.q1 = new ArrayDeque<>();
            this.q2 = new ArrayDeque<>();
            tail = q1;
        }

        /**
         * Push element x onto stack.
         */
        public void push(int x) {
            tail.add(x);
        }

        /**
         * Removes the element on top of the stack and returns that element.
         */
        public int pop() {
            top();
            int result = tail.remove();
            if (tail == q1) tail = q2;
            else tail = q1;
            return result;
        }

        /**
         * Get the top element.
         */
        @SuppressWarnings("UnusedReturnValue")
        public int top() {
            if (tail == q1) while (q1.size() > 1) q2.add(q1.remove());
            else while (q2.size() > 1) q1.add(q2.remove());
            return tail.peek();
        }

        /**
         * Returns whether the stack is empty.
         */
        public boolean empty() {
            return q1.isEmpty() && q2.isEmpty();
        }
    }
}
