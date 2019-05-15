package array;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvFileSource;
import junit.converter.IntegerArrayConverter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static util.IntArrayUtil.unBoxIntegerArray;

public class MajorityElement2Test {
    private static MajorityElement2 toBeTested;

    @BeforeAll
    static void setup(){
        toBeTested = new MajorityElement2();
    }

    @ParameterizedTest(name = "major elements of {0} = {1}")
    @CsvFileSource(resources = {"/MajorityElement2.csv"}, delimiter = ' ', numLinesToSkip = 2)
    void testMajorityElement(@ConvertWith(IntegerArrayConverter.class) Integer[] nums,
                             @ConvertWith(IntegerArrayConverter.class) Integer[] majors) {
        int[] intArray = unBoxIntegerArray(nums);
        Set<Integer> expected = new HashSet<>(Arrays.asList(majors));
        assertEquals(expected, new HashSet<>(toBeTested.majorityElementMap(intArray)));
        assertEquals(expected, new HashSet<>(toBeTested.majorityElementBMVoting(intArray)));
    }
}
