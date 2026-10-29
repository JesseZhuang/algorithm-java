package queue;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NumberOfStudentsUnableToEatLunchTest {

    static Stream<Arguments> cases() {
        return Stream.of(
                Arguments.of(new int[]{1, 1, 0, 0}, new int[]{0, 1, 0, 1}, 0),
                Arguments.of(new int[]{1, 1, 1, 0, 0, 1}, new int[]{1, 0, 0, 0, 1, 1}, 3),
                Arguments.of(new int[]{0, 0, 0}, new int[]{0, 0, 0}, 0),
                Arguments.of(new int[]{1, 1}, new int[]{1, 1}, 0),
                Arguments.of(new int[]{0, 0, 0}, new int[]{1, 0, 0}, 3),
                Arguments.of(new int[]{0, 1, 0, 1}, new int[]{0, 1, 1, 1}, 1),
                Arguments.of(new int[]{0}, new int[]{0}, 0),
                Arguments.of(new int[]{0}, new int[]{1}, 1),
                Arguments.of(new int[]{1, 1, 0}, new int[]{0, 1, 1}, 0)
        );
    }

    @ParameterizedTest
    @MethodSource("cases")
    void countsRemainingStudents(int[] students, int[] sandwiches, int expected) {
        assertEquals(expected, NumberOfStudentsUnableToEatLunch.countStudents(students, sandwiches));
    }

    @ParameterizedTest
    @MethodSource("cases")
    void simulatesQueueRotations(int[] students, int[] sandwiches, int expected) {
        assertEquals(expected, NumberOfStudentsUnableToEatLunch.countStudentsQueue(students, sandwiches));
    }
}
