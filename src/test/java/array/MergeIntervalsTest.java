package array;

import junit.converter.TwoDIntegerArrayConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;
import util.IntArrayUtil;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class MergeIntervalsTest {

    MergeIntervals.Solution tbt;

    @BeforeEach
    void setUp() {
        tbt = new MergeIntervals.Solution();
    }

    @ParameterizedTest
    @CsvSource({
            "'[[1,3],[2,6],[8,10],[15,18]]', '[[1,6],[8,10],[15,18]]'",
            "'[[1,4],[4,5]]', '[[1,5]]'",
            "'[[1,1]]', '[[1,1]]'",
            "'[[1,2],[4,5],[7,8]]', '[[1,2],[4,5],[7,8]]'",
            "'[[1,10],[2,3],[4,5],[6,7]]', '[[1,10]]'",
            "'[[3,4],[1,2],[5,6],[2,3]]', '[[1,4],[5,6]]'",
            "'[[1,5],[2,3]]', '[[1,5]]'",
            "'[[1,2],[2,3],[3,4]]', '[[1,4]]'"
    })
    void test(@ConvertWith(TwoDIntegerArrayConverter.class) Integer[][] intervals,
              @ConvertWith(TwoDIntegerArrayConverter.class) Integer[][] expected) {
        int[][] input = IntArrayUtil.unbox2DIntegerArray(intervals);
        int[][] exp = IntArrayUtil.unbox2DIntegerArray(expected);
        assertArrayEquals(exp, tbt.merge(input));
    }
}
