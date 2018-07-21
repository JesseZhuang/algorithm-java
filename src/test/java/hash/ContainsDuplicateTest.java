package hash;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvFileSource;
import util.IntegerArrayConverter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContainsDuplicateTest {
    private static ContainsDuplicate containsDuplicate;

    @BeforeAll
    static void setup() {
        containsDuplicate = new ContainsDuplicate();
    }

    @ParameterizedTest(name = "contains duplicate in {0} = {1}")
    @CsvFileSource(resources = {"/ContainsDuplicate.csv"}, delimiter = ' ', numLinesToSkip = 2)
    void testContainsDuplicate(@ConvertWith(IntegerArrayConverter.class) Integer[] array, boolean duplicateExists) {
        int[] intArray = IntegerArrayConverter.unboxIntegerArray(array);
        assertEquals(containsDuplicate.conTainsDuplicate(intArray), duplicateExists);
        assertEquals(containsDuplicate.containsDuplicate1(intArray), duplicateExists);
        assertEquals(containsDuplicate.containsDuplicate2(intArray), duplicateExists);
        assertEquals(containsDuplicate.containsDuplicate4(intArray), duplicateExists);
        assertEquals(containsDuplicate.containsDuplicateSet(intArray), duplicateExists);
    }
}
