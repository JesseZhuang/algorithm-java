package stack;

import java.util.*;

/**
 * LeetCode 71 Medium. Tags: String, Stack.
 * <p>
 * Given an absolute path for a Unix-style file system, which begins with a slash '/', transform
 * this absolute path into its simplified canonical path.
 * <p>
 * Constraints:
 * 1 <= path.length <= 3000
 * path consists of English letters, digits, period '.', slash '/' or '_'.
 * path is a valid absolute Unix path.
 */
public final class SimplifyPath {
    private SimplifyPath() {}

    /** Stack approach. O(n) time, O(n) space. */
    public static String simplifyPath(String path) {
        Deque<String> stack = new ArrayDeque<>();
        for (String part : path.split("/")) { // O(n) time
            if (part.equals("..")) {
                if (!stack.isEmpty()) stack.pop(); // O(1)
            } else if (!part.isEmpty() && !part.equals(".")) {
                stack.push(part);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (String dir : stack) sb.insert(0, "/" + dir); // O(n) total
        return sb.isEmpty() ? "/" : sb.toString();
    }
}
