package stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode 155, medium, tags: stack, design.
 * <p>
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 * <p>
 * Implement the MinStack class:
 * <p>
 * MinStack() initializes the stack object.
 * void push(int val) pushes the element val onto the stack.
 * void pop() removes the element on the top of the stack.
 * int top() gets the top element of the stack.
 * int getMin() retrieves the minimum element in the stack.
 * <p>
 * You must implement a solution with O(1) time complexity for each function.
 * <p>
 * Example 1:
 * Input: ["MinStack","push","push","push","getMin","pop","top","getMin"]
 * [[],[-2],[0],[-3],[],[],[],[]]
 * Output: [null,null,null,null,-3,null,0,-2]
 * <p>
 * Constraints:
 * -2^31 <= val <= 2^31 - 1
 * Methods pop, top and getMin operations will always be called on non-empty stacks.
 * At most 3 * 10^4 calls will be made to push, pop, top, and getMin.
 */
public final class MinStack {
    private MinStack() {}

    // Solution 1: two stacks. O(1) time for all operations, O(n) space.
    public static int[] twoStacks(String[] operations, int[][] args) {
        int[] result = new int[operations.length];
        Deque<Integer> stack = new ArrayDeque<>();
        Deque<Integer> minStack = new ArrayDeque<>(); // tracks current min at each level
        for (int i = 0; i < operations.length; i++) {
            switch (operations[i]) {
                case "push":
                    stack.push(args[i][0]); // O(1)
                    minStack.push(minStack.isEmpty() ? args[i][0] : Math.min(args[i][0], minStack.peek())); // O(1)
                    result[i] = Integer.MIN_VALUE;
                    break;
                case "pop":
                    stack.pop(); // O(1)
                    minStack.pop(); // O(1)
                    result[i] = Integer.MIN_VALUE;
                    break;
                case "top":
                    result[i] = stack.peek(); // O(1)
                    break;
                case "getMin":
                    result[i] = minStack.peek(); // O(1)
                    break;
                default:
                    result[i] = Integer.MIN_VALUE;
                    break;
            }
        }
        return result;
    }

    // Solution 2: single stack with tuples. O(1) time for all operations, O(n) space.
    public static int[] singleStack(String[] operations, int[][] args) {
        int[] result = new int[operations.length];
        Deque<int[]> stack = new ArrayDeque<>(); // stores {val, currentMin}
        for (int i = 0; i < operations.length; i++) {
            switch (operations[i]) {
                case "push":
                    int curMin = stack.isEmpty() ? args[i][0] : Math.min(args[i][0], stack.peek()[1]);
                    stack.push(new int[]{args[i][0], curMin}); // O(1)
                    result[i] = Integer.MIN_VALUE;
                    break;
                case "pop":
                    stack.pop(); // O(1)
                    result[i] = Integer.MIN_VALUE;
                    break;
                case "top":
                    result[i] = stack.peek()[0]; // O(1)
                    break;
                case "getMin":
                    result[i] = stack.peek()[1]; // O(1)
                    break;
                default:
                    result[i] = Integer.MIN_VALUE;
                    break;
            }
        }
        return result;
    }
}
