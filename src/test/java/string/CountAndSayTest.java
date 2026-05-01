package string;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CountAndSayTest {

    @Test void base() {
        assertEquals("1", CountAndSay.countAndSay(1));
    }

    @Test void term2() {
        assertEquals("11", CountAndSay.countAndSay(2));
    }

    @Test void term3() {
        assertEquals("21", CountAndSay.countAndSay(3));
    }

    @Test void term4() {
        assertEquals("1211", CountAndSay.countAndSay(4));
    }

    @Test void term5() {
        assertEquals("111221", CountAndSay.countAndSay(5));
    }

    @Test void term6() {
        assertEquals("312211", CountAndSay.countAndSay(6));
    }

    @Test void term7() {
        assertEquals("13112221", CountAndSay.countAndSay(7));
    }

    @Test void term8() {
        assertEquals("1113213211", CountAndSay.countAndSay(8));
    }

    @Test void term9And10() {
        assertEquals("31131211131221", CountAndSay.countAndSay(9));
        assertEquals("13211311123113112211", CountAndSay.countAndSay(10));
    }

    @Test void monotonicLength() {
        // Lengths are non-decreasing for the count-and-say sequence.
        int prev = 0;
        for (int i = 1; i <= 12; i++) {
            int len = CountAndSay.countAndSay(i).length();
            org.junit.jupiter.api.Assertions.assertTrue(len >= prev,
                "term " + i + " length " + len + " not >= " + prev);
            prev = len;
        }
    }

    @Test void onlyDigits1To3() {
        // From "1", subsequent terms only contain digits 1, 2, 3.
        for (int i = 1; i <= 15; i++) {
            for (char c : CountAndSay.countAndSay(i).toCharArray()) {
                org.junit.jupiter.api.Assertions.assertTrue(c == '1' || c == '2' || c == '3',
                    "unexpected digit '" + c + "' at term " + i);
            }
        }
    }
}
