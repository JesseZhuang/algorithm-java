package util;

import java.util.Random;

@SuppressWarnings("unused")
public class IntArrayUtil {
    /**
     * Fisher Yates shuffle. https://en.wikipedia.org/wiki/Fisher–Yates_shuffle.
     * Start i from high end, random j in [0, i].
     * Key for random generation, j range must be i inclusive: [0, i] or [i, n-1] not [0, i-1] or [i+1, n-1].
     *
     * @param nums int array
     */
    public static void FYShuffle(int[] nums) {
        Random r = new Random();
        for (int i = nums.length - 1; i > 0; i--) {
            int j = r.nextInt(i + 1);// [0, i] or [0, i+1)
            if (j != i) {
                nums[i] ^= nums[j];
                nums[j] ^= nums[i];
                nums[i] ^= nums[j];
            }
        }
    }

    /**
     * Fisher Yates shuffle alternate. Start i from low end 1..n-1, random j in [0, i].
     *
     * @param nums int array
     */
    public static void FYShuffle2(int[] nums) {
        Random r = new Random();
        for (int i = 1; i <= nums.length - 1; i++) {
            int j = r.nextInt(i + 1);// [0, i] or [0, i+1)
            if (j != i) swap(nums, i, j);
        }
    }

    /**
     * Fisher Yates shuffle alternate. Start i from low end 0..n-2, random j in [i, n-1].
     *
     * @param nums int array
     */
    public static void FYShuffle3(int[] nums) {
        Random r = new Random();
        for (int i = 0; i < nums.length - 1; i++) {
            int j = i + r.nextInt(nums.length - i);// [i, n-1]
            if (j != i) swap(nums, i, j);
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
            int j = r.nextInt(i + 1); //[0, i]
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
