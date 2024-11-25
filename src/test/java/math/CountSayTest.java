package math;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CountSayTest {
    private static CountSay.Solution1 tbt;
    private static CountSay.Solution2 tbt2;

    @BeforeAll
    static void setup() {
        tbt = new CountSay.Solution1();
        tbt2 = new CountSay.Solution2();
    }

    @ParameterizedTest(name = "count say sequence {0}: {1}")
    @CsvFileSource(resources = {"/CountSay.csv"}, delimiter = ' ', numLinesToSkip = 2)
    void testCountAndSay(int n, String sequence) {
        assertEquals(tbt.countAndSay(n), sequence);
        assertEquals(tbt2.countAndSay(n), sequence);
    }
}
