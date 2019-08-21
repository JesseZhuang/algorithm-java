package graph;

import junit.converter.IntegerArrayConverter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvFileSource;
import util.IntArrayUtil;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CoupleHoldingHandsTest {
    static CouplesHoldingHands tbt;

    @BeforeAll
    static void setup() {
        tbt = new CouplesHoldingHands();
    }

    @ParameterizedTest
    @CsvFileSource(resources = {"/CouplesHoldingHands.csv"}, numLinesToSkip = 2, delimiter = ';')
    void testMinSwap(@ConvertWith(IntegerArrayConverter.class) Integer[] row, int minSwap) {
        int[] positions = IntArrayUtil.unBoxIntegerArray(row);
        System.out.println(Arrays.toString(row));
        assertEquals(minSwap, tbt.minSwapsCouplesUF(positions));
    }
}
