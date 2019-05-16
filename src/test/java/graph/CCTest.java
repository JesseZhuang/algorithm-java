package graph;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.introcs.In;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CCTest {
    @Test
    void testTinyG(){
        List<List<Integer>> components = new ArrayList<>();
        components.add(Arrays.asList(0, 1, 2, 3, 4, 5, 6));
        components.add(Arrays.asList(7, 8));
        components.add(Arrays.asList(9, 10, 11, 12));
        assertEquals(components, ConnectedComponents.components(new Graph(new In("/graph/tinyG.txt"))));
    }
}
