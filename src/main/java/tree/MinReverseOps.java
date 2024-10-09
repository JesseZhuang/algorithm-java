package tree;

import java.util.*;
import java.util.stream.Collectors;

/**
 * LeetCode 2612, hard, tags: bfs, ordered set, array.
 * <p>
 * You are given an integer n and an integer p representing an array arr of length n where all elements are set to 0's,
 * except position p which is set to 1. You are also given an integer array banned containing restricted positions.
 * Perform the following operation on arr:
 * <p>
 * Reverse a subarray with size k if the single 1 is not set to a position in banned.
 * Return an integer array answer with n results where the ith result is the minimum number of operations needed to
 * bring the single 1 to position i in arr, or -1 if it is impossible.
 * <p>
 * Example 1:
 * <p>
 * Input: n = 4, p = 0, banned = [1,2], k = 4
 * <p>
 * Output: [0,-1,-1,1]
 * <p>
 * Explanation:
 * <p>
 * Initially 1 is placed at position 0 so the number of operations we need for position 0 is 0.
 * We can never place 1 on the banned positions, so the answer for positions 1 and 2 is -1.
 * Perform the operation of size 4 to reverse the whole array.
 * After a single operation 1 is at position 3 so the answer for position 3 is 1.
 * Example 2:
 * <p>
 * Input: n = 5, p = 0, banned = [2,4], k = 3
 * <p>
 * Output: [0,-1,-1,-1,-1]
 * <p>
 * Explanation:
 * <p>
 * Initially 1 is placed at position 0 so the number of operations we need for position 0 is 0.
 * We cannot perform the operation on the subarray positions [0, 2] because position 2 is in banned.
 * Because 1 cannot be set at position 2, it is impossible to set 1 at other positions in more operations.
 * Example 3:
 * <p>
 * Input: n = 4, p = 2, banned = [0,1,3], k = 1
 * <p>
 * Output: [-1,-1,0,-1]
 * <p>
 * Explanation:
 * <p>
 * Perform operations of size 1 and 1 never changes its position.
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 10^5
 * 0 <= p <= n - 1
 * 0 <= banned.length <= n - 1
 * 0 <= banned[i] <= n - 1
 * 1 <= k <= n
 * banned[i] != p
 * all values in banned are unique
 * <p>
 * Hint 1
 * Can we use a breadth-first search to find the minimum number of operations?
 * Hint 2
 * Find the beginning and end indices of the subarray of size k that can be reversed to bring 1 to a
 * particular position.
 * Hint 3
 * Can we visit every index or do we need to consider the parity of k?
 */
@SuppressWarnings("unused")
public class MinReverseOps {
    // n, n. 20ms, 63.88mb. use next neighbor array and set visited to hi+2. credit to dumbunny8128@.
    static class Solution1 {
        public int[] minReverseOperations(int n, int p, int[] banned, int k) {
            int[] res = new int[n], nn = new int[n]; // next neighbor
            Arrays.fill(res, -1);
            res[p] = 0;
            for (int i = 0; i < n; i++) nn[i] = i + 2; // next neighbor initially increments by 2
            for (int b : banned) res[b] = -2;
            int depth = 0, step = k - 1;
            List<Integer> q = Collections.singletonList(p);
            while (!q.isEmpty()) {
                depth++;
                List<Integer> nq = new ArrayList<>();
                for (int cur : q) {
                    int lo = Math.max(cur - step, 0); // range lo reachable from cur
                    lo = 2 * lo + step - cur; // after reverse -> lo + lo + step - cur
                    int hi = Math.min(cur + step, n - 1) - step;
                    hi = 2 * hi + step - cur;
                    int postHi = hi + 2, nei = lo;
                    while (nei <= hi) {
                        int nNei = nn[nei]; // next nei
                        nn[nei] = postHi;
                        if (res[nei] == -1) {
                            nq.add(nei);
                            res[nei] = depth;
                        }
                        nei = nNei;
                    }
                }
                q = nq;
            }
            for (int b : banned) res[b] = -1;
            return res;
        }
    }

    // [0,0,0,0,1,0,0,0,0] n:9,k:3,cur:4, lo,hi:[2,4]->[2,6]:[2,4,6] increments by 2
    // solution 2, tree set, 418ms, 61.74mb. nlgn, n.
    static class SolutionTreeSet {
        public int[] minReverseOperations(int n, int p, int[] banned, int k) {
            TreeSet<Integer>[] remaining = new TreeSet[2];
            remaining[0] = new TreeSet<>();
            remaining[1] = new TreeSet<>();
            Set<Integer> ban = Arrays.stream(banned).boxed().collect(Collectors.toSet());
            for (int i = 0; i < n; i++)
                if (i != p && !ban.contains(i)) remaining[i & 1].add(i);
            ArrayDeque<Integer> q = new ArrayDeque<>();
            q.add(p);
            int[] res = new int[n];
            Arrays.fill(res, -1);
            res[p] = 0;
            int step = k - 1;
            while (!q.isEmpty()) {
                int cur = q.remove();
                int lo = Math.max(cur - step, 0); // range lo reachable from cur
                lo = 2 * lo + step - cur; // after reverse -> lo + lo + step - cur
                int hi = Math.min(cur + step, n - 1) - step;
                hi = 2 * hi + step - cur;
                Set<Integer> neighbors = new HashSet<>(remaining[lo % 2].subSet(lo, hi + 1));
                for (int nei : neighbors) {
                    q.add(nei);
                    res[nei] = res[cur] + 1;
                }
                remaining[lo % 2].removeAll(neighbors);
            }
            return res;
        }
    }
}
