package stack;

/**
 * LeetCode 1003, medium, tags: string, stack.
 * Determine whether s can be built from the empty string by repeatedly inserting "abc".
 */
public final class CheckIfWordIsValidAfterSubstitutions {
    private CheckIfWordIsValidAfterSubstitutions() {}

    /**
     * Reduce every completed "abc" immediately, reversing an insertion.
     * The stack contains the unreduced prefix; a 'c' must complete its trailing "ab".
     * Each character is pushed and popped at most once: O(n) time and O(n) space.
     */
    public static boolean isValid(String s) {
        char[] stack = new char[s.length()];
        int size = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == 'c') {
                if (size < 2 || stack[size - 2] != 'a' || stack[size - 1] != 'b') {
                    return false;
                }
                size -= 2;
            } else {
                stack[size++] = c;
            }
        }
        return size == 0;
    }
}
