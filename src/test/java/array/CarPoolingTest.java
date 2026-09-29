package array;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CarPoolingTest {

    static Stream<Arguments> cases() {
        return Stream.of(
            Arguments.of(new int[][]{{2,1,5},{3,3,7}}, 4, false),  // stop 3: 2+3=5>4
            Arguments.of(new int[][]{{2,1,5},{3,3,7}}, 5, true),
            Arguments.of(new int[][]{{2,1,5},{3,5,7}}, 3, true),   // no overlap
            Arguments.of(new int[][]{{3,2,7}}, 3, true),
            Arguments.of(new int[][]{{3,2,7}}, 2, false),
            Arguments.of(new int[][]{{5,0,3},{5,3,6}}, 5, true),   // sequential
            Arguments.of(new int[][]{{2,0,5},{3,0,5}}, 5, true),   // exact capacity
            Arguments.of(new int[][]{{2,0,5},{3,0,5}}, 4, false),
            Arguments.of(new int[][]{{3,0,2},{3,2,4}}, 3, true),   // drop at 2, pick at 2
            Arguments.of(new int[][]{}, 1, true)                    // empty
        );
    }

    @ParameterizedTest
    @MethodSource("cases")
    void testCarPooling(int[][] trips, int capacity, boolean expected) {
        assertEquals(expected, CarPooling.carPooling(trips, capacity));
    }

    @ParameterizedTest
    @MethodSource("cases")
    void testCarPooling2(int[][] trips, int capacity, boolean expected) {
        assertEquals(expected, CarPooling.carPooling2(trips, capacity));
    }
}
