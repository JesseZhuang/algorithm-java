package heap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * LeetCode 759, LintCode 850, hard, tags: array, priority queue, sorting.
 * Companies: pinterest.
 * <p>
 * We are given a list schedule of employees, which represents the working time for each employee.
 * <p>
 * Each employee has a list of non-overlapping Intervals, and these intervals are in sorted order.
 * <p>
 * Return the list of finite intervals representing common, positive-length free time for all employees,
 * also in sorted order.
 * <p>
 * (Even though we are representing Intervals in the form [x, y], the objects inside are Intervals,
 * not lists or arrays. For example, schedule[0][0].start = 1, schedule[0][0].end = 2, and schedule[0][0][0] is not defined). Also, we wouldn’t include intervals like [5, 5] in our answer, as they have zero length.
 * <p>
 * Example 1:
 * <p>
 * Input: schedule = [[[1,2],[5,6]],[[1,3]],[[4,10]]]
 * <p>
 * Output: [[3,4]]
 * <p>
 * Explanation: There are a total of three employees, and all common free time intervals would be
 * [-inf, 1], [3, 4], [10, inf].
 * <p>
 * We discard any intervals that contain inf as they aren’t finite.
 * <p>
 * Example 2:
 * <p>
 * Input: schedule = [[[1,3],[6,7]],[[2,4]],[[2,5],[9,12]]]
 * <p>
 * Output: [[5,6],[7,9]]
 * <p>
 * Constraints:
 * <p>
 * 1 <= schedule.length, schedule[i].length <= 50
 * 0 <= schedule[i].start < schedule[i].end <= 10^8
 */
@SuppressWarnings("unused")
public class EmployeeFreeTime {

    public static void main(String[] args) {
        int[][] test = new int[][]{{1, 2, 5, 6}, {1, 3}, {4, 10}};
        Interval[] ia = {new Interval(1, 2), new Interval(5, 6),
                new Interval(1, 3), new Interval(4, 10)};
        List<List<Interval>> t1 = new ArrayList<>();
        for (Interval i : ia) {
            List<Interval> l = new ArrayList<>();
            l.add(i);
            t1.add(l);
        }
        System.out.println(new EmployeeFreeTime.Solution2().employeeFreeTime(t1));
    }

    // lint code 434ms, 23.84Mb.
    static class Solution {
        public List<Interval> employeeFreeTimeLint(int[][] schedule) {
            List<int[]> intervals = new ArrayList<>();
            for (int[] s : schedule)
                for (int i = 0; i < s.length; i += 2)
                    intervals.add(new int[]{s[i], s[i + 1]});
            intervals.sort(Comparator.comparingInt(i -> i[0]));

            int end = intervals.getFirst()[1];
            List<Interval> res = new ArrayList<>();
            for (int i = 1; i < intervals.size(); i++) {
                if (intervals.get(i)[0] > end)
                    res.add(new Interval(end, intervals.get(i)[0]));
                end = Math.max(end, intervals.get(i)[1]);
            }
            return res;
        }
    }

    // sort, nlgn time and n space. alternatively can use heap.
    static class Solution2 {
        public List<Interval> employeeFreeTime(List<List<Interval>> avails) {
            List<Interval> res = new ArrayList<>();
            List<Interval> timeLine = new ArrayList<>();
            avails.forEach(timeLine::addAll);
            timeLine.sort((Comparator.comparingInt(a -> a.start))); // sort by start

            int end = timeLine.getFirst().end;
            for (int i = 1; i < timeLine.size(); i++) {
                Interval cur = timeLine.get(i);
                if (cur.start > end) res.add(new Interval(end, cur.start));
                end = Math.max(end, cur.end);
            }
            return res;
        }
    }

    public static class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }


        @Override
        public String toString() {
            return "Interval{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }

}
