package dp;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PaintFenceTest {
    private static PaintFence paintFence;

    @BeforeAll
    static void setup() {
        paintFence = new PaintFence();
    }

    @ParameterizedTest(name = "number of ways {0} posts {1} colors = {2}")
    @CsvFileSource(resources = {"/PaintFence.csv"}, delimiter = ' ', numLinesToSkip = 2)
    void testNumWays(int numPosts, int numColors, int numWays) {
        assertEquals(paintFence.numWaysDP(numPosts, numColors), numWays);
    }
}
