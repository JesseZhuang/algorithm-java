package graph;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class EvaluateDivisionTest {

    private static final double EPSILON = 1e-5;

    @Test
    void testExample1() {
        List<List<String>> equations = Arrays.asList(
                Arrays.asList("a", "b"), Arrays.asList("b", "c"));
        double[] values = {2.0, 3.0};
        List<List<String>> queries = Arrays.asList(
                Arrays.asList("a", "c"), Arrays.asList("b", "a"),
                Arrays.asList("a", "e"), Arrays.asList("a", "a"),
                Arrays.asList("x", "x"));
        double[] expected = {6.0, 0.5, -1.0, 1.0, -1.0};

        assertArrayEquals(expected, EvaluateDivision.calcEquation(equations, values, queries), EPSILON);
        assertArrayEquals(expected, EvaluateDivision.calcEquation2(equations, values, queries), EPSILON);
    }

    @Test
    void testExample2() {
        List<List<String>> equations = Arrays.asList(
                Arrays.asList("a", "b"), Arrays.asList("b", "c"),
                Arrays.asList("bc", "cd"));
        double[] values = {1.5, 2.5, 5.0};
        List<List<String>> queries = Arrays.asList(
                Arrays.asList("a", "c"), Arrays.asList("c", "b"),
                Arrays.asList("bc", "cd"), Arrays.asList("cd", "bc"));
        double[] expected = {3.75, 0.4, 5.0, 0.2};

        assertArrayEquals(expected, EvaluateDivision.calcEquation(equations, values, queries), EPSILON);
        assertArrayEquals(expected, EvaluateDivision.calcEquation2(equations, values, queries), EPSILON);
    }

    @Test
    void testExample3() {
        List<List<String>> equations = Arrays.asList(Arrays.asList("a", "b"));
        double[] values = {0.5};
        List<List<String>> queries = Arrays.asList(
                Arrays.asList("a", "b"), Arrays.asList("b", "a"),
                Arrays.asList("a", "c"), Arrays.asList("x", "y"));
        double[] expected = {0.5, 2.0, -1.0, -1.0};

        assertArrayEquals(expected, EvaluateDivision.calcEquation(equations, values, queries), EPSILON);
        assertArrayEquals(expected, EvaluateDivision.calcEquation2(equations, values, queries), EPSILON);
    }

    @Test
    void testDisconnected() {
        List<List<String>> equations = Arrays.asList(
                Arrays.asList("a", "b"), Arrays.asList("c", "d"));
        double[] values = {2.0, 3.0};
        List<List<String>> queries = Arrays.asList(
                Arrays.asList("a", "d"), Arrays.asList("c", "b"));
        double[] expected = {-1.0, -1.0};

        assertArrayEquals(expected, EvaluateDivision.calcEquation(equations, values, queries), EPSILON);
        assertArrayEquals(expected, EvaluateDivision.calcEquation2(equations, values, queries), EPSILON);
    }

    @Test
    void testChain() {
        List<List<String>> equations = Arrays.asList(
                Arrays.asList("a", "b"), Arrays.asList("b", "c"),
                Arrays.asList("c", "d"), Arrays.asList("d", "e"));
        double[] values = {2.0, 3.0, 4.0, 5.0};
        List<List<String>> queries = Arrays.asList(
                Arrays.asList("a", "e"), Arrays.asList("e", "a"));
        double[] expected = {120.0, 1.0 / 120.0};

        assertArrayEquals(expected, EvaluateDivision.calcEquation(equations, values, queries), EPSILON);
        assertArrayEquals(expected, EvaluateDivision.calcEquation2(equations, values, queries), EPSILON);
    }
}
