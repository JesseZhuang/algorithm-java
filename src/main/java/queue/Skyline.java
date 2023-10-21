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
        List<int[]> lines = new ArrayList<>();
        for (int[] b : buildings) {
            lines.add(new int[]{b[0], b[2]}); // left_i, h
            lines.add(new int[]{b[1], -b[2]}); // right_i, -h
        }
        // order by x then by height opposite
        Collections.sort(lines, (l1, l2) -> l1[0] == l2[0] ? l2[1] - l1[1] : l1[0] - l2[0]);
        TreeMap<Integer, Integer> map = new TreeMap<>(); // height -> count
        map.put(0, 1); // dummy 0 height building count 1
        int prev = 0; // previous height
        for (int[] l : lines) {
            if (l[1] > 0) map.put(l[1], map.getOrDefault(l[1], 0) + 1); // height -> count++
            else { // height -> count--
                int f = map.get(-l[1]);
                if (f == 1) map.remove(-l[1]);
                else map.put(-l[1], f - 1);
            }
            int cur = map.lastKey(); // tallest building in the tree map
            if (cur != prev) { // if new height, add key point
                res.add(Arrays.asList(l[0], cur));
                prev = cur;
            }
        }
        return res;
    }

    // PQ, 8ms, 46.34 Mb. O(n) space. O(nLgn) time. harder to understand than the map method.
    public static List<List<Integer>> getSkylinePQ(int[][] buildings) {
        List<List<Integer>> res = new ArrayList<>();
        // height, right_i. sort by height opposite then right_i opposite
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] == b[2] ? b[1] - a[1] : b[2] - a[2]);
        int i = 0, curX = 0, curH = 0;
        while (i < buildings.length || !pq.isEmpty()) {
            curX = pq.isEmpty() ? buildings[i][0] : pq.peek()[1]; // next right_i to process
            if (i >= buildings.length || buildings[i][0] > curX) {
                // if the current tallest building will end before curX
                // pop processed buildings: those have height no larger than curH and end before the top one
                while (!pq.isEmpty() && pq.peek()[1] <= curX) pq.poll();
            } else {
                // if the next new building starts before the top one ends, process the new building
                curX = buildings[i][0];
                // // go through all the new buildings that starts at the same point
                while (i < buildings.length && curX == buildings[i][0]) pq.offer(buildings[i++]);
            }
            curH = pq.isEmpty() ? 0 : pq.peek()[2]; // tallest height
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
