package array;

import java.util.Arrays;
import java.util.Comparator;

/**
 * LeetCode 2070, medium, tags: array, binary search, soring.
 */
@SuppressWarnings("unused")
public class BeautifulItemQuery {
    // todo editorial, mlgm+nlgn, sorting(m+n)
    static class Solution {
        public int[] maximumBeauty(int[][] items, int[] queries) {
            int[] res = new int[queries.length];
            // sort both items and queries in ascending order
            Arrays.sort(items, Comparator.comparingInt(a -> a[0]));
            int[][] queriesWithIndices = new int[queries.length][2];
            for (int i = 0; i < queries.length; i++) {
                queriesWithIndices[i][0] = queries[i];
                queriesWithIndices[i][1] = i;
            }

            Arrays.sort(queriesWithIndices, Comparator.comparingInt(a -> a[0]));

            int itemIndex = 0;
            int maxBeauty = 0;

            for (int i = 0; i < queries.length; i++) {
                int query = queriesWithIndices[i][0];
                int originalIndex = queriesWithIndices[i][1];

                while (itemIndex < items.length && items[itemIndex][0] <= query) {
                    maxBeauty = Math.max(maxBeauty, items[itemIndex][1]);
                    itemIndex++;
                }

                res[originalIndex] = maxBeauty;
            }

            return res;
        }
    }
}
