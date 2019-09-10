package hash;

import junit.converter.StringArrayConverter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class MaximalPatternsTest {
    static MaximalPatterns tbt;

    @BeforeAll
    static void setup() {
        tbt = new MaximalPatterns();
    }

    @ParameterizedTest
    @CsvFileSource(numLinesToSkip = 2, delimiter = ';', resources = {"/MaximalPatterns.csv"})
    void testFrequentPatterns(@ConvertWith(StringArrayConverter.class) String[] sessions,
                              double threshold,
                              @ConvertWith(StringArrayConverter.class) String[] frequentPatterns){
        assertArrayEquals(frequentPatterns, tbt.maximalPatters(sessions, threshold));
    }
}
