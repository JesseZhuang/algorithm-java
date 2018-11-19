package array;

import java.util.Arrays;

/**
 * Amazon, WayFair. Easy.
 * <p>
 * An array contains both positive and negative numbers in
 * random order. Rearrange the array elements so that positive and negative
 * numbers are placed alternatively. Number of positive and negative numbers
 * need not be equal. If there are more positive numbers they appear at the end
 * of the array. If there are more negative numbers, they too appear in the end
 * of the array.
 * <p>
 * If the input array is [-1, 2, -3, 4, 5, 6, -7, 8, 9], then the output should
 * be [9, -7, 8, -3, 5, -1, 2, 4, 6] (positive before negative question).
 * <p>
 * Note this question always start with positive numbers first.
 * <p>
 * <b>Summary:</b>
 * <p>
 * <ul>
 * <li><b>use modulus, O(n), O(1) space, might overflow.</b>
 * <li>rotate instead of swapping, non linear O(n^2), O(1) space.
 * <li>O(1) space, non stable methods with two pointers or quick sort partition.
 * Refer to version 1 of this program.
 * </ul>
 */
public class InterleavePosNeg2 {

    public void rearrangeModulus(int[] A) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        int negCount = 0;
        for (int aA : A) {
            if (aA > max) max = aA;
            if (aA < min) min = aA;
            if (aA < 0) negCount++;

        }
        int posCount = A.length - negCount;
        if (posCount == 0 || negCount == 0) return;
        // Change all values to Positive
        for (int i = 0; i < A.length; i++)
            A[i] -= min;

        int range = max - min + 1;

        // Save positive values into new positions
        int currNegativeIndex = 1, currPositiveIndex = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] % range > (-min)) {
                A[currPositiveIndex] += (A[i] % range) * range;
                if (--negCount <= -1) currPositiveIndex++;
                else currPositiveIndex += 2;
            } else {
                A[currNegativeIndex] += (A[i] % range) * range;
                if (--posCount <= 0) currNegativeIndex++;
                else currNegativeIndex += 2;
            }
        }
        // Recover to original value
        for (int i = 0; i < A.length; i++) {
            A[i] = A[i] / range + min;
        }
    }

    public void rearrangeStableInPlace(int[] nums) {
        if (nums == null) return;
        int i = 0, j;
        while (i < nums.length) {
            if (i % 2 == 0) {
                if (nums[i++] > 0) continue;
                j = i;
                while (j < nums.length && nums[j] < 0)
                    j++;
                if (j >= nums.length) break;
                else rotateRight(nums, i++ - 1, j);
            } else {
                if (nums[i++] < 0) continue;
                j = i;
                while (j < nums.length && nums[j] > 0)
                    j++;
                if (j >= nums.length) break;
                else rotateRight(nums, i++ - 1, j);
            }
        }
    }

    private void rotateRight(int[] nums, int start, int end) {
        int temp = nums[end];
        for (; start < end; end--)
            nums[end] = nums[end - 1];
        nums[start] = temp;
    }

    public static void main(String[] args) {
        InterleavePosNeg2 i = new InterleavePosNeg2();
        int[][] inputs = { { -1, 2, -3, 4, 5, 6, -7, 8, 9 },
                { -1, -2, -4, -6, 8, 3, 5, -9 } };
        for (int[] A : inputs) {
            // i.rearrangeStableInPlace(A);
            // i.rotateRight(A, 0, 6);
            // i.rearrangeStableInPlace(A);
            i.rearrangeModulus(A);
            System.out.println(Arrays.toString(A));
        }
    }
}
