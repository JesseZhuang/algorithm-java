package hash;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvFileSource;
import util.IntegerArrayConverter;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ContainsDuplicateIITest {
    private static ContainsDuplicateII containsDuplicateII;

    @BeforeAll
    static void setup() {
        containsDuplicateII = new ContainsDuplicateII();
    }

    @ParameterizedTest(name = "contains duplicate in {0} = {2} that are less than {1} positions apart")
    @CsvFileSource(resources = {"/ContainsDuplicateII.csv"}, delimiter = ' ', numLinesToSkip = 2)
    void testContainsDuplicate(@ConvertWith(IntegerArrayConverter.class) Integer[] array, int positionDifference,
                               boolean duplicateExists) {
        int[] intArray = IntegerArrayConverter.unBoxIntegerArray(array);
        assertEquals(containsDuplicateII.containsNearbyDuplicateMap(intArray, positionDifference), duplicateExists);
        assertEquals(containsDuplicateII.containsNearbyDuplicateSet(intArray, positionDifference), duplicateExists);
    }
}
