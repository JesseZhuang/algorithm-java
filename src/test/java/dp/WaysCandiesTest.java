package dp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WaysCandiesTest {

    WaysCandies.Solution tbt1;
    WaysCandies.Solution2 tbt2;
    WaysCandies.Solution3 tbt3;
    WaysCandies.Solution4 tbt4;
    WaysCandies.SolutionR tbt5;

    @BeforeEach
    void setUp() {
        tbt1 = new WaysCandies.Solution();
        tbt2 = new WaysCandies.Solution2();
        tbt3 = new WaysCandies.Solution3();
        tbt4 = new WaysCandies.Solution4();
        tbt5 = new WaysCandies.SolutionR();
    }

    @ParameterizedTest
    @CsvSource({"20,5,206085257", "3,3,1", "3,2,3", "4,2,7"})
    void candies(int n, int k, int expected) {
        assertEquals(expected, tbt1.waysToDistribute(n, k));
        assertEquals(expected, tbt2.waysToDistribute(n, k));
        assertEquals(expected, tbt3.waysToDistribute(n, k));
        assertEquals(expected, tbt4.waysToDistribute(n, k));
        assertEquals(expected, tbt5.waysToDistribute(n, k));
    }
}