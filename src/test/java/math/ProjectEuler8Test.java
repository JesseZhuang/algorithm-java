package math;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvFileSource;
import util.IntegerArrayConverter;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static util.IntArrayUtil.unBoxIntegerArray;

class ProjectEuler8Test {

    @ParameterizedTest(name = "{0}: {1} (maxProduct, startingIndex)")
    @CsvFileSource(resources = {"/ProjectEuler8-4.csv"}, delimiter = ' ', numLinesToSkip = 2)
    void testFindMaxProduct(String number, @ConvertWith(IntegerArrayConverter.class) Integer[] result) {
        assertArrayEquals(ProjectEuler8.findMaxProduct4Naive(number), unBoxIntegerArray(result));
    }
}
