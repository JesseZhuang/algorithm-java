package string;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvFileSource;
import util.IntArrayUtil;
import util.IntegerArrayConverter;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class KeyIndexedCountingTest {
    private static KeyIndexedCounting tbt;

    @ParameterizedTest(name = "{0} sorted = {2}")
    @CsvFileSource(resources = {"/KeyIndexedCounting.csv"}, delimiter = ';', numLinesToSkip = 2)
    void testSort(@ConvertWith(IntegerArrayConverter.class) Integer[] a, int R,
                  @ConvertWith(IntegerArrayConverter.class) Integer[] sorted) {
        int[] input = IntArrayUtil.unBoxIntegerArray(a);
        tbt.sort(input, R);
        assertArrayEquals(IntArrayUtil.unBoxIntegerArray(sorted), input);
    }
}
