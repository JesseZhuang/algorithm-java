package stack;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RemoveKDigitsTest {

    @Test
    void basicCase() {
        assertEquals("1219", RemoveKDigits.removeKdigits("1432219", 3));
    }

    @Test
    void leadingZeros() {
        assertEquals("200", RemoveKDigits.removeKdigits("10200", 1));
    }

    @Test
    void removeAll() {
        assertEquals("0", RemoveKDigits.removeKdigits("10", 2));
    }

    @Test
    void singleDigitRemoveAll() {
        assertEquals("0", RemoveKDigits.removeKdigits("9", 1));
    }

    @Test
    void removeNone() {
        assertEquals("123", RemoveKDigits.removeKdigits("123", 0));
    }

    @Test
    void descendingDigits() {
        assertEquals("76", RemoveKDigits.removeKdigits("9876", 2));
    }

    @Test
    void leadingZerosMultiple() {
        assertEquals("1", RemoveKDigits.removeKdigits("10001", 1));
    }

    @Test
    void removeFromEnd() {
        assertEquals("12", RemoveKDigits.removeKdigits("1234", 2));
    }
}
