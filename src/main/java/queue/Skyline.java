package queue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * LeetCode 218, hard, tags: array, divide and conquer, binary indexed tree, segment tree, line sweep,
 * heap (priority queue), ordered set.
 * <p>
 * A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed
 * from a distance. Given the locations and heights of all the buildings, return the skyline formed
 * by these buildings collectively.
 * <p>
 * The geometric information of each building is given in the array buildings where
 * buildings[i] = [left_i, right_i, height_i]:
 * <p>
 * left_i is the x coordinate of the left edge of the ith building.
 * right_i is the x coordinate of the right edge of the ith building.
 * height_i is the height of the ith building.
 * You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.
 * <p>
 * The skyline should be represented as a list of "key points" sorted by their x-coordinate in the form
 * [[x1,y1],[x2,y2],...]. Each key point is the left endpoint of some horizontal segment in the skyline
 * except the last point in the list, which always has a y-coordinate 0 and is used to mark the skyline's
 * termination where the rightmost building ends. Any ground between the leftmost and rightmost buildings
 * should be part of the skyline's contour.
 * <p>
 * Note: There must be no consecutive horizontal lines of equal height in the output skyline. For instance,
 * [...,[2 3],[4 5],[7 5],[11 5],[12 7],...] is not acceptable; the three lines of height 5 should be merged
 * into one in the final output as such: [...,[2 3],[4 5],[12 7],...]
 * <p>
 * Example 1: https://assets.leetcode.com/uploads/2020/12/01/merged.jpg
 * <p>
 * Input: buildings = [[2,9,10],[3,7,15],[5,12,12],[15,20,10],[19,24,8]]
 * Output: [[2,10],[3,15],[7,12],[12,0],[15,10],[20,8],[24,0]]
 * Explanation:
 * Figure A shows the buildings of the input.
 * Figure B shows the skyline formed by those buildings. The red points in figure B represent the key points
 * in the output list.
 * Example 2:
 * <p>
 * Input: buildings = [[0,2,3],[2,5,3]]
 * Output: [[0,3],[5,0]]
 * <p>
 * Constraints:
 * <p>
 * 1 <= buildings.length <= 10^4
 * 0 <= left_i < right_i <= 2^31 - 1
 * 1 <= height_i <= 2^31 - 1
 * buildings is sorted by left_i in non-decreasing order.
 */
public class Skyline {
    // treemap O(nLgn) time O(n) space. 18ms, 46.14 Mb.
    public static List<List<Integer>> getSkylineMap(int[][] buildings) {
        List<List<Integer>> res = new ArrayList<>();
        List<int[]> vLines = new ArrayList<>();
        for (int[] b : buildings) {
            vLines.add(new int[]{b[0], b[2]}); // left_i, h, note new int[] required
            vLines.add(new int[]{b[1], -b[2]}); // right_i, -h
        }
        // order by x then by height opposite, note collections.sort not arrays.sort
        Collections.sort(vLines, (l1, l2) -> l1[0] == l2[0] ? l2[1] - l1[1] : l1[0] - l2[0]);
        TreeMap<Integer, Integer> hECount = new TreeMap<>(); // height -> count
        hECount.put(0, 1); // dummy 0 height building count 1
        int prev = 0; // previous height
        for (int[] l : vLines) {
            if (l[1] > 0) hECount.put(l[1], hECount.getOrDefault(l[1], 0) + 1);
            else { // height -> count--
                hECount.put(-l[1], hECount.get(-l[1]) - 1);
                if (hECount.get(-l[1]) == 0) hECount.remove(-l[1]);
            }
            int maxH = hECount.lastKey(); // tallest building in the tree map
            if (maxH != prev) { // if new height, add key point
                res.add(Arrays.asList(l[0], maxH));
                prev = maxH;
            }
        }
        return res;
    }

    // solution 1, PQ, 8ms, 46.34 Mb. O(n) space. O(nLgn) time. harder to understand than the map method.
    public static List<List<Integer>> getSkylinePQ(int[][] buildings) {
        List<List<Integer>> res = new ArrayList<>();
        // height, right_i. sort by height opposite then right_i opposite
        PriorityQueue<int[]> pq = new PriorityQueue<>((b1, b2) -> b1[2] == b2[2] ? b2[1] - b1[1] : b2[2] - b1[2]);
        int i = 0;
        while (i < buildings.length || !pq.isEmpty()) { // do not forget !
            // tallest right_i or cur building left_i: next curX to process
            // tallest on pq: if same height, pick larger right_i
            int curX = pq.isEmpty() ? buildings[i][0] : pq.peek()[1];
            //  buildings are all processed or curX < cur building left_i
            if (i >= buildings.length || curX < buildings[i][0]) { // >= important
                // pop processed buildings: right_i <= curX
                while (!pq.isEmpty() && pq.peek()[1] <= curX) pq.poll();
            } else {
                // if the next new building starts before the top one ends: buildings[i][0] <= curX
                curX = buildings[i][0];
                // add all the buildings that starts at the same point to pq
                while (i < buildings.length && curX == buildings[i][0]) pq.offer(buildings[i++]);
            }
            int curH = pq.isEmpty() ? 0 : pq.peek()[2]; // tallest height, do not forget empty check
            if (res.isEmpty() || res.get(res.size() - 1).get(1) != curH) {
                res.add(Arrays.asList(curX, curH));
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] buildings = {{2, 9, 10}, {3, 7, 15}, {5, 12, 12}, {15, 20, 10}, {19, 24, 8}};
//        buildings = new int[][]{{1, 7, 10}, {2, 3, 5}, {4, 5, 6}};
        System.out.println(getSkylinePQ(buildings));
    }
}
