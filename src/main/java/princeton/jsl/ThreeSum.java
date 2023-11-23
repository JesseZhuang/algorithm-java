package princeton.jsl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * If duplicates are present, count all possible combinations of the array indexes.
 * Worst case example, target 0, all array elements are 0. All possibilities are N choose 3.
 * N * (N-1) * (N-2) / (1 * 2 * 3) which is O(N^3). C(N, 3) = N! / (3! * (N-3)!).
 */
public class ThreeSum {

    /**
     * Brute force, O(N^3) time.
     *
     * @param a   int array
     * @param sum sum value to find
     * @return count of three numbers
     */
    public int countBF(int[] a, int sum) {
        int count = 0;
        for (int i = 0; i < a.length - 2; i++) {
            for (int j = i + 1; j < a.length - 1; j++) { // can relax bound to length, just some loops doing nothing
                for (int k = j + 1; k < a.length; k++) {
                    if (a[i] + a[j] + a[k] == sum) count++;
                }
            }
        }
        return count;
    }

    /**
     * O(N^2 LgN) solution with binary search.
     */
    public int count(int[] a, int sum) {
        int count = 0;
        Arrays.sort(a); // lgn
        for (int i = 0; i < a.length; i++) { // n^2
            for (int j = i + 1; j < a.length - 1; j++) {
                // int k = Arrays.binarySearch(a, sum - a[i] - a[j]); // this cannot have duplicate
                int k = BinarySearch.indexOf(a, sum - a[i] - a[j], j + 1, a.length - 1);
                // handle duplicate
                while (k > j + 1 && a[k] == a[k - 1]) k--;
                int k1 = k;
                while (k < a.length - 1 && a[k] == a[k + 1]) k++;
                count = count + k - k1 + 1;
            }
        }
        return count;
    }

    /**
     * O(N^2 lgN) solution with hash map. Handle duplicates.
     */
    public int countWithMap(int[] a, int sum) {
        int count = 0;
        // Arrays.sort(a);
        Map<Integer, RedBlackBST<Integer, Integer>> valueToIndex = new HashMap<>();
        for (int i = 0; i < a.length; i++) {// O(N)
            valueToIndex.putIfAbsent(a[i], new RedBlackBST<>()); // can use jsl TreeSet
            valueToIndex.get(a[i]).put(i, 0);
        }
        List<List<Integer>> possibilities = new ArrayList<>();
        for (int i = 0; i < a.length; i++) {// O(N^2)
            for (int j = i + 1; j < a.length; j++) {
                int toFind = sum - a[i] - a[j];
                if (valueToIndex.containsKey(toFind)) {
                    RedBlackBST<Integer, Integer> found = valueToIndex.get(toFind);
                    if (!found.isEmpty() && found.max() > j) { // O(lgN), N size of tree
                        possibilities.add(new ArrayList<>());
                        for (int index : found.keys(j + 1, found.max())) {
                            possibilities.get(possibilities.size() - 1).addAll(Arrays.asList(i, j, index));
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }
}
