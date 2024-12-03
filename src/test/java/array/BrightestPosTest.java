package array;

import junit.converter.TwoDIntegerArrayConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;
import util.IntArrayUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BrightestPosTest {

    BrightestPos.Solution tbt;
    BrightestPos.Solution2 tbt2;

    @BeforeEach
    void setUp() {
        tbt = new BrightestPos.Solution();
        tbt2 = new BrightestPos.Solution2();
    }

    @ParameterizedTest
    @CsvSource({
            "'[[-3, 2], [1, 2], [3, 2]]', -1",
            "'[[1, 0], [0, 1]]', 1"
    })
    void test(@ConvertWith(TwoDIntegerArrayConverter.class) Integer[][] lights, int exp) {
        int[][] input = IntArrayUtil.unbox2DIntegerArray(lights);
        assertEquals(exp, tbt.brightestPosition(input));
        assertEquals(exp, tbt2.brightestPosition(input));
    }
}
