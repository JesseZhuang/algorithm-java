package graph;

import junit.converter.TwoDIntegerArrayConverter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvFileSource;
import util.IntArrayUtil;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.fail;

class CourseScheduleIITest {
    static CourseScheduleII.SolutionAlgs4 tbt;
    static CourseScheduleII.Solution tbt2;

    @BeforeAll
    static void setup() {
        tbt = new CourseScheduleII.SolutionAlgs4();
        tbt2 = new CourseScheduleII.Solution();
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

        int[][] edges = IntArrayUtil.unbox2DIntegerArray(prerequisites);
        actual = IntArrayUtil.boxIntArray(tbt2.findOrder(numCourses, edges));
        foundOnePath = false;
        for (Integer[] expected : orders) if (Arrays.deepEquals(expected, actual)) foundOnePath = true;
        if (!foundOnePath)
            fail("actual topological order" + Arrays.toString(actual) + " not in " + Arrays.toString(orders));

    }

}
