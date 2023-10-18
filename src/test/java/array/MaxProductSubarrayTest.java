package array;

import junit.converter.IntegerArrayConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static util.IntArrayUtil.unBoxIntegerArray;

public class MaxProductSubarrayTest {
    private MaxProductSubarray tbt;

    @BeforeEach
    void setup() {
        tbt = new MaxProductSubarray();
    }

    @ParameterizedTest(name = "max product of {0}: {1}")
    @CsvFileSource(resources = {"/MaxProductSubarray.csv"}, delimiter = ' ', numLinesToSkip = 2)
    void testMaxProduct(@ConvertWith(IntegerArrayConverter.class) Integer[] nums, int max) {
        int[] intArray = unBoxIntegerArray(nums);
        assertEquals(max, tbt.maxProduct(intArray));
    }

}
