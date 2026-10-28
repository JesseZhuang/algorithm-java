package stack;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MinSwapsBalanceTest {

    @ParameterizedTest
    @CsvSource({
        "'[]', 0",
        "'][', 1",
        "'][][', 1",
        "']]][[[', 2",
        "']]]][[[[', 2",
        "']]]]][[[[[', 3",
        "'[[]]', 0",
        "'[[[]]]', 0",
        "'[[][]]', 0",
        "'[][][][]', 0",
        "'[[]][][[]]', 0",
        "']][[]][[', 1",
        "']][[]][[]][[', 1"
    })
    void countsMinimumSwaps(String input, int expected) {
        assertEquals(expected, new MinSwapsBalance.Solution().minSwaps(input));
    }

    @Test
    void handlesMaximumLengthWithWorstImbalance() {
        String input = "]".repeat(500_000) + "[".repeat(500_000);
        assertEquals(250_000, new MinSwapsBalance.Solution().minSwaps(input));
    }

    @Test
    void handlesMaximumLengthAlternatingBrackets() {
        String input = "[]".repeat(500_000);
        assertEquals(0, new MinSwapsBalance.Solution().minSwaps(input));
    }
}
