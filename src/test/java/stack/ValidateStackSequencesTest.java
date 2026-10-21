package stack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ValidateStackSequencesTest {

    @Test
    void testExampleValid() {
        assertTrue(ValidateStackSequences.validateStackSequences(
                new int[]{1, 2, 3, 4, 5}, new int[]{4, 5, 3, 2, 1}));
    }

    @Test
    void testExampleInvalid() {
        assertFalse(ValidateStackSequences.validateStackSequences(
                new int[]{1, 2, 3, 4, 5}, new int[]{4, 3, 5, 1, 2}));
    }

    @Test
    void testSingleElement() {
        assertTrue(ValidateStackSequences.validateStackSequences(new int[]{0}, new int[]{0}));
    }

    @Test
    void testAscendingPops() {
        assertTrue(ValidateStackSequences.validateStackSequences(
                new int[]{1, 2, 3, 4}, new int[]{1, 2, 3, 4}));
    }

    @Test
    void testReversedPops() {
        assertTrue(ValidateStackSequences.validateStackSequences(
                new int[]{1, 2, 3, 4}, new int[]{4, 3, 2, 1}));
    }

    @Test
    void testImpossibleInterleaving() {
        assertFalse(ValidateStackSequences.validateStackSequences(
                new int[]{1, 2, 3, 4}, new int[]{3, 1, 2, 4}));
    }

    @Test
    void testThousandElements() {
        int[] pushed = new int[1000];
        int[] popped = new int[1000];
        for (int i = 0; i < pushed.length; i++) {
            pushed[i] = i;
            popped[i] = i % 2 == 0 ? i + 1 : i - 1;
        }
        assertTrue(ValidateStackSequences.validateStackSequences(pushed, popped));
    }
}
