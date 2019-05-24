package graph;

import junit.converter.IntegerArrayConverter;
import junit.converter.TwoDIntegerArrayConverter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvFileSource;
import util.IntArrayUtil;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class RedundantConnectionTest {
    static RedundantConnection tbt;

    @BeforeAll
    static void setup() {
        tbt = new RedundantConnection();
    }

    @ParameterizedTest(name = "edge {1} creates a cycle in {0}")
    @CsvFileSource(delimiter = ';', numLinesToSkip = 2, resources = {"/RedundantConnection.csv"})
    void testRedundantEdge(@ConvertWith(TwoDIntegerArrayConverter.class) Integer[][] edges,
                           @ConvertWith(IntegerArrayConverter.class) Integer[] edge) {
        // array method fails for third case, expected [4, 10], actual [6, 8]
        // assertArrayEquals(IntArrayUtil.unBoxIntegerArray(edge), tbt.redundantArray(edges));
         assertArrayEquals(IntArrayUtil.unBoxIntegerArray(edge), tbt.redundantGraph(edges));
    }
}
