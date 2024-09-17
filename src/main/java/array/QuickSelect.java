package array;

import util.IntArrayUtil;

import java.util.Arrays;
import java.util.Random;

import static util.IntArrayUtil.swap;

/**
 * Time: O(N) because only need to recurse to one side. Worst case O(N^2). Worst case linear time is possible with
 * selecting pivot with median of medians. However, it is not used in practice because constant factor is high. So
 * it is actually slow, and it does not work with duplicates.
 * <p>
 * Space:
 * O(lgN) average case, worst case O(N) for recursive.
 * O(1) for iterative.
 * <p>
 * Find kth largest or smallest is counting duplicated values, so does not work to find kth distinct.
 * <p>
 * This class uses random pick to select a pivot then partition.
 */
public class QuickSelect {
    static Random r = new Random();


    /**
     * partition function similar to quick sort Considers last element as pivot and adds
     * elements with less value to the left and
     * high value to the right and also changes
     * the pivot position to its respective position
     * in the final array.
     *
     * @param a  array to partition
     * @param lo range low boundary
     * @param hi range high boundary
     * @return the pivot element's index
     */
    public static int partition(int[] a, int lo, int hi) {
        int pi = lo + r.nextInt(hi - lo + 1); // pivot's index
        swap(a, pi, hi);
        pi = lo;
        int pivot = a[hi];
        for (int i = lo; i < hi; i++) if (a[i] < pivot) swap(a, i, pi++);
        swap(a, pi, hi);
        return pi;
    }

    public static int partitionLarge(int[] a, int lo, int hi) {
        int pi = lo + r.nextInt(hi - lo + 1); //[lo,hi]
        swap(a, lo, pi);
        int pivot = a[lo];
        pi = hi;
        for (int i = hi; i > lo; i--) // to the right of the pivot location
            if (a[i] > pivot) swap(a, i, pi--);
        // swapping pivot to the final pivot location
        swap(a, lo, pi);
        return pi;
    }

    public static int kthSmallest(int[] a, int k) {
        return kthSmallest(a, 0, a.length - 1, k);
    }

    public static int kthSmallest(int[] a, int lo, int hi, int k) {
        while (lo < hi) {
            int pi = partition(a, lo, hi);
            if (pi == k - 1) break;
            else if (pi < k - 1) lo = pi + 1;
            else hi = pi - 1;
        }
        return a[k - 1];
    }

    // recursive version
    public static int kthSmallestR(int[] a, int lo, int hi, int k) {
        int partition = partition(a, lo, hi);
        // if partition value is equal to the kth position, return value at k.
        if (partition == k - 1) return a[partition];
        else if (partition < k - 1) return kthSmallest(a, partition + 1, hi, k);
        else return kthSmallest(a, lo, partition - 1, k);
    }

    public static int kthLargest(int[] array, int k) {
        return kthLargest(array, 0, array.length - 1, k);
    }

    // iterative version
    public static int kthLargest(int[] array, int lo, int hi, int k) {
        while (lo < hi) {
            int pi = partitionLarge(array, lo, hi);
            if (pi == array.length - k) break;
            else if (pi < array.length - k) lo = pi + 1;
            else hi = pi - 1;
        }
        return array[array.length - k];
    }

    // recursive version
    public static int kthLargestR(int[] array, int lo, int hi, int k) {
        int partition = partitionLarge(array, lo, hi);
        if (partition == array.length - k) return array[partition];
        else if (partition < array.length - k) return kthLargest(array, partition + 1, hi, k);
        else return kthLargest(array, lo, partition - 1, k);
    }

    public static void main(String[] args) {
        int[] array = new int[]{10, 4, 5, 8, 6, 11, 26}; // 3rd smallest 6, 3rd largest 10
        int[] arraycopy = new int[]{10, 4, 5, 8, 6, 11, 26, 4}; // 3rd smallest 5, 3rd largest 10
        int kPosition = 3;
        int length = arraycopy.length;
        if (kPosition > length) {
            System.out.println("Index out of bound");
        } else {
            IntArrayUtil.FYShuffle(arraycopy);
            System.out.println("shuffled: " + Arrays.toString(arraycopy));
            System.out.println("K-th smallest element in array : "
                    + kthSmallest(arraycopy, 0, length - 1, kPosition));
            IntArrayUtil.FYShuffle(arraycopy);
            System.out.println("shuffled: " + Arrays.toString(arraycopy));
            System.out.println("K-th largest element in array : "
                    + kthLargest(arraycopy, 0, length - 1, kPosition));
        }
    }
// adapted from geeks for geeks
}
