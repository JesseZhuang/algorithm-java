package array;

import junit.converter.IntegerArrayConverter;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;
import util.IntArrayUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LargestNumberTest {

    @ParameterizedTest
    @CsvSource({
            "'[10,2]', 210",
            "'[3,30,34,5,9]', 9534330",
            "'[0,0]', 0",
            "'[1]', 1",
            "'[12,121]', 12121",
            "'[34323,3432]', 343234323",
    })
    void test(@ConvertWith(IntegerArrayConverter.class) Integer[] nums, String expected) {
        assertEquals(expected, LargestNumber.largestNumber(IntArrayUtil.unBoxIntegerArray(nums)));
    }
}
