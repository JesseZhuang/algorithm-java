package math;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CountSayTest {
    private static CountSay countSay;

    @BeforeAll
    static void setup() {
        countSay = new CountSay();
    }

    @ParameterizedTest(name = "count say sequence {0}: {1}")
    @CsvFileSource(resources = {"/CountSay.csv"}, delimiter = ' ', numLinesToSkip = 2)
    void testCountAndSay(int n, String sequence) {
        assertEquals(countSay.countAndSayIterative(n), sequence);
        assertEquals(countSay.countAndSayRecursive(n), sequence);
    }
}
