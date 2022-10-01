package array;

import java.util.Arrays;

/**
 * <p>Amazon Delivery Centers dispatch parcels every day. There are n delivery centers, each having parcels[i] parcels
 * to be delivered. On each day, an equal number of parcels are to be dispatched from each delivery center that has
 * at least one parcel remaining.
 *
 * <p>Find the minimum number of days needed to deliver all the parcels.
 *
 * <p>Example: parcels = [2, 3, 4, 3, 3]
 * <ul>
 *     <ui>Day 1: 2 parcels are delivered from each center</ui>
 *     <ui>Day 2: 1 parcel is delivered from the remaining centers</ui>
 *     <ui>Day 3: 1 parcel is delivered from the remaining center</ui>
 *     <ui>All parcels can be delivered in a minimum of 3 days.</ui>
 * </ul>
 * <p>parcels = [4, 2, 3, 4] answer: 3
 * <p>parcels = [3, 3, 3, 3, 3, 3] answer: 1
 */
public class UniqueDeliveries {
    /*
     * Complete the 'getMinimumDays' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts INTEGER_ARRAY parcels as parameter.
     */
    // answer 1
    int getMinimumDays1(int[] parcels) {
        // Just count the number of unique numbers not taking into account zeroes
        // O(nlog(n)). Could be solved in O(n) using O(n) space if a hash_map is used
        Arrays.sort(parcels);
        System.out.println(Arrays.toString(parcels));

        int idx = 0; // find first id non zero.
        while (idx < parcels.length && parcels[idx] == 0)
            idx += 1;

        if (idx == parcels.length) // All the delivery centers are empty
            return 0;

        int prev = parcels[idx];
        int unique_count = 1;

        for (int i = idx + 1; i < parcels.length; i++) {
            System.out.println(String.format("i: %d, parcels[%d]: %d, prev: %d.", i, i, parcels[i], prev));
            if (prev != parcels[i]) {
                unique_count += 1;
                prev = parcels[i];
                System.out.println(String.format("new unique value: %d, index: %d, unique_count: %d.",
                        prev, i, unique_count));
            }
        }
        return unique_count;
    }

    public static void main(String[] args) {
        UniqueDeliveries tbt = new UniqueDeliveries();
        tbt.getMinimumDays1(new int[]{4, 2, 3, 4, 2, 3, 4}); // unique count 3, correct

    }

}
