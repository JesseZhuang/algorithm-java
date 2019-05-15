package array;

import junit.converter.IntegerArrayConverter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvFileSource;
import util.IntArrayUtil;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FindDuplicateSortedListsTest {
    static FindDuplicateSortedLists tbt;

    @BeforeAll
    static void setup() {
        tbt = new FindDuplicateSortedLists();
    }

    @ParameterizedTest(name = "duplicates for {0} and {1}: {2}")
    @CsvFileSource(resources = {"/FindDuplicateSortedLists.csv"}, delimiter = ';', numLinesToSkip = 2)
    void testSearch(@ConvertWith(IntegerArrayConverter.class) Integer[] array1,
                    @ConvertWith(IntegerArrayConverter.class) Integer[] array2,
                    @ConvertWith(IntegerArrayConverter.class) Integer[] duplicates) {
        int[] ua1 = IntArrayUtil.unBoxIntegerArray(array1);
        int[] ua2 = IntArrayUtil.unBoxIntegerArray(array2);
        assertEquals(Arrays.asList(duplicates), tbt.commonElementsLinearSearch(ua1, ua2));
        assertEquals(Arrays.asList(duplicates), tbt.commonElementsBinarySearch(ua1, ua2));
    }
}
