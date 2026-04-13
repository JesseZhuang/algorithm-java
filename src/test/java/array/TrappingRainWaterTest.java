package array;

import junit.converter.IntegerArrayConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;
import util.IntArrayUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TrappingRainWaterTest {

    TrappingRainWater.Solution tbt1;
    TrappingRainWater.Solution2 tbt2;

    @BeforeEach
    void setUp() {
        tbt1 = new TrappingRainWater.Solution();
        tbt2 = new TrappingRainWater.Solution2();
    }

    @ParameterizedTest
    @CsvSource({
            "'[0,1,0,2,1,0,1,3,2,1,2,1]', 6",
            "'[4,2,0,3,2,5]', 9",
            "'[]', 0",
            "'[5]', 0",
            "'[3,0,3]', 3",
            "'[5,4,3,2,1]', 0",
            "'[1,2,3,4,5]', 0",
            "'[2,2,2,2]', 0",
            "'[5,0,0,0,5]', 15",
            "'[3,0,2,0,4]', 7"
    })
    void test(@ConvertWith(IntegerArrayConverter.class) Integer[] height, int expected) {
        int[] h = IntArrayUtil.unBoxIntegerArray(height);
        assertEquals(expected, tbt1.trap(h));
        assertEquals(expected, tbt2.trap(h));
    }
}
