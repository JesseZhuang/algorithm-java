package bit;

import junit.converter.IntegerArrayConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;
import util.IntArrayUtil;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class MinBitwiseTest {

    private MinBitwise.Solution tbt;

    @BeforeEach
    void setUp() {
        tbt = new MinBitwise.Solution();
    }

    @ParameterizedTest
    @CsvSource({"'2,3,5,7','-1,1,4,3'", "'11,13,31','9,12,15'"})
    void test(@ConvertWith(IntegerArrayConverter.class) Integer[] input,
              @ConvertWith(IntegerArrayConverter.class) Integer[] exp) {
        assertArrayEquals(IntArrayUtil.unBoxIntegerArray(exp), tbt.minBitwiseArray(Arrays.stream(input).toList()));
    }
}