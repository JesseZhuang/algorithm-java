package array;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvFileSource;
import util.IntegerArrayConverter;

import java.util.Arrays;

public class MoveZeroesTest {
    private static MoveZeroes tbt;

    @BeforeAll
    static void setup() {
        tbt = new MoveZeroes();
    }

    @ParameterizedTest(name = "rearrange 0 of {0} = {1}")
    @CsvFileSource(resources = "/MoveZeroes.csv", delimiter = ' ', numLinesToSkip = 2)
    void testMoveZeroes(@ConvertWith(IntegerArrayConverter.class) Integer[] nums,
                        @ConvertWith(IntegerArrayConverter.class) Integer[] rearranged) {
        int[] unBoxedExpected = IntegerArrayConverter.unBoxIntegerArray(rearranged);
        int[] copy = makeCopy(nums);
        tbt.moveZeroesRec(copy);
        Assertions.assertArrayEquals(unBoxedExpected, copy);
        copy = makeCopy(nums);
        tbt.moveZeroesIter1(copy);
        Assertions.assertArrayEquals(unBoxedExpected, copy);
        copy = makeCopy(nums);
        tbt.moveZeroesIter2(copy);
        Assertions.assertArrayEquals(unBoxedExpected, copy);
        copy = makeCopy(nums);
        tbt.moveZeroesIter3(copy);
        Assertions.assertArrayEquals(unBoxedExpected, copy);
    }

    private int[] makeCopy(Integer[] nums) {
        return nums == null? null:IntegerArrayConverter.unBoxIntegerArray(Arrays.copyOf(nums, nums.length));
    }
}
