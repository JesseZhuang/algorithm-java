package array;

/**
 * LeetCode 1011, medium, tags: array, binary search, companies: apple.
 * A conveyor belt has packages that must be shipped from one port to another within days.
 * <p>
 * The ith package on the conveyor belt has a weight of weights[i]. Each day, we load the ship with packages on the
 * conveyor belt (in the order given by weights). We may not load more weight than the maximum weight
 * capacity of the ship.
 * <p>
 * Return the least weight capacity of the ship that will result in all the packages on the conveyor belt being
 * shipped within days.
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
 * Constraints:
 * <p>
 * 1 <= days <= weights.length <= 5 * 10^4, n
 * 1 <= weights[i] <= 500, w
 */
@SuppressWarnings("unused")
public class CapacityShipPackages {

    // solution 1, 9ms, 45.8 Mb. O(nLgw) time, O(1) space.
    public static int shipWithinDays2(int[] weights, int days) {
        int max = 0, sum = 0;
        for (int weight : weights) {
            if (weight > max) max = weight;
            sum += weight;
        }

//        Predicate<Integer> check = (mid -> {
//            int cnt = 1, cur = 0;
//            for (int w : weights) {
//                if (cur + w > mid) {
//                    cnt++;
//                    if (cnt > days) return false;
//                    cur = 0;
//                }
//                cur += w;
//            }
//            return cur <= days;
//        });

        int l = max, r = sum, res = l;
        while (l < r) { // not <= because if l incremented to r from r-1, r-1 does not work, r must work
            int mid = l + (r - l) / 2, count = 1, cur = 0; // note count starts with 1
            for (int w : weights) {
                if (cur + w > mid) { // not >=
                    count += 1;
                    if (count > days) break;
                    cur = 0;
                }
                cur += w;
            }
            if (count > days) l = mid + 1;
            else {
                res = mid; // this works, try smaller
                r = mid - 1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(shipWithinDays2(new int[]{3, 2, 2, 4, 1, 4}, 3));
        ;
    }
}
