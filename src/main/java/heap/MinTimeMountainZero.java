package heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * LeetCode 3296, medium, weekly 416 Q2.
 * <p>
 * You are given an integer mountainHeight denoting the height of a mountain.
 * <p>
 * You are also given an integer array workerTimes representing the work time of workers in seconds.
 * <p>
 * The workers work simultaneously to reduce the height of the mountain. For worker i:
 * <p>
 * To decrease the mountain's height by x, it takes workerTimes[i] + workerTimes[i] * 2 + ... +
 * workerTimes[i] * x seconds.
 * For example:
 * To reduce the height of the mountain by 1, it takes workerTimes[i] seconds.
 * To reduce the height of the mountain by 2, it takes workerTimes[i] + workerTimes[i] * 2 seconds, and so on.
 * Return an integer representing the minimum number of seconds required for the workers to make the height of
 * the mountain 0.
 * <p>
 * Example 1:
 * <p>
 * Input: mountainHeight = 4, workerTimes = [2,1,1]
 * <p>
 * Output: 3
 * <p>
 * Explanation:
 * <p>
 * One way the height of the mountain can be reduced to 0 is:
 * <p>
 * Worker 0 reduces the height by 1, taking workerTimes[0] = 2 seconds.
 * Worker 1 reduces the height by 2, taking workerTimes[1] + workerTimes[1] * 2 = 3 seconds.
 * Worker 2 reduces the height by 1, taking workerTimes[2] = 1 second.
 * Since they work simultaneously, the minimum time needed is max(2, 3, 1) = 3 seconds.
 * <p>
 * Example 2:
 * <p>
 * Input: mountainHeight = 10, workerTimes = [3,2,2,4]
 * <p>
 * Output: 12
 * <p>
 * Explanation:
 * <p>
 * Worker 0 reduces the height by 2, taking workerTimes[0] + workerTimes[0] * 2 = 9 seconds.
 * Worker 1 reduces the height by 3, taking workerTimes[1] + workerTimes[1] * 2 + workerTimes[1] * 3 = 12 seconds.
 * Worker 2 reduces the height by 3, taking workerTimes[2] + workerTimes[2] * 2 + workerTimes[2] * 3 = 12 seconds.
 * Worker 3 reduces the height by 2, taking workerTimes[3] + workerTimes[3] * 2 = 12 seconds.
 * The number of seconds needed is max(9, 12, 12, 12) = 12 seconds.
 * <p>
 * Example 3:
 * Input: mountainHeight = 5, workerTimes = [1]
 * Output: 15
 * <p>
 * Explanation:
 * <p>
 * There is only one worker in this example, so the answer is workerTimes[0] + workerTimes[0] * 2 + workerTimes[0] * 3
 * + workerTimes[0] * 4 + workerTimes[0] * 5 = 15.
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= mountainHeight <= 10^5, n
 * 1 <= workerTimes.length <= 10^4, k
 * 1 <= workerTimes[i] <= 10^6
 */
@SuppressWarnings("unused")
public class MinTimeMountainZero {
    static class Solution {
        public long minNumberOfSeconds(int mountainHeight, int[] workerTimes) {
            int n = workerTimes.length, mh = mountainHeight;
            long[] times = new long[n];
            long[] wt = new long[n];
            for (int i = 0; i < n; i++) wt[i] = workerTimes[i]; // auto cast
            var pq = new PriorityQueue<Info>(Comparator.comparingLong(i -> i.nt));
            for (int i = 0; i < n; i++)
                pq.add(new Info(wt[i], 1, i));
            while (mh > 0) {
                Info cur = pq.remove();
                long nt = cur.nt;
                int r = cur.r, i = cur.i;
                mh--;
                times[i] = nt;
                r += 1;
                pq.add(new Info(nt + wt[i] * r, r, i));
            }
            return Arrays.stream(times).max().orElseThrow();
        }

        static class Info {
            long nt; // next total
            int r; // round
            int i; // index

            Info(long nt, int r, int i) {
                this.nt = nt;
                this.r = r;
                this.i = i;
            }
        }
    }
}
