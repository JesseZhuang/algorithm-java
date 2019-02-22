package array;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvFileSource;
import util.IntegerArrayConverter;

import static util.IntArrayUtil.unBoxIntegerArray;

public class MajorityElementTest {
    private static MajorityElement toBeTested;

    @BeforeAll
    static void setup() {
        toBeTested = new MajorityElement();
    }

    @ParameterizedTest(name = "major element of {0} = {1}")
    @CsvFileSource(resources = {"/MajorityElement.csv"}, delimiter = ' ', numLinesToSkip = 2)
    void testMajorityElement(@ConvertWith(IntegerArrayConverter.class) Integer[] nums, int major) {
        int[] intArray = unBoxIntegerArray(nums);
        Assertions.assertEquals(toBeTested.majorityElementBMVoting1(intArray), major);
        Assertions.assertEquals(toBeTested.majorityElementBMVoting2(intArray), major);
        Assertions.assertEquals(toBeTested.majorityElementMap1(intArray), major);
        Assertions.assertEquals(toBeTested.majorityElementMap2(intArray), major);
        Assertions.assertEquals(toBeTested.majorityElementMap3(intArray), major);
        Assertions.assertEquals(toBeTested.majorityElementSort(intArray), major);
        Assertions.assertEquals(toBeTested.majorityElementRecursive(intArray), major);
        Assertions.assertEquals(toBeTested.majorityElementRandom(intArray), major);
    }
}
