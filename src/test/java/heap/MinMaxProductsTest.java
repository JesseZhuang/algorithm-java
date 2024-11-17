package heap;

import junit.converter.IntegerArrayConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;
import util.IntArrayUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MinMaxProductsTest {

    MinMaxProducts.Solution1 tbt1;

    @BeforeEach
    void setup() {
        tbt1 = new MinMaxProducts.Solution1();
    }

    @ParameterizedTest
    @CsvSource({
            "7,'100000,100000,100000,100000,100000,100000,100000',100000"
    })
    void test(int n, @ConvertWith(IntegerArrayConverter.class) Integer[] quantities, int exp) {
        assertEquals(exp, tbt1.minimizedMaximum(n, IntArrayUtil.unBoxIntegerArray(quantities)));
    }

}