package stack;

public final class RemoveKDigits {
    private RemoveKDigits() {}

    public static String removeKdigits(String num, int k) {
        StringBuilder stack = new StringBuilder();
        for (char c : num.toCharArray()) {
            while (k > 0 && stack.length() > 0 && stack.charAt(stack.length() - 1) > c) {
                stack.deleteCharAt(stack.length() - 1);
                k--;
            }
            stack.append(c);
        }
        stack.setLength(stack.length() - k);
        if (stack.length() == 0) return "0";
        // strip leading zeros
        int start = 0;
        while (start < stack.length() - 1 && stack.charAt(start) == '0') start++;
        return stack.substring(start);
    }
}
