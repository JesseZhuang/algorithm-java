package array;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvFileSource;
import util.IntegerArrayConverter;

import java.util.Arrays;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class InterleavePosNeg2Test {
    private static InterleavePosNeg2 interleavePosNeg2;

    @BeforeAll
    static void setup() {
        interleavePosNeg2 = new InterleavePosNeg2();
    }

    @ParameterizedTest(name = "rearrange: {0}\n")
    @CsvFileSource(resources = {"/InterleavePosNeg2.csv"}, delimiter = ';', numLinesToSkip = 2)
    void testRearrange(@ConvertWith(IntegerArrayConverter.class) Integer[] nums) {
        test(interleavePosNeg2::rearrangeStableInPlace, IntegerArrayConverter.unBoxIntegerArray(nums));
        int[] n = IntegerArrayConverter.unBoxIntegerArray(nums);
        test(interleavePosNeg2::rearrangeModulus, n);
        System.out.println(Arrays.toString(n));
    }

    private void test(Consumer<int[]> interleaveMethod, int[] n) {
        interleaveMethod.accept(n);
        assertTrue(n[0] > 0);
        boolean stillInterleaving = true;
        for (int i = 1; i < n.length; i++) {
            if (n[i] * n[i - 1] > 0) {
                if (stillInterleaving) stillInterleaving = false;
            } else assertTrue(stillInterleaving);
        }
    }
}
