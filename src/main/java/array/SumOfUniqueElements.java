package array;

import java.util.Arrays;

public final class SumOfUniqueElements {
    private SumOfUniqueElements() {
    }

    /**
     * Counts values in the problem's range [1, 100].
     * Time: O(n + U), where U is 100; space: O(U).
     */
    public static int sumOfUnique(int[] nums) {
        int[] frequencies = new int[101];
        for (int num : nums) {
            frequencies[num]++;
        }

        int sum = 0;
        for (int value = 1; value < frequencies.length; value++) {
            if (frequencies[value] == 1) {
                sum += value;
            }
        }
        return sum;
    }

    /**
     * Sorts a copy and adds values whose group has exactly one element.
     * Time: O(n log n); space: O(n).
     */
    public static int sumOfUniqueBySorting(int[] nums) {
        int[] sorted = Arrays.copyOf(nums, nums.length);
        Arrays.sort(sorted);

        int sum = 0;
        for (int index = 0; index < sorted.length; ) {
            int end = index + 1;
            while (end < sorted.length && sorted[end] == sorted[index]) {
                end++;
            }
            if (end - index == 1) {
                sum += sorted[index];
            }
            index = end;
        }
        return sum;
    }
}
