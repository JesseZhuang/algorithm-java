package dp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PartitionEqualSubsetSumTest {

    PartitionEqualSubsetSum tbt = new PartitionEqualSubsetSum();

    @Test
    void canPartitionDP() {
        assertEquals(true, tbt.canPartitionDP(new int[]{1, 5, 11, 5}));
        assertEquals(false, tbt.canPartitionDP(new int[]{1, 2, 3, 5}));
        assertEquals(true, tbt.canPartitionDP(new int[]{1, 1}));
        assertEquals(false, tbt.canPartitionDP(new int[]{1, 2, 5}));
        assertEquals(true, tbt.canPartitionDP(new int[]{2, 2, 1, 1}));
        assertEquals(false, tbt.canPartitionDP(new int[]{1}));
        assertEquals(false, tbt.canPartitionDP(new int[]{100}));
        assertEquals(true, tbt.canPartitionDP(new int[]{1, 2, 3, 4, 5, 6, 7}));
        assertEquals(true, tbt.canPartitionDP(new int[]{14, 9, 8, 4, 3, 2}));
    }

    @Test
    void canPartitionBitSet() {
        assertEquals(true, tbt.canPartitionBitSet(new int[]{1, 5, 11, 5}));
        assertEquals(false, tbt.canPartitionBitSet(new int[]{1, 2, 3, 5}));
        assertEquals(true, tbt.canPartitionBitSet(new int[]{1, 1}));
        assertEquals(false, tbt.canPartitionBitSet(new int[]{1, 2, 5}));
        assertEquals(true, tbt.canPartitionBitSet(new int[]{2, 2, 1, 1}));
        assertEquals(false, tbt.canPartitionBitSet(new int[]{1}));
        assertEquals(false, tbt.canPartitionBitSet(new int[]{100}));
        assertEquals(true, tbt.canPartitionBitSet(new int[]{1, 2, 3, 4, 5, 6, 7}));
        assertEquals(true, tbt.canPartitionBitSet(new int[]{14, 9, 8, 4, 3, 2}));
    }
}
