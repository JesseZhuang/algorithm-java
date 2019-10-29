package array;

import junit.converter.IntegerArrayConverter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FindArrayPeakTest {
    static FindArrayPeak tbt;

    @BeforeAll
    static void setup() {
        tbt = new FindArrayPeak();
    }

    @ParameterizedTest
    @CsvFileSource(resources = {"/FindArrayPeak.csv"}, numLinesToSkip = 2, delimiter = ';')
    void testFindPeak(@ConvertWith(IntegerArrayConverter.class) Integer[] a, int peakIndex) {
        assertEquals(peakIndex, tbt.peakIndex(a));
    }

    void testInvalid() {
        Integer[] a = new Integer[]{4, 2, 4};
        assertThrows(IllegalStateException.class, () -> tbt.peakIndex(a));
    }
}
