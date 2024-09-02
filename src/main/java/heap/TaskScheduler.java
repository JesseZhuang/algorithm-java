package heap;

/**
 * LeetCode 621, medium, tags: array, hash table, greedy, sorting, heap, counting.
 * <p>
 * You are given an array of CPU tasks, each represented by letters A to Z, and a cooling time, n. Each cycle or
 * interval allows the completion of one task. Tasks can be completed in any order, but there's a constraint:
 * identical tasks must be separated by at least n intervals due to cooling time.
 * <p>
 * ​Return the minimum number of intervals required to complete all tasks.
 * <p>
 * Example 1:
 * <p>
 * Input: tasks = ["A","A","A","B","B","B"], n = 2
 * <p>
 * Output: 8
 * <p>
 * Explanation: A possible sequence is: A -> B -> idle -> A -> B -> idle -> A -> B.
 * <p>
 * After completing task A, you must wait two cycles before doing A again. The same applies to task B. In the 3rd
 * interval, neither A nor B can be done, so you idle. By the 4th cycle, you can do A again as 2 intervals have passed.
 * <p>
 * Example 2:
 * <p>
 * Input: tasks = ["A","C","A","B","D","B"], n = 1
 * <p>
 * Output: 6
 * <p>
 * Explanation: A possible sequence is: A -> B -> C -> D -> A -> B.
 * <p>
 * With a cooling interval of 1, you can repeat a task after just one other task.
 * <p>
 * Example 3:
 * <p>
 * Input: tasks = ["A","A","A", "B","B","B"], n = 3
 * <p>
 * Output: 10
 * <p>
 * Explanation: A possible sequence is: A -> B -> idle -> idle -> A -> B -> idle -> idle -> A -> B.
 * <p>
 * There are only two types of tasks, A and B, which need to be separated by 3 intervals. This leads to idling twice
 * between repetitions of these tasks.
 * <p>
 * Constraints:
 * <p>
 * 1 <= tasks.length <= 10^4
 * tasks[i] is an uppercase English letter.
 * 0 <= n <= 100
 * <p>
 * Hint 1
 * There are many different solutions for this problem, including a greedy algorithm.
 * Hint 2
 * For every cycle, find the most frequent letter that can be placed in this cycle. After placing,
 * decrease the frequency of that letter by one.
 */
public class TaskScheduler {
    //https://leetcode.com/problems/task-scheduler/solutions/104500/java-o-n-time-o-1-space-1-pass-no-sorting-solution-with-detailed-explanation/comments/290139
    // solution 1, O(n) time, O(1) space. 4ms, 45.96Mb.
    // solution 2, see LeetCode 767, heap, O(n+kLgk) time, O(k) space. k<=26.
    public int leastInterval(char[] tasks, int n) {
        int[] count = new int[26];
        int max = 0, maxCnt = 0; // max frequency job count, how many jobs have the same max count
        for (char task : tasks) {
            count[task - 'A']++;
            if (count[task - 'A'] > max) {
                max = count[task - 'A'];
                maxCnt = 1;
            } else if (count[task - 'A'] == max) maxCnt++;
        }
        // AB?AB?AB n=2; gapL==3 : AB? ; nGaps==2 (gaps between the AB); maxCnt==2 (A and B both have max count of 2)
        int nGaps = max - 1, gapL = n + 1;
        return Math.max(tasks.length, nGaps * gapL + maxCnt);
    }
}
