package heap;

import junit.converter.IntegerArrayConverter;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;
import util.IntArrayUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;

class KthLargestElementTest {

    @ParameterizedTest
    @CsvSource({
            "'3,2,1,5,6,4', 2, 5",
            "'3,2,3,1,2,4,5,5,6', 4, 4",
            "'1', 1, 1",
            "'7,7,7,7', 2, 7",
            "'1,2,3,4,5', 1, 5",
            "'5,4,3,2,1', 5, 1",
            "'-1,-2,-3,-4', 2, -2",
            "'-1,2,0', 1, 2",
            "'3,1,2', 3, 1",
    })
    void testHeap(@ConvertWith(IntegerArrayConverter.class) Integer[] nums, int k, int expected) {
        assertEquals(expected, KthLargestElement.findKthLargestHeap(IntArrayUtil.unBoxIntegerArray(nums), k));
    }

    @ParameterizedTest
    @CsvSource({
            "'3,2,1,5,6,4', 2, 5",
            "'3,2,3,1,2,4,5,5,6', 4, 4",
            "'1', 1, 1",
            "'7,7,7,7', 2, 7",
            "'1,2,3,4,5', 1, 5",
            "'5,4,3,2,1', 5, 1",
            "'-1,-2,-3,-4', 2, -2",
            "'-1,2,0', 1, 2",
            "'3,1,2', 3, 1",
    })
    void testQuickSelect(@ConvertWith(IntegerArrayConverter.class) Integer[] nums, int k, int expected) {
        assertEquals(expected, KthLargestElement.findKthLargestQuickSelect(IntArrayUtil.unBoxIntegerArray(nums), k));
    }
}
