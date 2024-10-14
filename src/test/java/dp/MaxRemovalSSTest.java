package dp;

import junit.converter.IntegerArrayConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;
import util.IntArrayUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MaxRemovalSSTest {

    MaxRemovalSS.Solution tbt;

    @BeforeEach
    void setUp() {
        tbt = new MaxRemovalSS.Solution();
    }

    @ParameterizedTest
    @CsvSource({"abbaabba, aba, '0,1,2', 3", "abbaa, aba, '0,1,2', 1", "bcda, d, '0,3', 2", "dda, dda, '0,1,2', 0",
            "yeyeykyded, yeyyd, '0,2,3,4', 2"})
    void test(String s, String p, @ConvertWith(IntegerArrayConverter.class) Integer[] targetIndices, int expected) {
        assertEquals(expected, tbt.maxRemovals(s, p, IntArrayUtil.unBoxIntegerArray(targetIndices)));
    }
}