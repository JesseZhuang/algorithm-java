package graph;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;

import static util.Constants.dirs;

/**
 * LeetCode 864, hard, tags: bit, bfs, matrix, array.
 * <p>
 * You are given an m x n grid where:
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
@SuppressWarnings("unused")
public class SPGetKeys {

    // bfs, mn time and space, 20ms, 49Mb.
    public int shortestPathAllKeys(String[] grid) {
        int m = grid.length, n = grid[0].length(), x = -1, y = -1, maxK = -1;
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                char ch = grid[r].charAt(c);
                if (ch == '@') {
                    x = r;
                    y = c;
                }
                if (ch >= 'a' && ch <= 'f') maxK = Math.max(ch - 'a' + 1, maxK); // only need to +1 here
            }
        }
        Queue<State> q = new ArrayDeque<>();
        // keys (1<<key#),r,c; set saves space comparing to 3D boolean[]: mn vs mnk
        // can use String.format("%d %d %d", r, c, k) as key, space is important
        HashSet<State> vis = new HashSet<>();
        var state = new State(x, y, 0);
        vis.add(state);
        q.add(state);
        int res = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                var cur = q.remove();
                if (cur.k == (1 << maxK) - 1) return res;
                for (int[] d : dirs) {
                    int nr = cur.r + d[0], nc = cur.c + d[1], nk = cur.k;
                    if (nr < 0 || nr > m - 1 || nc < 0 || nc > n - 1) continue;
                    char c = grid[nr].charAt(nc);
                    if (c == '#') continue;
                    if (c >= 'A' && c <= 'F' && (nk & (1 << (c - 'A'))) == 0) continue; // don't have the key
                    if (c >= 'a' && c <= 'f') nk |= 1 << (c - 'a'); // important, same key twice ok, do not use +=
                    state = new State(nr, nc, nk);
                    if (vis.contains(state)) continue;
                    vis.add(state);
                    q.add(state);
                }
            }
            res++;
        }
        return -1;
    }

    record State(int r, int c, int k) { // inner record do not need static
    }
}
