package dp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrigTypedStringIITest {

    OrigTypedStringII.Solution tbt;

    @BeforeEach
    void setUp() {
        tbt = new OrigTypedStringII.Solution();
    }

    @ParameterizedTest
    @CsvSource({"aabbccdd, 8, 1", "aaabbb, 3, 8"})
    void test(String word, int k, int expected) {
        assertEquals(expected, tbt.possibleStringCount(word, k));
    }
}