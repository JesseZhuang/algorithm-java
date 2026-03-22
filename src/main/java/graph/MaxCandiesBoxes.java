package graph;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * LeetCode 1298, hard. Tags: graph, breadth-first search, simulation.
 * <p>
 * You have {@code n} boxes labeled {@code 0 .. n-1}. Each box may be open or closed, holds some candies, holds keys
 * to other boxes, and may contain other boxes. You start with some boxes in hand ({@code initialBoxes}). You can open
 * a box only if you have it and you have a key for it (either it started open per {@code status}, or you collected a
 * key). When you open a box, you take its candies, collect all keys inside, and take possession of all boxes contained
 * in it. Return the maximum total candies you can obtain.
 * <p>
 * Example 1:
 * <pre>
 * Input: status = [1,0,1,0], candies = [7,5,4,100], keys = [[],[],[1],[]],
 *        containedBoxes = [[1,2],[3],[],[]], initialBoxes = [0]
 * Output: 16
 * Explanation: Open box 0 (7 candies), get boxes 1 and 2. Open box 2 (4 candies), get key to box 1. Open box 1
 * (5 candies), get box 3. Box 3 stays closed (no key). Total 7+4+5 = 16.
 * </pre>
 * <p>
 * Example 2:
 * <pre>
 * Input: status = [1,0,0,0,0,0], candies = [1,1,1,1,1,1],
 *        keys = [[1,2,3,4,5],[],[],[],[],[]],
 *        containedBoxes = [[1,2,3,4,5],[],[],[],[],[]], initialBoxes = [0]
 * Output: 6
 * </pre>
 * <p>
 * <b>Summary:</b>
 * <p>
 * <ul>
 * <li><b>BFS over openable boxes. O(n^2) worst-case time (keys may repeat across boxes), O(n) extra space.</b>
 * <li>Track possession of each box, whether we have its key, and whether it is already opened; enqueue when we both
 * possess a box and have its key.
 * </ul>
 */
public final class MaxCandiesBoxes {

    private MaxCandiesBoxes() {
    }

    /**
     * @param status         {@code status[i] == 1} means box {@code i} starts open (you have a key implicitly).
     * @param candies        candies inside each box.
     * @param keys             keys found inside each box (indices of other boxes).
     * @param containedBoxes   boxes contained inside each box.
     * @param initialBoxes     box indices you start with.
     * @return maximum candies collectible.
     */
    public static int maxCandies(
            int[] status,
            int[] candies,
            int[][] keys,
            int[][] containedBoxes,
            int[] initialBoxes) {
        int n = status.length;
        boolean[] hasBox = new boolean[n];
        boolean[] opened = new boolean[n];
        boolean[] hasKey = new boolean[n];
        for (int i = 0; i < n; i++) {
            hasKey[i] = status[i] == 1;
        }
        int res = 0;
        Queue<Integer> q = new ArrayDeque<>();
        for (int b : initialBoxes) {
            hasBox[b] = true;
            if (hasKey[b]) {
                opened[b] = true;
                q.add(b);
            }
        }
        while (!q.isEmpty()) {
            int box = q.poll();
            res += candies[box];
            for (int k : keys[box]) {
                if (!hasKey[k]) {
                    hasKey[k] = true;
                    if (hasBox[k] && !opened[k]) {
                        opened[k] = true;
                        q.add(k);
                    }
                }
            }
            for (int b : containedBoxes[box]) {
                hasBox[b] = true;
                if (!opened[b] && hasKey[b]) {
                    opened[b] = true;
                    q.add(b);
                }
            }
        }
        return res;
    }
}
