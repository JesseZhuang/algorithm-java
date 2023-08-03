package stack;

import java.util.Stack;

/**
 * LeetCode 1381, medium, tags: array, stack, design.
 * <p>
 * Design a stack that supports increment operations on its elements.
 * <p>
 * Implement the CustomStack class:
 * <p>
 * CustomStack(int maxSize) Initializes the object with maxSize which is the maximum number of elements in the stack.
 * void push(int x) Adds x to the top of the stack if the stack has not reached the maxSize.
 * int pop() Pops and returns the top of the stack or -1 if the stack is empty.
 * void inc(int k, int val) Increments the bottom k elements of the stack by val. If there are less than k elements in
 * the stack, increment all the elements in the stack.
 * <pre>
 * Example 1:
 *
 * Input
 * ["CustomStack","push","push","pop","push","push","push","increment","increment","pop","pop","pop","pop"]
 * [[3],[1],[2],[],[2],[3],[4],[5,100],[2,100],[],[],[],[]]
 * Output
 * [null,null,null,2,null,null,null,null,null,103,202,201,-1]
 * Explanation
 * CustomStack stk = new CustomStack(3); // Stack is Empty []
 * stk.push(1);                          // stack becomes [1]
 * stk.push(2);                          // stack becomes [1, 2]
 * stk.pop();                            // return 2 --> Return top of the stack 2, stack becomes [1]
 * stk.push(2);                          // stack becomes [1, 2]
 * stk.push(3);                          // stack becomes [1, 2, 3]
 * stk.push(4);                          // stack still [1, 2, 3], Do not add another elements as size is 4
 * stk.increment(5, 100);                // stack becomes [101, 102, 103]
 * stk.increment(2, 100);                // stack becomes [201, 202, 103]
 * stk.pop();                            // return 103 --> Return top of the stack 103, stack becomes [201, 202]
 * stk.pop();                            // return 202 --> Return top of the stack 202, stack becomes [201]
 * stk.pop();                            // return 201 --> Return top of the stack 201, stack becomes []
 * stk.pop();                            // return -1 --> Stack is empty return -1.
 * </pre>
 * Constraints:
 * <p>
 * 1 <= maxSize, x, k <= 1000
 * 0 <= val <= 100
 * At most 1000 calls will be made to each method of increment, push and pop each separately.
 * <p>
 * Hints
 * <p>
 * 1. Use an array to represent the stack. Push will add new integer to the array.
 * Pop removes the last element in the array and increment will add val to the first k elements of the array.
 * 2. This solution run in O(1) per push and pop and O(k) per increment.
 */
public class DesignStackIncrementOp {
    // 5ms, 44.3 Mb. lazy increment
    class CustomStack {
        int n;
        int[] inc; // check prefix sum and difference array
        Stack<Integer> stack;

        // O(N) time and space.
        public CustomStack(int maxSize) {
            n = maxSize;
            inc = new int[n]; // incremented values for [0,i]
            stack = new Stack<>();
        }


        // O(1) time and space.
        public void push(int x) {
            if (stack.size() < n) stack.push(x);
        }

        //// O(1) time and space.
        public int pop() {
            int i = stack.size() - 1;
            if (i < 0) return -1;
            if (i > 0) inc[i - 1] += inc[i];
            int res = stack.pop() + inc[i];
            inc[i] = 0;
            return res;
        }

        // // O(1) time and space.
        public void increment(int k, int val) {
            int i = Math.min(k, stack.size()) - 1;
            if (i >= 0) inc[i] += val;
        }
    }
}
