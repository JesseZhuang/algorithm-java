package string;

import com.google.common.collect.Lists;
import junit.converter.IntegerArrayConverter;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;
import util.IntArrayUtil;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ZfunctionTest {

    @ParameterizedTest
    @CsvSource({"aaaaaa,'0,5,4,3,2,1'", "aabaacd,'0,1,0,2,1,0,0'", "abababab, '0,0,6,0,4,0,2,0'"})
    void testZfunction(String s, @ConvertWith(IntegerArrayConverter.class) Integer[] expected) {
        assertArrayEquals(expected, IntArrayUtil.boxIntArray(Zfunction.zfunction(s)));
    }

    @ParameterizedTest
    @CsvSource({"aaaaaa, aa, '0,1,2,3,4'", "GEEKS FOR GEEKS, GEEK, '0,10'"})
    void testSearch(String text, String pattern, @ConvertWith(IntegerArrayConverter.class) Integer[] expected) {
        assertEquals(Lists.newArrayList(expected), Zfunction.search(text, pattern));
    }

    @ParameterizedTest
    @CsvSource({"aaaa, 1", "abab, 2", "abcabc, 3"})
    void testCompression(String s, int expected) {
        assertEquals(expected, Zfunction.compression(s));
    }
}