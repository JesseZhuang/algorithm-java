package heap;

import junit.converter.IntegerArrayConverter;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;
import util.IntArrayUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LastStoneWeightTest {

    @ParameterizedTest
    @CsvSource({
            "'2,7,4,1,8,1', 1",
            "'1', 1",
            "'3,3', 0",
            "'3,7', 4",
            "'5,5,5,5', 0",
            "'5,5,5', 5",
            "'10,4,2,10', 2",
            "'42', 42",
            "'1000,999', 1",
            "'100,1,1,1,1', 96",
    })
    void testHeap(@ConvertWith(IntegerArrayConverter.class) Integer[] stones, int expected) {
        assertEquals(expected, LastStoneWeight.lastStoneWeightHeap(IntArrayUtil.unBoxIntegerArray(stones)));
    }

    @ParameterizedTest
    @CsvSource({
            "'2,7,4,1,8,1', 1",
            "'1', 1",
            "'3,3', 0",
            "'3,7', 4",
            "'5,5,5,5', 0",
            "'5,5,5', 5",
            "'10,4,2,10', 2",
            "'42', 42",
            "'1000,999', 1",
            "'100,1,1,1,1', 96",
    })
    void testSort(@ConvertWith(IntegerArrayConverter.class) Integer[] stones, int expected) {
        assertEquals(expected, LastStoneWeight.lastStoneWeightSort(IntArrayUtil.unBoxIntegerArray(stones)));
    }
}
