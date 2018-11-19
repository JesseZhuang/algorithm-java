package util;

import java.util.Random;

@SuppressWarnings("unused")
public class IntUtil {
    /**
     * Fisher Yates shuffle.
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
}
