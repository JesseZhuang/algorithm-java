package stack;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BasicCalculatorIITest {

    @ParameterizedTest
    @CsvSource({"3+2*2, 7", " 3/2 , 1", " 3+5 / 2 , 5", "1-1+1, 1", "2147483647, 2147483647"})
    void testSolution(String input, int expected) {
        assertEquals(expected, BasicCalculatorII.calculate(input));
    }

    @Test
    void testCharIntCast() {
        assertEquals(63, 10 + '5'); // ascii 53 for '5'
        assertEquals(53, (int) '5');
        assertEquals(15, '5' + 10 - '0');
        assertEquals(2147483647, 2147483640 + '7' - '0'); // overflowed but fine
    }
}