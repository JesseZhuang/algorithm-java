package stack;

public final class RemoveDuplicateLetters {
    private RemoveDuplicateLetters() {}

    public static String removeDuplicateLetters(String s) {
        int[] lastIndex = new int[26];
        for (int i = 0; i < s.length(); i++) {
            lastIndex[s.charAt(i) - 'a'] = i;
        }
        boolean[] inStack = new boolean[26];
        StringBuilder stack = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (inStack[c - 'a']) continue;
            while (stack.length() > 0 && stack.charAt(stack.length() - 1) > c
                    && lastIndex[stack.charAt(stack.length() - 1) - 'a'] > i) {
                inStack[stack.charAt(stack.length() - 1) - 'a'] = false;
                stack.deleteCharAt(stack.length() - 1);
            }
            stack.append(c);
            inStack[c - 'a'] = true;
        }
        return stack.toString();
    }
}
