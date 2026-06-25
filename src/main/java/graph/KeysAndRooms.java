package graph;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Queue;

/**
 * LeetCode 841 - Keys and Rooms
 *
 * Given an array of rooms where rooms[i] is the set of keys in room i,
 * starting from room 0 (unlocked), return true if you can visit all rooms.
 */
public final class KeysAndRooms {

    private KeysAndRooms() { }

    /**
     * DFS iterative using a stack.
     * Time: O(N + E) where N = number of rooms, E = total number of keys.
     * Space: O(N) for the visited array and stack.
     */
    public static boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int n = rooms.size();
        boolean[] visited = new boolean[n]; // O(N) space
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0);
        visited[0] = true;
        int count = 1; // track visited count

        while (!stack.isEmpty()) {
            int room = stack.pop(); // O(1) per visit
            for (int key : rooms.get(room)) { // iterate keys (edges)
                if (!visited[key]) {
                    visited[key] = true;
                    count++;
                    stack.push(key);
                }
            }
        }
        return count == n; // all rooms visited?
    }

    /**
     * BFS using a queue.
     * Time: O(N + E) where N = number of rooms, E = total number of keys.
     * Space: O(N) for the visited array and queue.
     */
    public static boolean canVisitAllRoomsBFS(List<List<Integer>> rooms) {
        int n = rooms.size();
        boolean[] visited = new boolean[n]; // O(N) space
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(0);
        visited[0] = true;
        int count = 1;

        while (!queue.isEmpty()) {
            int room = queue.poll(); // O(1) per visit
            for (int key : rooms.get(room)) { // iterate keys (edges)
                if (!visited[key]) {
                    visited[key] = true;
                    count++;
                    queue.offer(key);
                }
            }
        }
        return count == n; // all rooms visited?
    }
}
