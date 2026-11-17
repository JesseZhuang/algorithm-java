package array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MaximizeGreatnessTest {

    @Test
    void matchesFirstOfficialExample() {
        assertEquals(4, MaximizeGreatness.maximizeGreatness(new int[]{1, 3, 5, 2, 1, 3, 1}));
    }

    @Test
    void handlesShorterDuplicateCase() {
        assertEquals(3, MaximizeGreatness.maximizeGreatness(new int[]{1, 3, 5, 2, 1}));
    }

    @Test
    void matchesSecondOfficialExample() {
        assertEquals(3, MaximizeGreatness.maximizeGreatness(new int[]{1, 2, 3, 4}));
    }

    @Test
    void returnsZeroWhenAllValuesAreEqual() {
        assertEquals(0, MaximizeGreatness.maximizeGreatness(new int[]{7, 7, 7, 7}));
    }

    @Test
    void handlesSingleton() {
        assertEquals(0, MaximizeGreatness.maximizeGreatness(new int[]{42}));
    }

    @Test
    void handlesDuplicateBoundaries() {
        assertEquals(3, MaximizeGreatness.maximizeGreatness(new int[]{1, 1, 1, 2, 2, 3}));
    }

    @Test
    void handlesIntegerExtremesWithoutOverflow() {
        assertEquals(2, MaximizeGreatness.maximizeGreatness(
                new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE}));
    }
}
