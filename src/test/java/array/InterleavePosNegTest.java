package array;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvFileSource;
import junit.converter.IntegerArrayConverter;

import java.util.Arrays;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static util.IntArrayUtil.unBoxIntegerArray;

public class InterleavePosNegTest {
    private InterleavePosNeg interleavePosNeg;

    @BeforeEach
    void setup() {
        interleavePosNeg = new InterleavePosNeg();
    }

    @ParameterizedTest(name = "rearrange: {0}\n")
    @CsvFileSource(resources = {"/InterleavePosNeg.csv"}, delimiter = ';', numLinesToSkip = 2)
    void testRearrange(@ConvertWith(IntegerArrayConverter.class) Integer[] nums) {
        test(interleavePosNeg::rearrangeTwoPointer, unBoxIntegerArray(nums));
        test(interleavePosNeg::rearrangeAuxArray, unBoxIntegerArray(nums));
        int[] n = unBoxIntegerArray(nums);
        test(interleavePosNeg::rearrangeQuick, n);
        System.out.println(Arrays.toString(n));
    }

    private void test(Consumer<int[]> interleaveMethod, int[] n) {
        interleaveMethod.accept(n);
        for(int i = 1; i < n.length; i++) assertTrue(n[i] * n[i-1] < 0);
    }
}
