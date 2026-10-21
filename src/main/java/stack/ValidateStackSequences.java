package stack;

import java.util.ArrayDeque;
import java.util.Deque;

public final class ValidateStackSequences {
    private ValidateStackSequences() {}

    public static boolean validateStackSequences(int[] pushed, int[] popped) {
        Deque<Integer> stack = new ArrayDeque<>();
        int poppedIndex = 0;
        for (int value : pushed) {
            stack.push(value);
            // Each element can leave the stack only once: O(n) pops overall.
            while (!stack.isEmpty() && poppedIndex < popped.length
                    && stack.peek() == popped[poppedIndex]) {
                stack.pop();
                poppedIndex++;
            }
        }
        return poppedIndex == popped.length;
    }
}
