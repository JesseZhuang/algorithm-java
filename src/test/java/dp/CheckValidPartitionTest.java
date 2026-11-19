package dp;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CheckValidPartitionTest {
    @Test
    void officialExamples() {
        assertTrue(CheckValidPartition.validPartition(new int[] {4, 4, 4, 5, 6}));
        assertFalse(CheckValidPartition.validPartition(new int[] {1, 1, 1, 2}));
    }

    @Test
    void twoElementsMustBeEqual() {
        assertTrue(CheckValidPartition.validPartition(new int[] {1, 1}));
        assertFalse(CheckValidPartition.validPartition(new int[] {1, 2}));
    }

    @Test
    void threeElementsMustBeEqualOrIncreasingByOne() {
        assertTrue(CheckValidPartition.validPartition(new int[] {7, 7, 7}));
        assertTrue(CheckValidPartition.validPartition(new int[] {1, 2, 3}));
        assertFalse(CheckValidPartition.validPartition(new int[] {1, 2, 4}));
        assertFalse(CheckValidPartition.validPartition(new int[] {1, 3, 4}));
        assertFalse(CheckValidPartition.validPartition(new int[] {3, 2, 1}));
    }

    @Test
    void overlappingPairsAndTriplesNeedBothChoices() {
        assertTrue(CheckValidPartition.validPartition(new int[] {1, 1, 1, 1}));
        assertTrue(CheckValidPartition.validPartition(new int[] {1, 1, 1, 2, 3}));
        assertTrue(CheckValidPartition.validPartition(new int[] {1, 1, 1, 1, 1, 1}));
    }

    @Test
    void validSuffixCannotRepairInvalidPrefix() {
        assertFalse(CheckValidPartition.validPartition(new int[] {1, 2, 2}));
        assertFalse(CheckValidPartition.validPartition(new int[] {9, 1, 2, 3}));
    }

    @Test
    void partitionsCanMixGroupTypes() {
        assertTrue(CheckValidPartition.validPartition(new int[] {1, 1, 2, 2, 3, 3}));
        assertTrue(CheckValidPartition.validPartition(new int[] {2, 2, 3, 4, 5, 6, 6, 6}));
    }

    @Test
    void handlesMaximumValues() {
        assertTrue(CheckValidPartition.validPartition(new int[] {999_998, 999_999, 1_000_000}));
        assertTrue(CheckValidPartition.validPartition(new int[] {1_000_000, 1_000_000}));
    }

    @Test
    void handlesMaximumLengthValidArray() {
        int[] nums = new int[100_000];
        Arrays.fill(nums, 1_000_000);
        assertTrue(CheckValidPartition.validPartition(nums));
    }

    @Test
    void rejectsMaximumLengthArrayWithInvalidTail() {
        int[] nums = new int[100_000];
        Arrays.fill(nums, 1);
        nums[nums.length - 1] = 2;
        assertFalse(CheckValidPartition.validPartition(nums));
    }

    @Test
    void preservesInput() {
        int[] nums = {4, 4, 4, 5, 6};
        int[] original = nums.clone();
        CheckValidPartition.validPartition(nums);
        assertArrayEquals(original, nums);
    }

    @Test
    void matchesRecursiveSearchForAllSmallArrays() {
        for (int length = 2; length <= 8; length++) {
            checkAllArrays(new int[length], 0);
        }
    }

    private static void checkAllArrays(int[] nums, int index) {
        if (index == nums.length) {
            assertEquals(canPartitionFrom(nums, 0), CheckValidPartition.validPartition(nums),
                    () -> Arrays.toString(nums));
            return;
        }
        for (int value = 1; value <= 3; value++) {
            nums[index] = value;
            checkAllArrays(nums, index + 1);
        }
    }

    private static boolean canPartitionFrom(int[] nums, int start) {
        if (start == nums.length) return true;
        for (int size = 2; size <= 3 && start + size <= nums.length; size++) {
            boolean equal = true;
            boolean increasing = size == 3;
            for (int offset = 1; offset < size; offset++) {
                equal &= nums[start + offset] == nums[start];
                increasing &= nums[start + offset] == nums[start + offset - 1] + 1;
            }
            if ((equal || increasing) && canPartitionFrom(nums, start + size)) return true;
        }
        return false;
    }
}
