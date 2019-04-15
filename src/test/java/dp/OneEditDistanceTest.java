package dp;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OneEditDistanceTest {
    private static final OneEditDistance tbt = new OneEditDistance();

    @ParameterizedTest(name = "{0}, {1}: {2}")
    @CsvFileSource(numLinesToSkip = 2, resources = {"/OneEditDistance.csv"}, delimiter = ' ')
    void testOneEditDistance(String s1, String s2, boolean oneEditAway) {
        assertEquals(oneEditAway, tbt.isEditDistanceOne1(s1, s2));
        assertEquals(oneEditAway, tbt.isEditDistanceOne2(s1, s2));
    }
}
