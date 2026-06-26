package graph;

import java.util.*;

/**
 * LeetCode 752 - Open the Lock
 *
 * BFS and Bidirectional BFS solutions.
 */
public final class OpenTheLock {

    private OpenTheLock() {}

    /**
     * BFS approach.
     */
    public static int openLock(String[] deadends, String target) {
        Set<String> dead = new HashSet<>(Arrays.asList(deadends));
        if (dead.contains("0000")) return -1;
        if ("0000".equals(target)) return 0;

        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.offer("0000");
        visited.add("0000");
        int steps = 0;

        while (!queue.isEmpty()) {
            steps++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String curr = queue.poll();
                for (String next : neighbors(curr)) {
                    if (next.equals(target)) return steps;
                    if (!dead.contains(next) && visited.add(next)) {
                        queue.offer(next);
                    }
                }
            }
        }
        return -1;
    }

    /**
     * Bidirectional BFS approach.
     */
    public static int openLockBidirectional(String[] deadends, String target) {
        Set<String> dead = new HashSet<>(Arrays.asList(deadends));
        if (dead.contains("0000")) return -1;
        if ("0000".equals(target)) return 0;
        if (dead.contains(target)) return -1;

        Set<String> beginSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();
        Set<String> visited = new HashSet<>();

        beginSet.add("0000");
        endSet.add(target);
        visited.add("0000");
        visited.add(target);
        int steps = 0;

        while (!beginSet.isEmpty() && !endSet.isEmpty()) {
            // Always expand the smaller frontier
            if (beginSet.size() > endSet.size()) {
                Set<String> temp = beginSet;
                beginSet = endSet;
                endSet = temp;
            }

            Set<String> nextSet = new HashSet<>();
            steps++;
            for (String curr : beginSet) {
                for (String next : neighbors(curr)) {
                    if (endSet.contains(next)) return steps;
                    if (!dead.contains(next) && visited.add(next)) {
                        nextSet.add(next);
                    }
                }
            }
            beginSet = nextSet;
        }
        return -1;
    }

    private static List<String> neighbors(String code) {
        List<String> result = new ArrayList<>(8);
        char[] arr = code.toCharArray();
        for (int i = 0; i < 4; i++) {
            char orig = arr[i];
            // Turn up
            arr[i] = orig == '9' ? '0' : (char) (orig + 1);
            result.add(new String(arr));
            // Turn down
            arr[i] = orig == '0' ? '9' : (char) (orig - 1);
            result.add(new String(arr));
            arr[i] = orig;
        }
        return result;
    }
}
