package util;

import java.util.Random;

@SuppressWarnings("unused")
public class IntArrayUtil {
    /**
     * Fisher Yates shuffle. https://en.wikipedia.org/wiki/Fisher–Yates_shuffle
     * @param nums int array
     */
    public static void FYShuffle(int[] nums) {
        Random r = new Random();
        for (int i = nums.length - 1; i > 0; i--) {
            int j = r.nextInt(i + 1);
            if (j != i) {
                nums[i] ^= nums[j];
                nums[j] ^= nums[i];
                nums[i] ^= nums[j];
            }
        }
    }

    /**
     * Pro/Con:
     * <p>
     * <ul>
     * <li>Can handle cases source size is unknown.
     * <li>No swapping performed.
     * <li>Extra space needed.
     * </ul>
     *
     * @param source source array to shuffle.
     * @return shuffled array.
     */
    public static int[] FYShuffleInsideOut(int[] source) {
        int[] a = new int[source.length];
        Random r = new Random();
        for (int i = 0; i < source.length; i++) {
            int j = r.nextInt(i + 1);
            if (j != i) a[i] = a[j];
            a[j] = source[i];
        }
        return a;
    }

    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static int binarySearchRange(int[] nums, int lo, int hi, int target) {
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (target == nums[mid]) return mid;
            else if (target > nums[mid]) lo = mid + 1;
            else hi = mid - 1;
        }
        return -1;
    }

    /**
     * Arrays.binarySearch or typical binary search returns -1 if not found. This version returns the position to be
     * inserted.
     *
     * @param array array to search.
     * @param target target to find.
     * @return index where the target is found or where it should be inserted. For duplicates, return earliest position.
     */
    public static int binarySearchIndexToInsert(int[] array, int target){
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

    public static Integer[] boxIntArray(int[] integers) {
        if (integers == null) return null;
        Integer[] result = new Integer[integers.length];
        for (int i = 0; i < integers.length; i++)
            result[i] = integers[i];
        return result;
    }

    public static int[] unBoxIntegerArray(Integer[] integers) {
        if (integers == null) return null;
        int[] result = new int[integers.length];
        for (int i = 0; i < integers.length; i++)
            result[i] = integers[i];
        return result;
    }
}
