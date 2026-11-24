package graph;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

/** LeetCode 1306, medium. Reach a zero by jumping left or right from the current index. */
public final class JumpGameIII {
    private JumpGameIII() {}

    /** Breadth-first search. O(n) time and O(n) space. */
    public static boolean canReach(int[] arr, int start) {
        boolean[] visited = new boolean[arr.length];
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int index = queue.remove();
            int jump = arr[index];
            if (jump == 0) {
                return true;
            }

            int left = index - jump;
            if (left >= 0 && !visited[left]) {
                visited[left] = true;
                queue.add(left);
            }
            int right = index + jump;
            if (right < arr.length && !visited[right]) {
                visited[right] = true;
                queue.add(right);
            }
        }
        return false;
    }

    /** Iterative depth-first search. O(n) time and O(n) space. */
    public static boolean canReachDFS(int[] arr, int start) {
        boolean[] visited = new boolean[arr.length];
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(start);
        visited[start] = true;

        while (!stack.isEmpty()) {
            int index = stack.pop();
            int jump = arr[index];
            if (jump == 0) {
                return true;
            }

            int left = index - jump;
            if (left >= 0 && !visited[left]) {
                visited[left] = true;
                stack.push(left);
            }
            int right = index + jump;
            if (right < arr.length && !visited[right]) {
                visited[right] = true;
                stack.push(right);
            }
        }
        return false;
    }
}
