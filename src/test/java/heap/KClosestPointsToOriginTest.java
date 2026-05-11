package heap;

import junit.converter.TwoDIntegerArrayConverter;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;
import util.IntArrayUtil;

import java.util.Arrays;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;

class KClosestPointsToOriginTest {

    @ParameterizedTest
    @CsvSource({
            "'[[1,3],[-2,2]]', 1, '[[-2,2]]'",
            "'[[3,3],[5,-1],[-2,4]]', 2, '[[-2,4],[3,3]]'",
            "'[[0,1],[1,0]]', 2, '[[0,1],[1,0]]'",
            "'[[1,1]]', 1, '[[1,1]]'",
    })
    void testHeap(@ConvertWith(TwoDIntegerArrayConverter.class) Integer[][] points, int k,
                  @ConvertWith(TwoDIntegerArrayConverter.class) Integer[][] expected) {
        int[][] result = KClosestPointsToOrigin.kClosestHeap(IntArrayUtil.unbox2DIntegerArray(points), k);
        assertEqualsSorted(IntArrayUtil.unbox2DIntegerArray(expected), result);
    }

    @ParameterizedTest
    @CsvSource({
            "'[[1,3],[-2,2]]', 1, '[[-2,2]]'",
            "'[[3,3],[5,-1],[-2,4]]', 2, '[[-2,4],[3,3]]'",
            "'[[0,1],[1,0]]', 2, '[[0,1],[1,0]]'",
            "'[[1,1]]', 1, '[[1,1]]'",
    })
    void testQuickSelect(@ConvertWith(TwoDIntegerArrayConverter.class) Integer[][] points, int k,
                         @ConvertWith(TwoDIntegerArrayConverter.class) Integer[][] expected) {
        int[][] result = KClosestPointsToOrigin.kClosestQuickSelect(IntArrayUtil.unbox2DIntegerArray(points), k);
        assertEqualsSorted(IntArrayUtil.unbox2DIntegerArray(expected), result);
    }

    private void assertEqualsSorted(int[][] expected, int[][] actual) {
        Comparator<int[]> cmp = (a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1];
        Arrays.sort(expected, cmp);
        Arrays.sort(actual, cmp);
        assertEquals(Arrays.deepToString(expected), Arrays.deepToString(actual));
    }
}
