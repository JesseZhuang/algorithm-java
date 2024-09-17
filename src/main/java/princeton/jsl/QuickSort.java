package princeton.jsl;

import util.IntArrayUtil;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static util.IntArrayUtil.isSorted;
import static util.IntArrayUtil.swap;

@SuppressWarnings("unused")
public class QuickSort {
    public static void sort(Integer[] array) {
        List<Integer> list = Arrays.asList(array);
        Collections.shuffle(list); // this shuffles the underlying array as well
        sort(array, 0, array.length - 1);
        // otherwise need to copy back one by one, assign array = shuffled does not work
        // Integer[] shuffled = list.toArray(new Integer[0]);
        // sort(shuffled, 0, array.length - 1);
        // for (int i = 0; i < array.length; i++) array[i] = shuffled[i];
    }

    private static void sort(Integer[] array, int lo, int hi) {
        if (lo >= hi) return;
        int j = partition(array, lo, hi);
        sort(array, lo, j - 1);
        sort(array, j + 1, hi);
    }

    /**
     * quick sort 2-way partition. note i,j starting points and increment before evaluation in while loops
     *
     * @param array the array to partition
     * @param lo    left bound, inclusive
     * @param hi    right bound, inclusive
     * @return the index of the pivot
     */
    public static int partition(Integer[] array, int lo, int hi) {
        int i = lo, j = hi + 1;
        Integer pivot = array[i];
        while (true) {
            while (++i < hi && array[i] < pivot) ;
            while (--j > lo && array[j] > pivot) ;
            if (i < j) swap(array, i, j);
            else break;
        }
        swap(array, lo, j);
        return j;
    }

    public static int partition3Way(int[] nums, int lo, int hi) {
        int pivot = nums[lo], lt = lo, gt = hi, i = lo + 1;
        while (i <= gt) {
            if (nums[i] < pivot) swap(nums, i++, lt++);
            else if (nums[i] > pivot) swap(nums, i, gt--);
            else i++;
        }
        return lt;
    }

    public static void main(String[] args) {
        Integer[] a = new Integer[]{2, 1, 3, 1, 2, 2, 2, 2, 2};
        QuickSort.sort(a);
        for (int i = 0; i < 100; i++) {
            a = IntArrayUtil.generateRandomArray(20, 10);
            QuickSort.sort(a);
            if (!isSorted(a, 0, 20 - 1)) System.out.println(Arrays.toString(a));
        }
    }
}
