package graph;

import junit.converter.TwoDIntegerArrayConverter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvFileSource;
import util.IntArrayUtil;

import java.util.ArrayDeque;
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

    @Test
    void testQueueSizeEvaluation() {
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.add(0);
        int k = 3;
        while (!q.isEmpty() && k-- > 0) {
            System.out.println("start polling from the q");
            for (int i = 0; i < q.size(); i++) { // q.size() evaluated dynamically for every iteration
                if (q.size() > 5) return;
                System.out.println("size: " + q.size() + " i: " + i);
                System.out.println(q.removeFirst());
                q.add(i * 10 + 1);
                q.add(i * 10 + 2);
            }
        }
    }

}
