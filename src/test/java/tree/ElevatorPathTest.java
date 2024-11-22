package tree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toCollection;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ElevatorPathTest {

    ElevatorPath.Solution tbt;

    static Stream<Arguments> testCases() {
        return Stream.of(
                Arguments.of(new ArrayList<>(List.of(2, 1, 9, 8, 7, 3)), 6, List.of(7, 8, 9, 3, 2, 1)),
                Arguments.of(new ArrayList<>(List.of(-1, 1, 9, 8, 7, 3)), 0, List.of(-1, 1, 3, 7, 8, 9)),
                Arguments.of(new ArrayList<>(List.of(1, 9, 8, 7, 3)), 0, List.of(1, 3, 7, 8, 9)),
                Arguments.of(new ArrayList<>(List.of(1, 9, 8, 7, 3)), 12, List.of(9, 8, 7, 3, 1))
        );
    }

    @BeforeEach
    void setUp() {
        tbt = new ElevatorPath.Solution();
    }

    @Test
    void testSubListReverse() {
        // list needs to be mutable, toList() returns immutable list
        List<Integer> l1 = IntStream.range(0, 6).boxed().collect(toCollection(ArrayList::new));
        List<Integer> firstTwo = l1.subList(0, 3);
        Collections.reverse(firstTwo);
        assertEquals(List.of(2, 1, 0, 3, 4, 5), l1);
        List<Integer> tmp = new ArrayList<>(firstTwo);
        assertEquals(List.of(2, 1, 0), tmp);
    }

    @ParameterizedTest
    @MethodSource("testCases")
    void test(List<Integer> floors, int start, List<Integer> exp) {
        assertEquals(exp, tbt.closestFloors(floors, start));
    }
}