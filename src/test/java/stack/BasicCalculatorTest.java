package stack;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BasicCalculatorTest {

    private final BasicCalculator sol = new BasicCalculator();

    @ParameterizedTest
    @CsvSource({
            "1 + 1, 2",
            "' 2-1 + 2 ', 3",
            "'(1+(4+5+2)-3)+(6+8)', 23",
            "0, 0",
            "2147483647, 2147483647",
            "-1, -1",
            "'((1+2))', 3",
            "'(1+(2+(3+4)))', 10",
            "'(7)-(0)+(4)', 11",
            "1-1+1, 1",
            "10-5-3, 2",
    })
    void testCalculate(String input, int expected) {
        assertEquals(expected, sol.calculate(input));
    }
}
