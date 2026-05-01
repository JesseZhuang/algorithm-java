package string;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ReverseCountAndSayTest {

    @Test void empty() {
        assertEquals("", ReverseCountAndSay.reverseCountSay(""));
    }

    @Test void example() {
        assertEquals("1211", ReverseCountAndSay.reverseCountSay("111221"));
    }

    @Test void singlePair() {
        assertEquals("3", ReverseCountAndSay.reverseCountSay("13"));
        assertEquals("11", ReverseCountAndSay.reverseCountSay("21"));
        assertEquals("222", ReverseCountAndSay.reverseCountSay("32"));
    }

    @Test void countNine() {
        assertEquals("777777777", ReverseCountAndSay.reverseCountSay("97"));
    }

    @Test void zeroCountYieldsNothing() {
        // 04 -> repeat '4' zero times. Result for "0405" is "5" (after second pair).
        assertEquals("5", ReverseCountAndSay.reverseCountSay("0415"));
    }

    @Test void multiplePairs() {
        // 11 21 31 -> 1 + 11 + 111 = "111111" (six ones).
        assertEquals("111111", ReverseCountAndSay.reverseCountSay("112131"));
    }

    @Test void roundTripCountAndSay() {
        // For each n in [2..10], reverse(countAndSay(n)) == countAndSay(n-1).
        for (int n = 2; n <= 10; n++) {
            String forward = CountAndSay.countAndSay(n);
            String back = ReverseCountAndSay.reverseCountSay(forward);
            assertEquals(CountAndSay.countAndSay(n - 1), back, "n=" + n);
        }
    }

    @Test void oddLengthThrows() {
        assertThrows(IllegalArgumentException.class,
            () -> ReverseCountAndSay.reverseCountSay("123"));
    }

    @Test void nullReturnsEmpty() {
        assertEquals("", ReverseCountAndSay.reverseCountSay(null));
    }
}
