package princeton.jsl;

public class Merge {

    // This class should not be instantiated.
    private Merge() { }

    // stably merge a[lo .. mid] with a[mid+1 ..hi] using aux[lo .. hi]
    private static void merge(Comparable[] a, Comparable[] helper, int lo, int mid, int hi) {
        assert isSorted(a, lo, mid);
        assert isSorted(a, mid+1, hi);

        for (int k = lo; k <= hi; k++) helper[k] = a[k];
        int i = lo, j = mid + 1, k = lo; // 3 pointers
        while (k <= hi) {
            if (i > mid) a[k++] = helper[j++]; // i depleted, take from j
            else if (j > hi) a[k++] = helper[i++];
            else if (less(a[i], a[j])) a[k++] = helper[i++];
            else a[k++] = helper[j++];
        }

        assert isSorted(a, lo, hi);
    }

    // mergesort a[lo..hi] using auxiliary array aux[lo..hi]
    private static void sort(Comparable[] a, Comparable[] helper, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, helper, lo, mid);
        sort(a, helper, mid + 1, hi); // note mid + 1, not mid
        merge(a, helper, lo, mid, hi);
    }

    /**
     * Rearranges the array in ascending order, using the natural order.
     * @param a the array to be sorted
     */
    public static void sort(Comparable[] a) {
        Comparable[] helper = new Comparable[a.length];
        sort(a, helper, 0, a.length-1);
        assert isSorted(a);
    }


    /***************************************************************************
     *  Helper sorting function.
     ***************************************************************************/

    // is v < w ?
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    /***************************************************************************
     *  Check if array is sorted - useful for debugging.
     ***************************************************************************/
    private static boolean isSorted(Comparable[] a) {
        return isSorted(a, 0, a.length - 1);
    }

    private static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }


    /***************************************************************************
     *  Index mergesort.
     ***************************************************************************/
    // stably merge a[lo .. mid] with a[mid+1 .. hi] using aux[lo .. hi]
    private static void mergeIndex(Comparable[] a, int[] index, int[] aux, int lo, int mid, int hi) {

        // copy to aux[]
        for (int k = lo; k <= hi; k++) {
            aux[k] = index[k];
        }

        // merge back to a[]
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            if      (i > mid)                    index[k] = aux[j++];
            else if (j > hi)                     index[k] = aux[i++];
            else if (less(a[aux[j]], a[aux[i]])) index[k] = aux[j++];
            else                                 index[k] = aux[i++];
        }
    }

    /**
     * Returns a permutation that gives the elements in the array in ascending order.
     * @param a the array
     * @return a permutation {@code p[]} such that {@code a[p[0]]}, {@code a[p[1]]},
     *    ..., {@code a[p[N-1]]} are in ascending order
     */
    public static int[] indexSort(Comparable[] a) {
        int n = a.length;
        int[] index = new int[n];
        for (int i = 0; i < n; i++)
            index[i] = i;

        int[] aux = new int[n];
        sortIndex(a, index, aux, 0, n-1);
        return index;
    }

    // mergesort a[lo..hi] using auxiliary array aux[lo..hi]
    private static void sortIndex(Comparable[] a, int[] index, int[] aux, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sortIndex(a, index, aux, lo, mid);
        sortIndex(a, index, aux, mid + 1, hi);
        mergeIndex(a, index, aux, lo, mid, hi);
    }

    // print array to standard output
    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.printf(i + ": ");
            System.out.println(a[i]);
        }
    }

    /**
     * Reads in a sequence of strings from standard input; mergesorts them;
     * and prints them to standard output in ascending order.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        String[] a = new String[] {"a", "a1", "", "a2"};
        Merge.sort(a);
        show(a);
    }
}
