package hash;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IsomorphicStringsTest {
    private static IsomorphicStrings toBeTested;

    @BeforeAll
    static void setup() {
        toBeTested = new IsomorphicStrings();
    }

    @ParameterizedTest(name = "isomorphic strings {0}, {1} = {2}")
    @CsvFileSource(resources = {"/IsomorphicStrings.csv"}, delimiter = ' ', numLinesToSkip = 2)
    void testIsomorphic(String a, String b, boolean result) {
        assertEquals(toBeTested.isIsomorphic(a, b), result);
        assertEquals(toBeTested.isIsomorphic2(a, b), result);
        assertEquals(toBeTested.isIsomorphic3(a, b), result);

    }
}
