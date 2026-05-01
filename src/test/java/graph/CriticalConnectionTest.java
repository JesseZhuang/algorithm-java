package graph;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CriticalConnectionTest {

    CriticalConnection tbt;

    @BeforeEach
    void setUp() {
        tbt = new CriticalConnection();
    }

    @Test
    void testExample1() {
        List<List<Integer>> connections = Arrays.asList(
                Arrays.asList(0, 1), Arrays.asList(1, 2),
                Arrays.asList(2, 0), Arrays.asList(1, 3));
        List<List<Integer>> result = tbt.criticalConnections(4, connections);
        result.sort(Comparator.comparingInt(a -> a.get(0)));
        assertEquals(List.of(Arrays.asList(1, 3)), result);
    }

    @Test
    void testExample2() {
        List<List<Integer>> connections = List.of(Arrays.asList(0, 1));
        List<List<Integer>> result = tbt.criticalConnections(2, connections);
        assertEquals(List.of(Arrays.asList(0, 1)), result);
    }
}
