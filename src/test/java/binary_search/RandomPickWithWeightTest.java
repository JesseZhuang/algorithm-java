package binary_search;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RandomPickWithWeightTest {

    @Test
    void testSingleElement() {
        RandomPickWithWeight sol = new RandomPickWithWeight(new int[]{5});
        for (int i = 0; i < 100; i++) {
            assertEquals(0, sol.pickIndex());
        }
        RandomPickWithWeight2 sol2 = new RandomPickWithWeight2(new int[]{5});
        for (int i = 0; i < 100; i++) {
            assertEquals(0, sol2.pickIndex());
        }
    }

    @Test
    void testEqualWeights() {
        int[] w = {1, 1, 1};
        int trials = 3000;
        int[] counts = new int[3];
        RandomPickWithWeight sol = new RandomPickWithWeight(w);
        for (int i = 0; i < trials; i++) {
            counts[sol.pickIndex()]++;
        }
        for (int i = 0; i < 3; i++) {
            assertTrue(counts[i] > 500, "index " + i + " picked only " + counts[i] + " times");
        }

        // test approach 2
        counts = new int[3];
        RandomPickWithWeight2 sol2 = new RandomPickWithWeight2(w);
        for (int i = 0; i < trials; i++) {
            counts[sol2.pickIndex()]++;
        }
        for (int i = 0; i < 3; i++) {
            assertTrue(counts[i] > 500, "index " + i + " picked only " + counts[i] + " times (linear)");
        }
    }

    @Test
    void testSkewedWeights() {
        int[] w = {1, 99};
        int trials = 10000;
        int[] counts = new int[2];
        RandomPickWithWeight sol = new RandomPickWithWeight(w);
        for (int i = 0; i < trials; i++) {
            counts[sol.pickIndex()]++;
        }
        assertTrue(counts[1] > 9000, "index 1 picked only " + counts[1] + " times, expected > 9000");

        // test approach 2
        counts = new int[2];
        RandomPickWithWeight2 sol2 = new RandomPickWithWeight2(w);
        for (int i = 0; i < trials; i++) {
            counts[sol2.pickIndex()]++;
        }
        assertTrue(counts[1] > 9000, "index 1 picked only " + counts[1] + " times (linear), expected > 9000");
    }

    @Test
    void testIndexInValidRange() {
        int[] w = {3, 7, 2, 5};
        RandomPickWithWeight sol = new RandomPickWithWeight(w);
        for (int i = 0; i < 1000; i++) {
            int idx = sol.pickIndex();
            assertTrue(idx >= 0 && idx < w.length, "index out of range: " + idx);
        }
        RandomPickWithWeight2 sol2 = new RandomPickWithWeight2(w);
        for (int i = 0; i < 1000; i++) {
            int idx = sol2.pickIndex();
            assertTrue(idx >= 0 && idx < w.length, "index out of range (linear): " + idx);
        }
    }
}
