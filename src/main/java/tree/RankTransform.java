package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * LeetCode 1331, easy, tags: array, hash table, sorting.
 * <p>
 * Given an array of integers arr, replace each element with its rank.
 * <p>
 * The rank represents how large the element is. The rank has the following rules:
 * <p>
 * Rank is an integer starting from 1.
 * The larger the element, the larger the rank. If two elements are equal, their rank must be the same.
 * Rank should be as small as possible.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: arr = [40,10,20,30]
 * Output: [4,1,2,3]
 * Explanation: 40 is the largest element. 10 is the smallest. 20 is the second smallest. 30 is the third smallest.
 * Example 2:
 * <p>
 * Input: arr = [100,100,100]
 * Output: [1,1,1]
 * Explanation: Same elements share the same rank.
 * Example 3:
 * <p>
 * Input: arr = [37,12,28,9,100,56,80,5,12]
 * Output: [5,3,4,2,8,6,7,1,3]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= arr.length <= 10^5
 * -10^9 <= arr[i] <= 10^9
 * <p>
 * Hint 1
 * Use a temporary array to copy the array and sort it.
 * Hint 2
 * The rank of each element is the number of unique elements smaller than it in the sorted array plus one.
 */
@SuppressWarnings("unused")
public class RankTransform {

    // treemap solution, nlgn, n. 69ms, 75.4mb.
    // other solutions: sorting+hashmap (n+sorting space), hashmap+treeset.
    static class Solution {
        public int[] arrayRankTransform(int[] arr) {
            // value: [indices]
            TreeMap<Integer, List<Integer>> valIndices = new TreeMap<>();
            for (int i = 0; i < arr.length; i++)
                valIndices.computeIfAbsent(arr[i], k -> new ArrayList<>()).add(i);
            int rank = 1;
            for (int num : valIndices.keySet()) {
                for (int i : valIndices.get(num)) arr[i] = rank;
                rank++;
            }
            return arr;
        }
    }

}
