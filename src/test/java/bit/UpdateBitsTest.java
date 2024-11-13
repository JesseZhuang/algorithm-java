package bit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UpdateBitsTest {

    UpdateBits.Solution tbt;

    @BeforeEach
    void setUp() {
        tbt = new UpdateBits.Solution();
    }

    @ParameterizedTest
    @CsvSource({
            "-521,0,31,31,2147483127",
            "1024,21,2,6,1108",
            "1024,31,2,6,1148",
            "1024,31,2,31,124",
            "456,31,27,31,-134217272"
    })
    void test(int N, int M, int i, int j, int exp) {
        assertEquals(exp, tbt.updateBits(N, M, i, j));
    }

    @Test
    void test_overflow() {
        assertEquals(-1, 0xffff_ffff);
        assertEquals(1 << 31, Integer.MIN_VALUE);
        assertEquals(1 << 31 - 1, 1 << 30); // precedence
        assertEquals((1 << 31) - 1, Integer.MAX_VALUE);
        System.out.println(Integer.toHexString(-134217272));
    }
}