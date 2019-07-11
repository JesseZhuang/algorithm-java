package graph;

import junit.converter.IntegerArrayConverter;
import junit.converter.TwoDIntegerArrayConverter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class RedundantConnectionIITest {
    static RedundantConnectionII tbt;

    @BeforeAll
    static void setup() {
        tbt = new RedundantConnectionII();
    }

    @ParameterizedTest(name = "edge {1} invalid in {0}")
    @CsvFileSource(delimiter = ';', numLinesToSkip = 2, resources = {"/RedundantConnectionII.csv"})
    void testRedundantEdge(@ConvertWith(TwoDIntegerArrayConverter.class) Integer[][] edges,
                           @ConvertWith(IntegerArrayConverter.class) Integer[] edge) {
        // assertArrayEquals(IntArrayUtil.unBoxIntegerArray(edge), tbt.redundantGraph(edges));
        assertArrayEquals(edge, tbt.redundantUFOnePass(edges));
    }
}
