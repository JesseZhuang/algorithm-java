package stack;

import java.util.*;

/**
 * LeetCode 895, hard, tags: hash table, stack, design, ordered set.
 * <p>
 * Design a stack-like data structure to push elements to the stack and pop the most frequent element
 * from the stack.
 * <p>
 * Implement the FreqStack class:
 * <p>
 * FreqStack() constructs an empty frequency stack.
 * void push(int val) pushes an integer val onto the top of the stack.
 * int pop() removes and returns the most frequent element in the stack.
 * If there is a tie for the most frequent element, the element closest to the stack's top is
 * removed and returned.
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["FreqStack", "push", "push", "push", "push", "push", "push", "pop", "pop", "pop", "pop"]
 * [[], [5], [7], [5], [7], [4], [5], [], [], [], []]
 * Output
 * [null, null, null, null, null, null, null, 5, 7, 5, 4]
 * <p>
 * Constraints:
 * <p>
 * 0 <= val <= 10^9
 * At most 2 * 10^4 calls total will be made to push and pop.
 * It is guaranteed that there will be at least one element in the stack before calling pop.
 */
public final class MaxFreqStack {
    private MaxFreqStack() {}

    /**
     * HashMap + group stacks. O(1) time for push and pop, O(n) space total.
     */
    static class FreqStack {
        Map<Integer, Integer> freq; // val -> frequency count, O(n) space
        Map<Integer, Deque<Integer>> group; // frequency -> stack of vals, O(n) space
        int maxFreq;

        public FreqStack() {
            freq = new HashMap<>();
            group = new HashMap<>();
            maxFreq = 0;
        }

        // O(1) time
        public void push(int val) {
            int f = freq.merge(val, 1, Integer::sum); // increment freq
            group.computeIfAbsent(f, k -> new ArrayDeque<>()).push(val); // push to group[f]
            maxFreq = Math.max(maxFreq, f); // update maxFreq
        }

        // O(1) time
        public int pop() {
            int val = group.get(maxFreq).pop(); // pop from most frequent group
            freq.merge(val, -1, Integer::sum); // decrement freq
            if (group.get(maxFreq).isEmpty()) maxFreq--; // shrink maxFreq if group empty
            return val;
        }
    }
}
