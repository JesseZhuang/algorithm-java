package bit;

import junit.converter.IntegerArrayConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;
import util.IntArrayUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CanArrayBeSortedTest {

    CanArrayBeSorted.Solution2 tbt2;

    @BeforeEach
    void setUp() {
        tbt2 = new CanArrayBeSorted.Solution2();
    }

    @ParameterizedTest
    @CsvSource({
            "'[3,16,8,4,2]', false"
    })
    void test(@ConvertWith(IntegerArrayConverter.class) Integer[] c, boolean exp) {
        assertEquals(exp, tbt2.canSortArray(IntArrayUtil.unBoxIntegerArray(c)));
    }
}