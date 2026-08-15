package binary_search;

/**
 * LeetCode 1482, medium, tags: array, binary search.
 * <p>
 * Given an integer array bloomDay, an integer m and an integer k, return the minimum number of days you need to wait
 * to be able to make m bouquets from the garden. Each bouquet requires k adjacent flowers. Return -1 if impossible.
 * <p>
 * Example 1:
 * Input: bloomDay = [1,10,3,10,2], m = 3, k = 1
 * Output: 3
 * <p>
 * Example 2:
 * Input: bloomDay = [1,10,3,10,2], m = 3, k = 2
 * Output: -1
 * <p>
 * Example 3:
 * Input: bloomDay = [7,7,7,7,12,7,7], m = 2, k = 3
 * Output: 12
 * <p>
 * Constraints:
 * 1 <= bloomDay.length <= 10^5
 * 1 <= bloomDay[i] <= 10^9
 * 1 <= m <= 10^6
 * 1 <= k <= bloomDay.length
 */
public final class MinDaysBouquets {

    private MinDaysBouquets() {
    }

    /**
     * Binary Search on Answer + Greedy. O(n * log(max_day)) time, O(1) space.
     */
    public static int minDays(int[] bloomDay, int m, int k) {
        int n = bloomDay.length;
        if ((long) m * k > n) return -1; // impossible
        int lo = 1, hi = 0;
        for (int d : bloomDay) hi = Math.max(hi, d);
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (canMake(bloomDay, mid, m, k)) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }

    /**
     * Greedy: count bouquets achievable by day. O(n) time.
     */
    private static boolean canMake(int[] bloomDay, int day, int m, int k) {
        int bouquets = 0, consecutive = 0;
        for (int d : bloomDay) {
            if (d <= day) {
                consecutive++;
                if (consecutive == k) {
                    bouquets++;
                    consecutive = 0;
                }
            } else {
                consecutive = 0;
            }
        }
        return bouquets >= m;
    }
}
