package heap;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ReorganizeStringTest {

    static boolean isValid(String result, String s) {
        if (result.isEmpty()) return false;
        char[] r = result.toCharArray(), o = s.toCharArray();
        java.util.Arrays.sort(r);
        java.util.Arrays.sort(o);
        if (!java.util.Arrays.equals(r, o)) return false;
        for (int i = 1; i < result.length(); i++)
            if (result.charAt(i) == result.charAt(i - 1)) return false;
        return true;
    }

    @ParameterizedTest
    @CsvSource({
            "'aab', true",
            "'aaab', false",
            "'a', true",
            "'aa', false",
            "'ab', true",
            "'aabb', true",
            "'aaabb', true",
            "'vvvlo', true",
    })
    void testCounting(String s, boolean possible) {
        ReorganizeString sol = new ReorganizeString();
        String res = sol.reorganizeString(s);
        if (!possible) {
            assertEquals("", res);
        } else {
            assertTrue(isValid(res, s), "Invalid result: " + res + " for input: " + s);
        }
    }

    @ParameterizedTest
    @CsvSource({
            "'aab', true",
            "'aaab', false",
            "'a', true",
            "'aa', false",
            "'ab', true",
            "'aabb', true",
            "'aaabb', true",
            "'vvvlo', true",
    })
    void testHeap(String s, boolean possible) {
        ReorganizeString sol = new ReorganizeString();
        String res = sol.reorganizeString2(s);
        if (!possible) {
            assertEquals("", res);
        } else {
            assertTrue(isValid(res, s), "Invalid result: " + res + " for input: " + s);
        }
    }
}
