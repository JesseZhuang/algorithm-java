package string;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BetterCompressionTest {
    private static BetterCompression.Solution tbt;

    @BeforeAll
    static void setup() {
        tbt = new BetterCompression.Solution();
    }

    @ParameterizedTest(name = "betterCompression({0}) = {1}")
    @CsvSource({
            "a3c9b2c1, a3b2c10",
            "a12b56c1, a12b56c1",
            "a12c56a1b5, a13b5c56",
            "c2b3a1, a1b3c2",
            "a2b4c1, a2b4c1",
    })
    void testBetterCompression(String input, String expected) {
        assertEquals(expected, tbt.betterCompression(input));
    }
}
