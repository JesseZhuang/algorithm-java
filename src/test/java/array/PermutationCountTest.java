package array;

import junit.converter.IntegerArrayConverter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PermutationCountTest {
    static PermutationCount tbt;

    @BeforeAll
    static void setup() {
        tbt = new PermutationCount();
    }

    @ParameterizedTest
    @CsvFileSource(resources = {"/PermutationCount.csv"}, numLinesToSkip = 2, delimiter = ';')
    void testPermutationCount(@ConvertWith(IntegerArrayConverter.class) Integer[] numbers, int count) {
        assertEquals(count, tbt.permutationCount(numbers));
    }
}
