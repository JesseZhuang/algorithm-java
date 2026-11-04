package array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ConstructDistancedSequenceTest {
    @Test
    void testSingleValue() {
        assertArrayEquals(new int[]{1}, ConstructDistancedSequence.constructDistancedSequence(1));
    }

    @Test
    void testExamples() {
        assertArrayEquals(new int[]{3, 1, 2, 3, 2},
                ConstructDistancedSequence.constructDistancedSequence(3));
        assertArrayEquals(new int[]{5, 3, 1, 4, 3, 5, 2, 4, 2},
                ConstructDistancedSequence.constructDistancedSequence(5));
    }

    @Test
    void testMaximumInputHasValidCountsAndDistances() {
        int n = 20;
        int[] sequence = ConstructDistancedSequence.constructDistancedSequence(n);
        assertEquals(2 * n - 1, sequence.length);
        int[] counts = new int[n + 1];
        int[] firstIndex = new int[n + 1];
        for (int i = 0; i < sequence.length; i++) {
            int value = sequence[i];
            assertTrue(value >= 1 && value <= n, "Values must be between 1 and n");
            if (counts[value] == 0) {
                firstIndex[value] = i;
            } else {
                assertEquals(value, i - firstIndex[value], "Distance for value " + value);
            }
            counts[value]++;
        }
        assertEquals(1, counts[1]);
        for (int value = 2; value <= n; value++) {
            assertEquals(2, counts[value], "Count for value " + value);
        }
    }
}
