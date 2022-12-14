package dp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LongestIncreasingSubSequenceTest {
    LongestIncreasingSubSequence tbt;

    @BeforeEach
    void setup() {
        tbt = new LongestIncreasingSubSequence();
    }

    @Test
    void testFindLIS() {
        // {2,3,7,18} or {2,3,7,101} both fine
        assertEquals(Arrays.asList(2, 3, 7, 18), tbt.longestIncreasingSequence(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
    }
}
