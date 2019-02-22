package hash;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvFileSource;
import util.IntegerArrayConverter;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static util.IntArrayUtil.boxIntArray;
import static util.IntArrayUtil.unBoxIntegerArray;

public class TwoSumTest {
    private static TwoSum tbt;

    @BeforeAll
    static void setup() {
        tbt = new TwoSum();
    }

    @ParameterizedTest(name = "array {0}, target {1}: indexes: {2}")
    @CsvFileSource(resources = "/TwoSum.csv", numLinesToSkip = 2, delimiter = ' ')
    void testTwoSum(@ConvertWith(IntegerArrayConverter.class) Integer[] nums,
                    int target, @ConvertWith(IntegerArrayConverter.class) Integer[] indexes) {
        int[] intArray = unBoxIntegerArray(nums);
        assertArrayEquals(indexes, boxIntArray(tbt.twoSumRadixArray(intArray, target)));
        assertArrayEquals(indexes, boxIntArray(tbt.twoSumMap(intArray, target)));
        assertArrayEquals(indexes, boxIntArray(tbt.twoSumJavaQuickSort(intArray, target)));
        assertArrayEquals(indexes, boxIntArray(tbt.twoSumMergeSort2(intArray, target)));
    }
}
