package array;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

class MinimumOperationsToMakeArrayIncreasingTest {

    @Test
    void equalValuesExampleRequiresThreeOperations() {
        assertEquals(3, MinimumOperationsToMakeArrayIncreasing.minOperations(new int[]{1, 1, 1}));
    }

    @Test
    void mixedValuesExampleRequiresFourteenOperations() {
        assertEquals(14, MinimumOperationsToMakeArrayIncreasing.minOperations(new int[]{1, 5, 2, 4, 1}));
    }

    @Test
    void singleElementRequiresNoOperations() {
        assertEquals(0, MinimumOperationsToMakeArrayIncreasing.minOperations(new int[]{8}));
    }

    @Test
    void strictlyIncreasingArrayRequiresNoOperations() {
        assertEquals(0, MinimumOperationsToMakeArrayIncreasing.minOperations(new int[]{1, 2, 3, 5, 10}));
    }

    @Test
    void equalPairRequiresOneOperation() {
        assertEquals(1, MinimumOperationsToMakeArrayIncreasing.minOperations(new int[]{5, 5}));
    }

    @Test
    void descendingArrayCarriesAdjustedValuesForward() {
        assertEquals(20, MinimumOperationsToMakeArrayIncreasing.minOperations(new int[]{5, 4, 3, 2, 1}));
    }

    @Test
    void carryChainUsesAdjustedPreviousValue() {
        assertEquals(10, MinimumOperationsToMakeArrayIncreasing.minOperations(new int[]{3, 1, 2, 2}));
    }

    @Test
    void higherOriginalValueResetsAdjustedPreviousValue() {
        assertEquals(23, MinimumOperationsToMakeArrayIncreasing.minOperations(new int[]{2, 1, 10, 1, 1}));
    }

    @Test
    void minimumSingleValueRequiresNoOperations() {
        assertEquals(0, MinimumOperationsToMakeArrayIncreasing.minOperations(new int[]{1}));
    }

    @Test
    void maximumSingleValueRequiresNoOperations() {
        assertEquals(0, MinimumOperationsToMakeArrayIncreasing.minOperations(new int[]{10_000}));
    }

    @Test
    void minimumThenMaximumRequiresNoOperations() {
        assertEquals(0, MinimumOperationsToMakeArrayIncreasing.minOperations(new int[]{1, 10_000}));
    }

    @Test
    void maximumThenMinimumAllowsAdjustmentBeyondInputBound() {
        assertEquals(10_000, MinimumOperationsToMakeArrayIncreasing.minOperations(new int[]{10_000, 1}));
    }

    @Test
    void equalMaximumValuesRequireOneOperation() {
        assertEquals(1, MinimumOperationsToMakeArrayIncreasing.minOperations(new int[]{10_000, 10_000}));
    }

    @Test
    void maximumLengthWithHighFirstValueAndOnes() {
        int[] nums = new int[5_000];
        Arrays.fill(nums, 1);
        nums[0] = 10_000;

        assertEquals(62_482_501, MinimumOperationsToMakeArrayIncreasing.minOperations(nums));
    }

    @Test
    void maximumLengthWithAllMaximumValues() {
        int[] nums = new int[5_000];
        Arrays.fill(nums, 10_000);

        assertEquals(12_497_500, MinimumOperationsToMakeArrayIncreasing.minOperations(nums));
    }

    @Test
    void leavesInputUnchanged() {
        int[] nums = {1, 5, 2, 4, 1};
        int[] original = nums.clone();

        assertEquals(14, MinimumOperationsToMakeArrayIncreasing.minOperations(nums));
        assertArrayEquals(original, nums);
    }

    @Test
    void repeatedCallsReturnSameResult() {
        int[] nums = {1, 5, 2, 4, 1};

        assertEquals(14, MinimumOperationsToMakeArrayIncreasing.minOperations(nums));
        assertEquals(3, MinimumOperationsToMakeArrayIncreasing.minOperations(new int[]{1, 1, 1}));
        assertEquals(0, MinimumOperationsToMakeArrayIncreasing.minOperations(new int[]{8}));
        assertEquals(14, MinimumOperationsToMakeArrayIncreasing.minOperations(nums));
    }
}
