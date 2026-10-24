package math;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FindThePivotIntegerTest {

    @ParameterizedTest
    @CsvSource({"1, 1", "8, 6", "4, -1", "49, 35"})
    void perfectSquareMethodFindsPivotOrReturnsNegativeOne(int n, int expected) {
        assertEquals(expected, FindThePivotInteger.pivotInteger(n));
    }

    @ParameterizedTest
    @CsvSource({"1, 1", "8, 6", "4, -1", "49, 35"})
    void runningSumMethodFindsPivotOrReturnsNegativeOne(int n, int expected) {
        assertEquals(expected, FindThePivotInteger.pivotIntegerPrefixSum(n));
    }

    @Test
    void algorithmsAgreeForEveryAllowedInput() {
        for (int n = 1; n <= 1000; n++) {
            assertEquals(FindThePivotInteger.pivotInteger(n),
                    FindThePivotInteger.pivotIntegerPrefixSum(n), "n=" + n);
        }
    }
}
