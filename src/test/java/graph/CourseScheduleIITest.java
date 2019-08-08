package graph;

import junit.converter.TwoDIntegerArrayConverter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.fail;

class CourseScheduleIITest {
    static CourseScheduleII tbt;

    @BeforeAll
    static void setup() {
        tbt = new CourseScheduleII();
    }

    @ParameterizedTest
    @CsvFileSource(resources = {"/CourseScheduleII.csv"}, delimiter = ';', numLinesToSkip = 2)
    void testFindOrder(int numCourses,
                       @ConvertWith(TwoDIntegerArrayConverter.class) Integer[][] prerequisites,
                       @ConvertWith(TwoDIntegerArrayConverter.class) Integer[][] orders) {
        Integer[] actual = tbt.findOrderDFS(numCourses, prerequisites);
        for (Integer[] expected : orders) if (Arrays.deepEquals(expected, actual)) return;
        fail("actual topological order DFS " + Arrays.toString(actual) + " not in " + Arrays.toString(orders));

        actual = tbt.findOrderBFS(numCourses, prerequisites);
        for (Integer[] expected : orders) if (Arrays.deepEquals(expected, actual)) return;
        fail("actual topological order BFS " + Arrays.toString(actual) + " not in " + Arrays.toString(orders));

        actual = tbt.findOrderDFS2(numCourses, prerequisites);
        for (Integer[] expected : orders) if (Arrays.deepEquals(expected, actual)) return;
        fail("actual topological order DFS2 " + Arrays.toString(actual) + " not in " + Arrays.toString(orders));

        actual = tbt.findOrderBFS2(numCourses, prerequisites);
        for (Integer[] expected : orders) if (Arrays.deepEquals(expected, actual)) return;
        fail("actual topological order BFS2 " + Arrays.toString(actual) + " not in " + Arrays.toString(orders));
    }

}
