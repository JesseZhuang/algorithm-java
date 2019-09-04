package string;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValidNumberTest {
    static ValidNumber tbt;

    @BeforeAll
    static void setup() {
        tbt = new ValidNumber();
    }

    @ParameterizedTest
    @CsvFileSource(resources = {"/ValidNumber.csv"}, numLinesToSkip = 2, delimiter = ';')
    void testIsNumber(String s, boolean isNumber) {
        assertEquals(isNumber, tbt.isNumber1(s));
        assertEquals(isNumber, tbt.isNumberRegex(s));
    }
}
