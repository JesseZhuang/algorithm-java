package dp;

import junit.converter.IntegerArrayConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;
import util.IntArrayUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MaxConsecutiveOnesIITest {

    MaxConsecutiveOnesII.Solution2 tbt;

    @BeforeEach
    void setUp() {
        tbt = new MaxConsecutiveOnesII.Solution2();
    }

    @ParameterizedTest
    @CsvSource({
            "'1,0,1,1,0,0,0,0', 4",
            "'1,0,1,1,0,1', 4",
            "'1,0,1,1,0', 4",
            "'1,0,1,0,1', 3",
    })
    void test(@ConvertWith(IntegerArrayConverter.class) Integer[] nums, int expected) {
        assertEquals(expected, tbt.findMaxConsecutiveOnes(IntArrayUtil.unBoxIntegerArray(nums)));
    }
}