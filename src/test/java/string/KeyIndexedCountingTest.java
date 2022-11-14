package string;

import junit.converter.IntegerArrayConverter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvFileSource;
import util.IntArrayUtil;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class KeyIndexedCountingTest {

    @ParameterizedTest(name = "{0} sorted = {2}")
    @CsvFileSource(resources = {"/KeyIndexedCounting.csv"}, delimiter = ';', numLinesToSkip = 2)
    void testSort(@ConvertWith(IntegerArrayConverter.class) Integer[] a, int R,
                  @ConvertWith(IntegerArrayConverter.class) Integer[] sorted) {
        int[] input = IntArrayUtil.unBoxIntegerArray(a);
        KeyIndexedCounting.sort(input, R);
        assertArrayEquals(IntArrayUtil.unBoxIntegerArray(sorted), input);
    }

    @Test
    void testSortCharacters() {
        char[] characters = new char[]{'b', 'a', 'c', 'b'};
        int[] numbers = new int[characters.length];
        for (int i = 0; i < characters.length; i++) numbers[i] = convertCharToInt(characters[i]);
        System.out.println(Arrays.toString(numbers));
        KeyIndexedCounting.sort(numbers, 128); // assuming lower case letters, use ascii to avoid shifting
        System.out.println(Arrays.toString(numbers));
        for (int i = 0; i < characters.length; i++) characters[i] = convertIntToChar(numbers[i]);
        assertArrayEquals(new char[]{'a', 'b', 'b', 'c'}, characters);
    }

    private int convertCharToInt(char c) {
        return c;
    }

    // https://en.wikibooks.org/wiki/Unicode/Character_reference/0000-0FFF
    private char convertIntToChar(int i) {
        if (i > 65536) throw new IllegalArgumentException("integer is too lager, > 2 bytes");
        return (char) i;
    }
}
