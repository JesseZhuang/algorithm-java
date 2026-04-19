package array;

import junit.converter.IntegerArrayConverter;
import junit.converter.TwoDIntegerArrayConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;
import util.IntArrayUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ThreeSumTest {

    ThreeSum.Solution1 tbt1;
    ThreeSum.Solution2 tbt2;

    @BeforeEach
    void setUp() {
        tbt1 = new ThreeSum.Solution1();
        tbt2 = new ThreeSum.Solution2();
    }

    @ParameterizedTest(name = "threeSum({0}) = {1}")
    @CsvSource({
            "'[-1,0,1,2,-1,-4]', '[[-1,-1,2],[-1,0,1]]'",
            "'[0,1,1]', '[[]]'",
            "'[0,0,0]', '[[0,0,0]]'",
            "'[1,2,3]', '[[]]'",
            "'[0,0,0,0]', '[[0,0,0]]'",
            "'[-1,-2,-3]', '[[]]'",
            "'[-2,0,1,1,2]', '[[-2,0,2],[-2,1,1]]'",
            "'[-1,-1,-1,0,1,1,1]', '[[-1,0,1]]'",
    })
    void test(@ConvertWith(IntegerArrayConverter.class) Integer[] nums,
              @ConvertWith(TwoDIntegerArrayConverter.class) Integer[][] expected) {
        int[] input = IntArrayUtil.unBoxIntegerArray(nums);
        List<List<Integer>> expectedList = toListOfLists(expected);

        int[] copy1 = Arrays.copyOf(input, input.length);
        int[] copy2 = Arrays.copyOf(input, input.length);
        assertEquals(sorted(expectedList), sorted(tbt1.threeSum(copy1)));
        assertEquals(sorted(expectedList), sorted(tbt2.threeSum(copy2)));
    }

    /** Sort list of triplets lexicographically for order-independent comparison. */
    private List<List<Integer>> sorted(List<List<Integer>> lists) {
        List<List<Integer>> copy = new ArrayList<>(lists);
        copy.sort(Comparator.<List<Integer>, Integer>comparing(l -> l.get(0))
                .thenComparing(l -> l.get(1))
                .thenComparing(l -> l.get(2)));
        return copy;
    }

    private List<List<Integer>> toListOfLists(Integer[][] arr) {
        List<List<Integer>> result = new ArrayList<>();
        for (Integer[] row : arr) {
            if (row.length == 0) continue; // empty row means no triplets
            result.add(Arrays.stream(row).collect(Collectors.toList()));
        }
        return result;
    }
}
