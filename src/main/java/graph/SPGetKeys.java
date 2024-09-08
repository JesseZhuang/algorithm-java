package graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

import static util.Constants.dirs;

/**
 * LeetCode 864, hard, tags: bit, bfs, matrix, array.
 * <p>
 * You are given an m x n grid grid where:
 * <p>
 * '.' is an empty cell.
 * '#' is a wall.
 * '@' is the starting point.
 * Lowercase letters represent keys.
 * Uppercase letters represent locks.
 * You start at the starting point and one move consists of walking one space in one of the four cardinal directions.
 * You cannot walk outside the grid, or walk into a wall.
 * <p>
 * If you walk over a key, you can pick it up and you cannot walk over a lock unless you have its corresponding key.
 * <p>
 * For some 1 <= k <= 6, there is exactly one lowercase and one uppercase letter of the first k letters of the
 * English alphabet in the grid. This means that there is exactly one key for each lock, and one lock for each key;
 * and also that the letters used to represent the keys and locks were chosen in the same order as the English alphabet.
 * <p>
 * Return the lowest number of moves to acquire all keys. If it is impossible, return -1.
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: grid = ["@.a..","###.#","b.A.B"]
 * Output: 8
 * Explanation: Note that the goal is to obtain all the keys not to open all the locks.
 * Example 2:
 * <p>
 * <p>
 * Input: grid = ["@..aA","..B#.","....b"]
 * Output: 6
 * Example 3:
 * <p>
 * <p>
 * Input: grid = ["@Aa"]
 * Output: -1
 * <p>
 * <p>
 * Constraints:
 * <p>
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 30
 * grid[i][j] is either an English letter, '.', '#', or '@'.
 * There is exactly one '@' in the grid.
 * The number of keys in the grid is in the range [1, 6].
 * Each key in the grid is unique.
 * Each key in the grid has a matching lock.
 */
public class SPGetKeys {

    // bfs, mn time and space, 20ms, 49Mb.
    public int shortestPathAllKeys(String[] grid) {
        int m = grid.length, n = grid[0].length(), x = -1, y = -1, maxK = -1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = grid[i].charAt(j);
                if (c == '@') {
                    x = i;
                    y = j;
                }
                if (c >= 'a' && c <= 'f') maxK = Math.max(c - 'a' + 1, maxK);
            }
        }
        State start = new State(0, x, y);
        Queue<State> q = new LinkedList<>();
        HashSet<State> visited = new HashSet<>(); // keys (1<<key#),r,c
        visited.add(start);
        q.add(start);
        int res = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                State cur = q.remove();
                if (cur.keys == (1 << maxK) - 1) return res;
                for (int[] d : dirs) {
                    int ni = cur.i + d[0], nj = cur.j + d[1], keys = cur.keys;
                    if (ni < 0 || ni > m - 1 || nj < 0 || nj > n - 1) continue;
                    char c = grid[ni].charAt(nj);
                    if (c == '#') continue;
                    if (c >= 'A' && c <= 'F' && (keys & (1 << (c - 'A'))) == 0) continue; // don't have the key
                    if (c >= 'a' && c <= 'f') keys |= 1 << (c - 'a');
                    State ns = new State(keys, ni, nj);
                    if (visited.contains(ns)) continue;
                    visited.add(ns);
                    q.add(ns);
                }
            }
            res++;
        }
        return -1;
    }

    public static class State {
        int keys, i, j;

        State(int keys, int i, int j) {
            this.keys = keys; // 'a'->b1, 'b'-> b10
            this.i = i;
            this.j = j;
        }

        @Override
        public final boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof State)) return false;

            State state = (State) o;
            return keys == state.keys && i == state.i && j == state.j;
        }

        @Override
        public int hashCode() {
            int result = keys;
            result = 31 * result + i;
            result = 31 * result + j;
            return result;
        }
    }
}
