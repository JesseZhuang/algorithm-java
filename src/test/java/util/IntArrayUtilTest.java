package util;

import junit.converter.IntegerArrayConverter;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IntArrayUtilTest {

    @ParameterizedTest(name = "search {1} in {0}: {2}")
    @CsvFileSource(delimiter = ';', numLinesToSkip = 2, resources = {"/util/IntArrayUtil.binary.search.position.insert.csv"})
    void testBinarySearchIndexToInsert(@ConvertWith(IntegerArrayConverter.class) Integer[] array,
                                       int target, int index) {
        int[] unboxedArray = IntArrayUtil.unBoxIntegerArray(array);
        assertEquals(index, IntArrayUtil.binarySearchIndexToInsert(unboxedArray, target));
    }
}
