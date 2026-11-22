package array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ArithmeticTripletsTest {
    @Test
    void countsFirstOfficialExample() {
        assertEquals(2, ArithmeticTriplets.arithmeticTriplets(new int[]{0, 1, 4, 6, 7, 10}, 3));
    }

    @Test
    void countsSecondOfficialExample() {
        assertEquals(2, ArithmeticTriplets.arithmeticTriplets(new int[]{4, 5, 6, 7, 8, 9}, 2));
    }

    @Test
    void returnsZeroWhenNoTripletExists() {
        assertEquals(0, ArithmeticTriplets.arithmeticTriplets(new int[]{0, 1, 4, 5, 8}, 3));
    }

    @Test
    void countsOverlappingTriplets() {
        assertEquals(4, ArithmeticTriplets.arithmeticTriplets(new int[]{0, 1, 2, 3, 4, 5}, 1));
    }

    @Test
    void countsTripletInMinimumSizeInput() {
        assertEquals(1, ArithmeticTriplets.arithmeticTriplets(new int[]{0, 3, 6}, 3));
    }

    @Test
    void requiresBothGapsToMatchInMinimumSizeInput() {
        assertEquals(0, ArithmeticTriplets.arithmeticTriplets(new int[]{0, 3, 7}, 3));
    }
}
