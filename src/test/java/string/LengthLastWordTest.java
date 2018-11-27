package string;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LengthLastWordTest {
    private static LengthLastWord toBeTested;

    @BeforeAll
    static void setup() {
        toBeTested = new LengthLastWord();
    }

    @ParameterizedTest(name = "length of last word of {0} is {1}\n")
    @CsvFileSource(resources = {"/LengthLastWord.csv"}, delimiter = ' ', numLinesToSkip = 2)
    void testLengthLastWord(String string, int len) {
        assertEquals(toBeTested.lengthOfLastWord(string), len);
        assertEquals(toBeTested.lengthOfLastWord2(string), len);
        assertEquals(toBeTested.lengthOfLastWord3(string), len);
        assertEquals(toBeTested.lengthOfLastWord4(string), len);
        assertEquals(toBeTested.lengthOfLastWord5(string), len);
    }


}
