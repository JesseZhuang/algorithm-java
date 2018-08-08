package math;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MultiplyStringsTest {
    private static MultiplyStrings toBeTested;

    @BeforeAll
    static void setup() {
        toBeTested = new MultiplyStrings();
    }

    @ParameterizedTest(name = "{0} * {1} = {2}")
    @CsvFileSource(resources = {"/MultiplyStrings.csv"}, delimiter = ' ', numLinesToSkip = 2)
    void testMultiply(String num1, String num2, String product) {
        assertEquals(toBeTested.multiply(num1, num2), product);
    }
}
