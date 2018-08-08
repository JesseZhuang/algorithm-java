package math;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CountPrimesTest {
    private static CountPrimes countPrimes;

    @BeforeAll
    static void setup() {
        countPrimes = new CountPrimes();
    }

    @ParameterizedTest(name = "# of primes <= {0} : {1}")
    @CsvFileSource(resources = {"/CountPrimes.csv"}, numLinesToSkip = 2)
    void testCountPrimes(int n, int count) {
        assertEquals(countPrimes.countPrimesErato(n), count);
        assertEquals(countPrimes.countPrimesSkipEven(n), count);
        assertEquals(countPrimes.countPrimesBitSet(n), count);
    }
}
