package string;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LongestCommonPrefixTest {
    private static LongestCommonPrefix toBeTested;

    @BeforeAll
    static void setup() {
        toBeTested = new LongestCommonPrefix();
    }

    @ParameterizedTest(name = "prefix of {0} is {1}\n")
    @CsvFileSource(resources = {"/LongestCommonPrefix.csv"}, delimiter = ';', numLinesToSkip = 2)
    void testLongestPrefix(String strs, String prefix) {
        String[] strings;
        if (strs != null) strings = strs.split("\\s*,\\s*");
        else strings = null;
        assertEquals(toBeTested.longestCommonPrefix(strings), prefix);
        assertEquals(toBeTested.longestCommonPrefix1(strings), prefix);
        assertEquals(toBeTested.longestCommonPrefix2(strings), prefix);
    }
}
