package array;

import junit.converter.IntegerArrayConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;
import util.IntArrayUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MinDistanceTargetTest {

    MinDistanceTarget.Solution tbt;

    @BeforeEach
    void setUp() {
        tbt = new MinDistanceTarget.Solution();
    }

    @ParameterizedTest
    @CsvSource({
            "'[1,2,3,4,5]',5,3,1",
            "'[1]',1,0,0",
            "'[1,1,1,1,1,1,1,1,1,1]',1,0,0",
            "'[5,1,2,3,4]',5,0,0",
            "'[1,2,3,4,5]',5,0,4",
            "'[1,2,3,2,1]',2,2,1",
            "'[3,1,1,1,3]',3,2,2",
            "'[7,7,7]',7,1,0"
    })
    void test(@ConvertWith(IntegerArrayConverter.class) Integer[] nums, int target, int start, int expected) {
        assertEquals(expected, tbt.getMinDistance(IntArrayUtil.unBoxIntegerArray(nums), target, start));
    }
}
