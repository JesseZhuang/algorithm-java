package bit;

import junit.converter.IntegerArrayConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;
import util.IntArrayUtil;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class MaxXorQueryTest {

    MaxXorQuery.Solution tbt;

    @BeforeEach
    void setUp() {
        tbt = new MaxXorQuery.Solution();
    }

    @ParameterizedTest
    @CsvSource({
            "'0,1,1,3',2,'0,3,2,3'",
            "'2,3,4,7',3,'5,2,6,5'",
            "'0,1,2,2,5,7',3,'4,3,6,4,6,7'"
    })
    void test(@ConvertWith(IntegerArrayConverter.class) Integer[] a, int mBit,
              @ConvertWith(IntegerArrayConverter.class) Integer[] expected) {
        int[] nums = IntArrayUtil.unBoxIntegerArray(a), exp = IntArrayUtil.unBoxIntegerArray(expected);
        assertArrayEquals(exp, tbt.getMaximumXor(nums, mBit));
    }
}