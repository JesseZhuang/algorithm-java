package princeton.jsl;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 * The {@code BinarySearch} class provides a static method for binary
 * searching for an integer in a sorted array of integers.
 * <p>
 * The <em>indexOf</em> operations takes logarithmic time in the worst case.
 * <p>
 * For additional documentation, see <a href="https://algs4.cs.princeton.edu/11model">Section 1.1</a> of
 * <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */
public class BinarySearch {

    /**
     * This class should not be instantiated.
     */
    private BinarySearch() {
    }

    /**
     * Arrays.binarySearch or typical binary search returns -1 if not found. This version returns the position to be
     * inserted.
     *
     * @param array  array to search.
     * @param target target to find.
     * @return index where the target is found or where it should be inserted. For duplicates, return earliest position.
     */
    public static int binarySearchIndexToInsert(int[] array, int target) {
        int length = array.length;
        int lo = 0, hi = length - 1;
        while (lo <= hi) {
            int middle = lo + (hi - lo) / 2;
            if (target > array[middle]) lo = middle + 1;
            else if (target == array[middle]) {
                while (middle - 1 >= lo && target == array[middle - 1]) middle--;
                return middle;
            } else hi = middle - 1;
        }
        return lo;//hi < lo, array[lo] > target > array[lo - 1], so return lo. example find 2 in [1,2], hi=0, lo=1
    }

    /**
     * bisect left, return index of the leftmost duplicate if exists otherwise the index to insert to keep
     * the array sorted.
     * <p>
     * Same complexity as {@link BinarySearch#binarySearchIndexToInsert}.
     *
     * @param array,  input array.
     * @param target, target to find.
     * @return index found.
     */
    public static int bisectLeft(int[] array, int target) {
        int lo = 0;
        int hi = array.length - 1;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (array[mid] >= target) hi = mid;
            else lo = mid + 1;
        }
        return lo;
    }

    /**
     * Returns the index of the specified key in the specified array.
     *
     * @param a   the array of integers, must be sorted in ascending order
     * @param key the search key
     * @return index of key in array {@code a} if present; {@code -1} otherwise
     */
    public static int indexOf(int[] a, int key) {
        return indexOf(a, key, 0, a.length - 1);
    }

    public static int indexOf(int[] a, int key, int lo, int hi) {
        if (lo < 0 || hi > a.length - 1) throw new IllegalArgumentException("range is not valid");
        while (lo <= hi) {
            // Key is in a[lo..hi] or not present.
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid]) hi = mid - 1;
            else if (key > a[mid]) lo = mid + 1;
            else return mid;
        }
        return -1;
    }

    /**
     * Returns the index of the specified key in the specified array.
     * This function is poorly named because it does not give the <em>rank</em>
     * if the array has duplicate keys or if the key is not in the array.
     *
     * @param key the search key
     * @param a   the array of integers, must be sorted in ascending order
     * @return index of key in array {@code a} if present; {@code -1} otherwise
     * @deprecated Replaced by {@link #indexOf(int[], int)}.
     */
    @Deprecated
    public static int rank(int key, int[] a) {
        return indexOf(a, key);
    }

    /**
     * Reads in a sequence of integers from the whitelist file, specified as
     * a command-line argument; reads in integers from standard input;
     * prints to standard output those integers that do <em>not</em> appear in the file.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {

        // read the integers from a file
        In in = new In(args[0]);
        int[] whitelist = in.readAllInts();

        // sort the array
        Arrays.sort(whitelist);

        // read integer key from standard input; print if not in whitelist
        while (!StdIn.isEmpty()) {
            int key = StdIn.readInt();
            if (BinarySearch.indexOf(whitelist, key) == -1)
                StdOut.println(key);
        }
    }
}
