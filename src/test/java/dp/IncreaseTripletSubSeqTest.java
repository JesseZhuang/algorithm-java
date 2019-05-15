package dp;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvFileSource;
import junit.converter.IntegerArrayConverter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static util.IntArrayUtil.unBoxIntegerArray;

class IncreaseTripletSubSeqTest {
    private static IncreaseTripletSubSeq increaseTripletSubSeq;

    @BeforeAll
    static void setup() {
        increaseTripletSubSeq = new IncreaseTripletSubSeq();
    }

    @ParameterizedTest(name = "increasing triplet in {0} = {1}")
    @CsvFileSource(resources = {"/IncreaseTripletSubSeq.csv"}, delimiter = ' ', numLinesToSkip = 2)
    void testIncreasingTriplet(@ConvertWith(IntegerArrayConverter.class) Integer[] array, boolean tripletExists) {
        int[] intArray = unBoxIntegerArray(array);
        assertEquals(increaseTripletSubSeq.increasingTriplet(intArray), tripletExists);
        assertEquals(increaseTripletSubSeq.increasingTriplet2(intArray), tripletExists);
    }
}
