package array;

import junit.converter.TwoDIntegerArrayConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;
import util.IntArrayUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NonOverlapIntervalsTest {

    NonOverlapIntervals tbt;

    @BeforeEach
    void setUp() {
        tbt = new NonOverlapIntervals();
    }

    @ParameterizedTest
    @CsvSource({
            "'[[1,2],[2,3],[3,4],[1,3]]', 1",
            "'[[1,2],[1,2],[1,2]]', 2",
            "'[[1,2],[2,3]]', 0",
            "'[[1,5]]', 0",
            "'[[1,4],[2,5],[3,6]]', 2",
            "'[[1,10],[2,3],[4,5],[6,7]]', 1",
            "'[[-5,-3],[-4,-1],[0,2]]', 1"
    })
    void test1(@ConvertWith(TwoDIntegerArrayConverter.class) Integer[][] intervals, int expected) {
        int[][] input = IntArrayUtil.unbox2DIntegerArray(intervals);
        assertEquals(expected, tbt.eraseOverlapIntervals1(input));
    }

    @ParameterizedTest
    @CsvSource({
            "'[[1,2],[2,3],[3,4],[1,3]]', 1",
            "'[[1,2],[1,2],[1,2]]', 2",
            "'[[1,2],[2,3]]', 0",
            "'[[1,5]]', 0",
            "'[[1,4],[2,5],[3,6]]', 2",
            "'[[1,10],[2,3],[4,5],[6,7]]', 1",
            "'[[-5,-3],[-4,-1],[0,2]]', 1"
    })
    void test2(@ConvertWith(TwoDIntegerArrayConverter.class) Integer[][] intervals, int expected) {
        int[][] input = IntArrayUtil.unbox2DIntegerArray(intervals);
        assertEquals(expected, tbt.eraseOverlapIntervals2(input));
    }

    @ParameterizedTest
    @CsvSource({
            "'[[1,2],[2,3],[3,4],[1,3]]', 1",
            "'[[1,2],[1,2],[1,2]]', 2",
            "'[[1,2],[2,3]]', 0",
            "'[[1,5]]', 0",
            "'[[1,4],[2,5],[3,6]]', 2",
            "'[[1,10],[2,3],[4,5],[6,7]]', 1",
            "'[[-5,-3],[-4,-1],[0,2]]', 1"
    })
    void test3(@ConvertWith(TwoDIntegerArrayConverter.class) Integer[][] intervals, int expected) {
        int[][] input = IntArrayUtil.unbox2DIntegerArray(intervals);
        assertEquals(expected, tbt.eraseOverlapIntervals3(input));
    }
}
