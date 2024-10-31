package dp;

import junit.converter.IntegerArrayConverter;
import junit.converter.TwoDIntegerArrayConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;
import util.IntArrayUtil;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MinDistanceTravelTest {

    MinDistanceTravel.Solution tbt;

    @BeforeEach
    void setUp() {
        tbt = new MinDistanceTravel.Solution();
    }

    @ParameterizedTest
    @CsvSource({
            "'[9, 11, 99, 101]','[[7,1], [10,1], [14,1], [96,1], [100,1], [103,1]]', 6",
            "'[0,4,6]', '[[2,2],[6,2]]', 4",
    })
    void test(@ConvertWith(IntegerArrayConverter.class) Integer[] robot,
              @ConvertWith(TwoDIntegerArrayConverter.class) Integer[][] factory, int exp) {
        int[][] fac = IntArrayUtil.unbox2DIntegerArray(factory);
        assertEquals(exp, tbt.minimumTotalDistance(Arrays.asList(robot), fac));
    }
}