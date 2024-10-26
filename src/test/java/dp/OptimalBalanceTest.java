package dp;

import junit.converter.TwoDIntegerArrayConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;
import util.IntArrayUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OptimalBalanceTest {

    OptimalBalance.Solution1 tbt1;

    @BeforeEach
    void setUp() {
        tbt1 = new OptimalBalance.Solution1();
    }

    @ParameterizedTest
    @CsvSource({"'[[0,1,10],[1,2,15],[2,3,10]]', 2", "'[[0,1,100],[0,2,100],[1,2,1],[1,3,100],[2,3,100]]',2",
            "'[[0,1,10],[2,0,5]]', 2", "'[[0,1,10],[1,0,1],[1,2,5],[2,0,5]]', 1"})
    void test(@ConvertWith(TwoDIntegerArrayConverter.class) Integer[][] input, int expected) {
        int[][] transactions = IntArrayUtil.unbox2DIntegerArray(input);
        assertEquals(expected, tbt1.minTransfers(transactions));
    }
}