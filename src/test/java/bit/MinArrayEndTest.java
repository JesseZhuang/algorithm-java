package bit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MinArrayEndTest {

    MinArrayEnd.Solution tbt;

    @BeforeEach
    void setUp() {
        tbt = new MinArrayEnd.Solution();
    }

    @ParameterizedTest
    @CsvSource({
//            "3,4,6",   // 4,5,6
//            "2,7,15",  // 7,15
            "6,4,13",    // 4,5,6,7,8,13
            "7,4,14",
    })
    void test(int n, int x, int exp) {
        assertEquals(exp, tbt.minEnd(n, x));
    }
}