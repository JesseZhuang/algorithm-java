package string;

import junit.converter.IntegerArrayConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;
import util.IntArrayUtil;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class LexMinValidSeqTest {

    LexMinValidSeq.Solution tbt;

    @BeforeEach
    void setUp() {
        tbt = new LexMinValidSeq.Solution();
    }

    @ParameterizedTest
    @CsvSource({
            "bacdc, abc, '[1,2,4]'",
            "vbcca, abc, '0,1,2'",
            "aaaaaa, aaabc, ''",
            "abc, ab, '0,1'",
            "aac, aab, '0,1,2'"
    })
    void test(String a, String b, @ConvertWith(IntegerArrayConverter.class) Integer[] expected) {
        assertArrayEquals(IntArrayUtil.unBoxIntegerArray(expected), tbt.validSequence(a, b));
    }
}