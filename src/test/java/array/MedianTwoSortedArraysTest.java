package array;

import junit.converter.IntegerArrayConverter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvFileSource;
import util.IntArrayUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MedianTwoSortedArraysTest {
    static MedianTwoSortedArrays tbt;

    @BeforeAll
    static void setup() {
        tbt = new MedianTwoSortedArrays();
    }

    @ParameterizedTest
    @CsvFileSource(resources = {"/MedianTwoSortedArrays.csv"}, numLinesToSkip = 2, delimiter = ';')
    void testMedian(@ConvertWith(IntegerArrayConverter.class) Integer[] a1,
                    @ConvertWith(IntegerArrayConverter.class) Integer[] a2,
                    double median) {
        int[] nums1 = IntArrayUtil.unBoxIntegerArray(a1);
        int[] nums2 = IntArrayUtil.unBoxIntegerArray(a2);
        assertEquals(median, tbt.findMedianSortedArrays(nums1, nums2));
    }
}
