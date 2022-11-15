package princeton.jsl;

import java.util.Arrays;

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

    public int count(int[] a, int sum) {
        int count = 0;
        Arrays.sort(a);
        for (int i = 0; i < a.length; i++) {
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
}
