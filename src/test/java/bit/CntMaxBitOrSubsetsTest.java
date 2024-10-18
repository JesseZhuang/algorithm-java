package bit;

import junit.converter.IntegerArrayConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;
import util.IntArrayUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CntMaxBitOrSubsetsTest {

    CntMaxBitOrSubsets.Solution tbt;

    @BeforeEach
    void setUp() {
        tbt = new CntMaxBitOrSubsets.Solution();
    }

    @ParameterizedTest
    @CsvSource({"'3,2,1,5',6", "'2,2,2',7", "'3,1',2"})
    void test(@ConvertWith(IntegerArrayConverter.class) Integer[] input, int expected) {
        assertEquals(expected, tbt.countMaxOrSubsets(IntArrayUtil.unBoxIntegerArray(input)));
    }
}