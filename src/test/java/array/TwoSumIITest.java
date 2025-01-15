package array;

import junit.converter.IntegerArrayConverter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static util.IntArrayUtil.boxIntArray;
import static util.IntArrayUtil.unBoxIntegerArray;

public class TwoSumIITest {
    private static TwoSumII tbt;

    @BeforeAll
    static void setup() {
        tbt = new TwoSumII();
    }

    @ParameterizedTest(name = "array {0} target {1} : indexes {2}")
    @CsvFileSource(resources = "/TwoSum2.csv", numLinesToSkip = 2, delimiter = ' ')
    void testTwoSum(@ConvertWith(IntegerArrayConverter.class) Integer[] nums,
                    int target, @ConvertWith(IntegerArrayConverter.class) Integer[] indexes) {
        int[] intArray = unBoxIntegerArray(nums);
        assertArrayEquals(indexes, boxIntArray(tbt.twoSum2P(intArray, target)));
    }
}
