package math;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PossibleWaysEventTest {

    PossibleWaysEvent.Solution tbt;

    @BeforeEach
    void setUp() {
        tbt = new PossibleWaysEvent.Solution();
    }

    @ParameterizedTest
    @CsvSource({"1,2,3,6", "5,2,1,32", "3,3,4,684"})
    void test(int n, int x, int y, int expected) {
        assertEquals(expected, tbt.numberOfWays(n, x, y));
    }
}