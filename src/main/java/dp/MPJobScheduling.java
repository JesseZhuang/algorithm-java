package dp;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;

/**
 * LeetCode 1235, hard, tags: array, binary search, dp, sorting.
 * <p>
 * We have n jobs, where every job is scheduled to be done from startTime[i] to endTime[i], obtaining a profit of profit[i].
 * <p>
 * You're given the startTime, endTime and profit arrays, return the maximum profit you can take such that there are no two jobs in the subset with overlapping time range.
 * <p>
 * If you choose a job that ends at time X you will be able to start another job that starts at time X.
 * <p>
 * <p>
 * Example 1: https://assets.leetcode.com/uploads/2019/10/10/sample1_1584.png
 * <p>
 * Input: startTime = [1,2,3,3], endTime = [3,4,5,6], profit = [50,10,40,70]
 * Output: 120
 * Explanation: The subset chosen is the first and fourth job.
 * Time range [1-3]+[3-6] , we get profit of 120 = 50 + 70.
 * Example 2: https://assets.leetcode.com/uploads/2019/10/10/sample22_1584.png
 * <p>
 * Input: startTime = [1,2,3,4,6], endTime = [3,5,10,6,9], profit = [20,20,100,70,60]
 * Output: 150
 * Explanation: The subset chosen is the first, fourth and fifth job.
 * Profit obtained 150 = 20 + 70 + 60.
 * Example 3: https://assets.leetcode.com/uploads/2019/10/10/sample3_1584.png
 * <p>
 * Input: startTime = [1,1,1], endTime = [2,3,4], profit = [5,6,4]
 * Output: 6
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= startTime.length == endTime.length == profit.length <= 5 * 10^4
 * 1 <= startTime[i] < endTime[i] <= 10^9
 * 1 <= profit[i] <= 10^4
 * <p>
 * Hint 1
 * Think on DP.
 * Hint 2
 * Sort the elements by starting time, then define the dp[i] as the maximum profit taking elements from the suffix starting at i.
 * Hint 3
 * Use binarySearch (lower_bound/upper_bound on C++) to get the next index for the DP transition.
 */
public class MPJobScheduling {
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int len = startTime.length;
        int[][] dp = new int[len][3];
        for (int i = 0; i < len; i++)
            dp[i] = new int[]{startTime[i], endTime[i], profit[i]};

        Arrays.sort(dp, Comparator.comparingInt(a -> a[1])); // sort by end time
        TreeMap<Integer, Integer> intsMap = new TreeMap<>(); // endTime, total profit
        intsMap.put(0, 0); // dummy job end at 0, profit 0, important
        for (int[] i : dp) {
            int cur = intsMap.floorEntry(i[0]).getValue() + i[2]; // max <= i[0]
            if (cur > intsMap.lastEntry().getValue()) // max key
                intsMap.put(i[1], cur); // put (endTime, profit)
        }
        return intsMap.lastEntry().getValue();
    }
}
