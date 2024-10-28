package stack;

import java.util.TreeMap;

/**
 * LeetCode 853, LintCode 1477, medium, tags: monotonic stack, array, sorting.
 * Companies: Google.
 * <p>
 * There are n cars at given miles away from the starting mile 0, traveling to reach the mile target.
 * <p>
 * You are given two integer array position and speed, both of length n, where position[i] is the starting mile of
 * the ith car and speed[i] is the speed of the ith car in miles per hour.
 * <p>
 * A car cannot pass another car, but it can catch up and then travel next to it at the speed of the slower car.
 * <p>
 * A car fleet is a car or cars driving next to each other. The speed of the car fleet is the minimum speed of any
 * car in the fleet.
 * <p>
 * If a car catches up to a car fleet at the mile target, it will still be considered as part of the car fleet.
 * <p>
 * Return the number of car fleets that will arrive at the destination.
 * <p>
 * Example 1:
 * <p>
 * Input: target = 12, position = [10,8,0,5,3], speed = [2,4,1,1,3]
 * <p>
 * Output: 3
 * <p>
 * Explanation:
 * <p>
 * The cars starting at 10 (speed 2) and 8 (speed 4) become a fleet, meeting each other at 12. The fleet forms
 * at target.
 * The car starting at 0 (speed 1) does not catch up to any other car, so it is a fleet by itself.
 * The cars starting at 5 (speed 1) and 3 (speed 3) become a fleet, meeting each other at 6. The fleet moves at
 * speed 1 until it reaches target.
 * Example 2:
 * <p>
 * Input: target = 10, position = [3], speed = [3]
 * <p>
 * Output: 1
 * <p>
 * Explanation:
 * <p>
 * There is only one car, hence there is only one fleet.
 * Example 3:
 * <p>
 * Input: target = 100, position = [0,2,4], speed = [4,2,1]
 * <p>
 * Output: 1
 * <p>
 * Explanation:
 * <p>
 * The cars starting at 0 (speed 4) and 2 (speed 2) become a fleet, meeting each other at 4. The car starting
 * at 4 (speed 1) travels to 5.
 * Then, the fleet at 4 (speed 2) and the car at position 5 (speed 1) become one fleet, meeting each other at 6.
 * The fleet moves at speed 1 until it reaches target.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * n == position.length == speed.length
 * 1 <= n <= 10^5
 * 0 < target <= 10^6
 * 0 <= position[i] < target
 * All the values of position are unique.
 * 0 < speed[i] <= 10^6
 */
@SuppressWarnings("unused")
public class CarFleet {
    // nlgn, n.
    static class Solution {
        public int carFleet(int target, int[] position, int[] speed) {
            TreeMap<Integer, Double> m = new TreeMap<>();
            // sort by position, reverse order
            for (int i = 0; i < position.length; ++i)
                m.put(-position[i], (double) (target - position[i]) / speed[i]);
            int res = 0;
            double cur = 0;
            for (double time : m.values()) {
                if (time <= cur) continue; // time > cur, stuck behind
                cur = time;
                res++;
            }
            return res;
        }
    }
}
