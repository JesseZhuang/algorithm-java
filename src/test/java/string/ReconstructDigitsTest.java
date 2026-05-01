package string;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReconstructDigitsTest {
    private static ReconstructDigits.Solution tbt;

    @BeforeAll
    static void setup() {
        tbt = new ReconstructDigits.Solution();
    }

    @ParameterizedTest(name = "originalDigits({0}) = {1}")
    @CsvSource({
            "owoztneoer, 012",
            "fviefuro, 45",
            "zeroonetwothreefourfivesixseveneightnine, 0123456789",
    })
    void testOriginalDigits(String input, String expected) {
        assertEquals(expected, tbt.originalDigits(input));
    }
}
