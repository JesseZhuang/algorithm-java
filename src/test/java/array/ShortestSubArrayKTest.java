package array;

import junit.converter.IntegerArrayConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;
import util.IntArrayUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShortestSubArrayKTest {

    ShortestSubArrayK.Solution tbt;

    @BeforeEach
    void setUp() {
        tbt = new ShortestSubArrayK.Solution();
    }

    @ParameterizedTest
    @CsvSource({
            "'[2,-1,2]',3,3",
            "'[1,2]',4,-1",
            "'[1]',1,1"
    })
    void test(@ConvertWith(IntegerArrayConverter.class) Integer[] nums, int k, int exp) {
        assertEquals(exp, tbt.shortestSubarray(IntArrayUtil.unBoxIntegerArray(nums), k));
    }
}