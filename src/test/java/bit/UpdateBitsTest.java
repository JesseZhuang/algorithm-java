package bit;

import org.junit.jupiter.api.BeforeEach;
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
            "1024,21,2,6,1108",
            "1024,31,2,6,1148",
            "1024,31,2,31,124",
    })
    void test(int N, int M, int i, int j, int exp) {
        System.out.println();
        assertEquals(exp, tbt.updateBits(N, M, i, j));
    }
}