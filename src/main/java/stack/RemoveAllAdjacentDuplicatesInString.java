package stack;

public final class RemoveAllAdjacentDuplicatesInString {
    private RemoveAllAdjacentDuplicatesInString() {}

    /** Time: O(n); space: O(n) for the stack, where n is the string length. */
    public static String removeDuplicates(String s) {
        StringBuilder stack = new StringBuilder();
        for (int index = 0; index < s.length(); index++) {
            char current = s.charAt(index);
            if (stack.length() > 0 && stack.charAt(stack.length() - 1) == current) {
                stack.setLength(stack.length() - 1);
            } else {
                stack.append(current);
            }
        }
        return stack.toString();
    }
}
