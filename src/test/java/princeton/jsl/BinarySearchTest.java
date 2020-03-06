package princeton.jsl;

import junit.converter.IntegerArrayConverter;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvFileSource;
import util.IntArrayUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BinarySearchTest {

    @ParameterizedTest(name = "search {1} in {0}: {2}")
    @CsvFileSource(delimiter = ';', numLinesToSkip = 2, resources = {"/util/binary.search.position.insert.csv"})
    void testBinarySearchIndexToInsert(@ConvertWith(IntegerArrayConverter.class) Integer[] array,
                                       int target, int index) {
        int[] unboxedArray = IntArrayUtil.unBoxIntegerArray(array);
        assertEquals(index, BinarySearch.binarySearchIndexToInsert(unboxedArray, target));
    }
}
