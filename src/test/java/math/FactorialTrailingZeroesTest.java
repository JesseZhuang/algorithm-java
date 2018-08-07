package math;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FactorialTrailingZeroesTest {
    private static FactorialTrailingZeroes factorialTrailingZeroes;

    @BeforeAll
    static void setup() {
        factorialTrailingZeroes = new FactorialTrailingZeroes();
    }

    @ParameterizedTest(name = "trailing zeroes for {0}!: {1}")
    @CsvFileSource(resources = {"/FactorialTrailingZeroes.csv"}, delimiter = ' ', numLinesToSkip = 2)
    void testTrailingZeroes(int n, int trailingZeroes) {
        assertEquals(factorialTrailingZeroes.trailingZeroes(n), trailingZeroes);
        assertEquals(factorialTrailingZeroes.trailingZeroesIterative(n), trailingZeroes);
        assertEquals(factorialTrailingZeroes.trailingZeroesRecursive(n), trailingZeroes);
        assertEquals(factorialTrailingZeroes.trailingZeroesIterative2(n), trailingZeroes);
    }
}
