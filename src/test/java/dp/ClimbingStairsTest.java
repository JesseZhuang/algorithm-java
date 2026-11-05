package dp;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClimbingStairsTest {
    @ParameterizedTest(name = "{0} stairs have {1} ways")
    @CsvSource({"1, 1", "2, 2", "3, 3", "45, 1836311903"})
    void testClimbStairs(int n, int expected) {
        ClimbingStairs solution = new ClimbingStairs();
        assertEquals(expected, solution.climbStairsIterative(n));
        assertEquals(expected, solution.climbStairsMemo(n));
    }
}
