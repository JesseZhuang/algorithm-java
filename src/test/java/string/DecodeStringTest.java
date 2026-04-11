package string;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DecodeStringTest {
    private static DecodeString.Solution tbt1;
    private static DecodeString.Solution2 tbt2;

    @BeforeAll
    static void setup() {
        tbt1 = new DecodeString.Solution();
        tbt2 = new DecodeString.Solution2();
    }

    @ParameterizedTest(name = "decodeString({0}) = {1}")
    @CsvSource({
            "3[a]2[bc], aaabcbc",
            "3[a2[c]], accaccacc",
            "2[abc]3[cd]ef, abcabccdcdcdef",
            "abc, abc",
            "1[a], a",
            "10[a], aaaaaaaaaa",
            "2[a2[b3[c]]], abcccbcccabcccbccc",
            "'', ''",
            "2[a]2[b], aabb",
    })
    void testDecodeString(String input, String expected) {
        if (input == null) input = "";
        if (expected == null) expected = "";
        assertEquals(expected, tbt1.decodeString(input));
        assertEquals(expected, tbt2.decodeString(input));
    }
}
