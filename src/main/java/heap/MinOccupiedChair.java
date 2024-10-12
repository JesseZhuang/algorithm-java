package heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.TreeSet;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toCollection;

/**
 * LeetCode 1942, medium, tags: array, hash table, heap.
 * <p>
 * There is a party where n friends numbered from 0 to n - 1 are attending. There is an infinite number of chairs in
 * this party that are numbered from 0 to infinity. When a friend arrives at the party, they sit on the unoccupied
 * chair with the smallest number.
 * <p>
 * For example, if chairs 0, 1, and 5 are occupied when a friend comes, they will sit on chair number 2.
 * When a friend leaves the party, their chair becomes unoccupied at the moment they leave. If another friend arrives
 * at that same moment, they can sit in that chair.
 * <p>
 * You are given a 0-indexed 2D integer array times where times[i] = [arrivali, leavingi], indicating the arrival and
 * leaving times of the ith friend respectively, and an integer targetFriend. All arrival times are distinct.
 * <p>
 * Return the chair number that the friend numbered targetFriend will sit on.
 * <p>
 * Example 1:
 * <p>
 * Input: times = [[1,4],[2,3],[4,6]], targetFriend = 1
 * Output: 1
 * Explanation:
 * - Friend 0 arrives at time 1 and sits on chair 0.
 * - Friend 1 arrives at time 2 and sits on chair 1.
 * - Friend 1 leaves at time 3 and chair 1 becomes empty.
 * - Friend 0 leaves at time 4 and chair 0 becomes empty.
 * - Friend 2 arrives at time 4 and sits on chair 0.
 * Since friend 1 sat on chair 1, we return 1.
 * Example 2:
 * <p>
 * Input: times = [[3,10],[1,5],[2,6]], targetFriend = 0
 * Output: 2
 * Explanation:
 * - Friend 1 arrives at time 1 and sits on chair 0.
 * - Friend 2 arrives at time 2 and sits on chair 1.
 * - Friend 0 arrives at time 3 and sits on chair 2.
 * - Friend 1 leaves at time 5 and chair 0 becomes empty.
 * - Friend 2 leaves at time 6 and chair 1 becomes empty.
 * - Friend 0 leaves at time 10 and chair 2 becomes empty.
 * Since friend 0 sat on chair 2, we return 2.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * n == times.length
 * 2 <= n <= 10^4
 * times[i].length == 2
 * 1 <= arrivali < leavingi <= 10^5
 * 0 <= targetFriend <= n - 1
 * Each arrivali time is distinct.
 * <p>
 * Hint 1
 * Sort times by arrival time.
 * Hint 2
 * for each arrival_i find the smallest unoccupied chair and mark it as occupied until leaving_i.
 */
@SuppressWarnings("unused")
public class MinOccupiedChair {
    // treeset+pq, nlgn, n. 48ms, 51.5mb.
    // 2 min pq for available and occupied chairs, same complexity.
    static class Solution {
        public int smallestChair(int[][] times, int targetFriend) {
            int tA = times[targetFriend][0], n = times.length; // target arrive time
            Arrays.sort(times, Comparator.comparingInt(a -> a[0])); // by arrive time
            // heap: [leaving time, chair id]
            PriorityQueue<int[]> occupy = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
            TreeSet<Integer> free = IntStream.range(0, n).boxed().collect(toCollection(TreeSet::new));
            int chair = -1; // chair to occupy
            for (int[] time : times) {
                int arrive = time[0], leave = time[1];
                while (!occupy.isEmpty() && occupy.peek()[0] <= arrive) free.add(occupy.remove()[1]);
                chair = free.first();
                free.remove(chair);
                occupy.add(new int[]{leave, chair});
                if (arrive == tA) break;
            }
            return chair;
        }
    }
}
