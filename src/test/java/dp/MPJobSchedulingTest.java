package dp;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;
import junit.converter.IntegerArrayConverter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static util.IntArrayUtil.unBoxIntegerArray;

class MPJobSchedulingTest {

    @ParameterizedTest(name = "start={0} end={1} profit={2} -> {3}")
    @CsvSource({
            "'1,2,3,3', '3,4,5,6', '50,10,40,70', 120",
            "'1,2,3,4,6', '3,5,10,6,9', '20,20,100,70,60', 150",
            "'1,1,1', '2,3,4', '5,6,4', 6",
            "'1', '2', '50', 50",
            "'1,3,5', '2,4,6', '10,20,30', 60",
            "'1,1,1', '10,10,10', '5,6,4', 6",
            "'1,2,3', '2,3,4', '10,20,30', 60",
            "'1,2,4', '3,5,6', '60,10,70', 130",
            "'1,1000000000', '1000000000,1000000001', '100,200', 300",
    })
    void jobScheduling(
            @ConvertWith(IntegerArrayConverter.class) Integer[] startTime,
            @ConvertWith(IntegerArrayConverter.class) Integer[] endTime,
            @ConvertWith(IntegerArrayConverter.class) Integer[] profit,
            int expected) {
        int[] s = unBoxIntegerArray(startTime);
        int[] e = unBoxIntegerArray(endTime);
        int[] p = unBoxIntegerArray(profit);
        assertEquals(expected, new MPJobScheduling().jobScheduling(s, e, p));
        assertEquals(expected, MPJobScheduling.jobScheduling2(s, e, p));
    }
}
