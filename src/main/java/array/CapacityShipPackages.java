package array;

/**
 * LeetCode 1011, medium, tags: array, binary search.
 * A conveyor belt has packages that must be shipped from one port to another within days.
 * <p>
 * The ith package on the conveyor belt has a weight of weights[i]. Each day, we load the ship with packages on the
 * conveyor belt (in the order given by weights). We may not load more weight than the maximum weight
 * capacity of the ship.
 * <p>
 * Return the least weight capacity of the ship that will result in all the packages on the conveyor belt being
 * shipped within days.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: weights = [1,2,3,4,5,6,7,8,9,10], days = 5
 * Output: 15
 * Explanation: A ship capacity of 15 is the minimum to ship all the packages in 5 days like this:
 * 1st day: 1, 2, 3, 4, 5
 * 2nd day: 6, 7
 * 3rd day: 8
 * 4th day: 9
 * 5th day: 10
 * <p>
 * Note that the cargo must be shipped in the order given, so using a ship of capacity 14 and splitting the packages
 * into parts like (2, 3, 4, 5), (1, 6, 7), (8), (9), (10) is not allowed.
 * Example 2:
 * <p>
 * Input: weights = [3,2,2,4,1,4], days = 3
 * Output: 6
 * Explanation: A ship capacity of 6 is the minimum to ship all the packages in 3 days like this:
 * 1st day: 3, 2
 * 2nd day: 2, 4
 * 3rd day: 1, 4
 * Example 3:
 * <p>
 * Input: weights = [1,2,3,1,1], days = 4
 * Output: 3
 * Explanation:
 * 1st day: 1
 * 2nd day: 2
 * 3rd day: 3
 * 4th day: 1, 1
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= days <= weights.length <= 5 * 104
 * 1 <= weights[i] <= 500
 */
public class CapacityShipPackages {

    // 9ms, 45.8 Mb.
    public int shipWithinDays2(int[] weights, int days) {
        int max = 0, sum = 0;
        for (int weight : weights) {
            if (weight > max) max = weight;
            sum += weight;
        }
        while (max < sum) {
            int mid = max + (sum - max) / 2, need = 1, cur = 0;
            for (int w : weights) {
                if (cur + w > mid) {
                    need += 1;
                    cur = 0;
                }
                cur += w;
            }
            if (need > days) max = mid + 1;
            else sum = mid;
        }
        return max;
    }

    // O(nLgn) time, O(1) space. 10ms, 45.7Mb.
    public int shipWithinDays(int[] weights, int days) { // ask for constraints, whether days can >= length
        int max = 0, sum = 0; // no need min, max should be minimum
        for (int weight : weights) {
            if (weight > max) max = weight;
            sum += weight;
        }
        while (max < sum) { // can stop when left == right
            int mid = max + (sum - max) / 2;
            if (canMove(weights, days, mid)) sum = mid;
            else max = mid + 1;
        }
        return max;
    }

    private boolean canMove(int[] weights, int days, int capacity) {
        int count = 0, cWeight = 0;
        for (int i = 0; i < weights.length; ) {
            while (i < weights.length && weights[i] + cWeight <= capacity) {
                cWeight += weights[i];
                i++;
            }
            cWeight = 0;
            count++;
        }
        return count <= days;
    }

}
