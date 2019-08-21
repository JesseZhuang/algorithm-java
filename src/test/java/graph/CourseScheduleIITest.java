package graph;

import junit.converter.TwoDIntegerArrayConverter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
        boolean foundOnePath = false;
        for (Integer[] expected : orders) if (Arrays.deepEquals(expected, actual)) foundOnePath = true;
        if (!foundOnePath)
            fail("actual topological order DFS " + Arrays.toString(actual) + " not in " + Arrays.toString(orders));

        actual = tbt.findOrderBFS(numCourses, prerequisites);
        foundOnePath = false;
        for (Integer[] expected : orders) if (Arrays.deepEquals(expected, actual)) foundOnePath = true;
        if (!foundOnePath)
            fail("actual topological order BFS " + Arrays.toString(actual) + " not in " + Arrays.toString(orders));

        actual = tbt.findOrderDFS2(numCourses, prerequisites);
        foundOnePath = false;
        for (Integer[] expected : orders) if (Arrays.deepEquals(expected, actual)) foundOnePath = true;
        if (!foundOnePath)
            fail("actual topological order DFS2 " + Arrays.toString(actual) + " not in " + Arrays.toString(orders));

        actual = tbt.findOrderBFS2(numCourses, prerequisites);
        foundOnePath = false;
        for (Integer[] expected : orders) if (Arrays.deepEquals(expected, actual)) foundOnePath = true;
        if (!foundOnePath)
            fail("actual topological order BFS2 " + Arrays.toString(actual) + " not in " + Arrays.toString(orders));

        System.out.println("finished testing all methods for this case.");
    }

}
