package list;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FirstBadVersionTest {

    @ParameterizedTest(name = "first bad version for {0}, {1}: {2}")
    @CsvFileSource(resources = {"/FirstBadVersion.csv"}, delimiter = ' ', numLinesToSkip = 2)
    void testFirstBadVersion(int n, int answer) {
        FirstBadVersion fbv = new FirstBadVersion(answer);
        assertEquals(fbv.firstBadVersion(n), answer);
        assertEquals(fbv.firstBadVersion3(n), answer);
    }
}
