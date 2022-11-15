package princeton.jsl;

public class ThreeSum {

    /**
     * Brute force, O(N^3) time.
     * @param a int array
     * @param sum sum value to find
     * @return count of three numbers
     */
    public int countBF(int[] a, int sum) {
        int count = 0;
        for (int i = 0; i < a.length - 2; i++) {
            for (int j = i+1; j < a.length - 1; j++) { // can relax bound to length, just some loops doing nothing
                for (int k = j+1; k < a.length; k++) {
                    if (a[i] + a[j] + a[k] == sum) count++;
                }
            }
        }
        return count;
    }
}
