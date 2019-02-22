package array;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvFileSource;
import util.IntegerArrayConverter;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static util.IntArrayUtil.unBoxIntegerArray;

public class MergeSortedArrayTest {
    private static MergeSortedArray toBeTested;

    @BeforeAll
    static void setup() {
        toBeTested = new MergeSortedArray();
    }

    @ParameterizedTest(name = "merge {0} and {2} = {4}")
    @CsvFileSource(resources = {"/MergeSortedArray.csv"}, delimiter = ' ', numLinesToSkip = 2)
    void testMajorityElement(@ConvertWith(IntegerArrayConverter.class) Integer[] nums1,
                             int m,
                             @ConvertWith(IntegerArrayConverter.class) Integer[] nums2,
                             int n,
                             @ConvertWith(IntegerArrayConverter.class) Integer[] merged) {
        int[] unboxedNums1 = unBoxIntegerArray(nums1);
        int[] unboxedNums2 = unBoxIntegerArray(nums2);
        int[] unboxedMerged = unBoxIntegerArray(merged);
        toBeTested.merge1(unboxedNums1, m, unboxedNums2, n);
        assertArrayEquals(unboxedMerged, unboxedNums1);

        unboxedNums1 = unBoxIntegerArray(nums1);
        unboxedNums2 = unBoxIntegerArray(nums2);
        toBeTested.merge2(unboxedNums1, m, unboxedNums2, n);
        assertArrayEquals(unboxedMerged, unboxedNums1);
    }
}
