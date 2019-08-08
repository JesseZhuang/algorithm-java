package junit.converter;

import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Converts 2D array like [[1,2], [2,3]] or {{1, 2}, {2, 3}}.
 */
public class TwoDIntegerArrayConverter extends SimpleArgumentConverter {
    @Override
    protected Object convert(Object source, Class<?> targetType) throws ArgumentConversionException {
        assertEquals(targetType, Integer[][].class);
        String array = (String) source;
        //remove unimportant brackets
        array = array.replaceAll("[\\[{]", "").replaceAll("[}\\]]{2}\\s*$", "");
        String[] outer = array.split("[\\]}],\\s*");
        if (outer[0].trim().isEmpty()) return new Integer[outer.length][0];
        String[] inner = outer[0].split("\\s*,\\s*");
        Integer[][] result = new Integer[outer.length][inner.length];
        for (int i = 0; i < outer.length; i++) {
            inner = outer[i].split("\\s*,\\s*");
            for (int j = 0; j < inner.length; j++) {
                result[i][j] = Integer.parseInt(inner[j]);
            }
        }
        return result;
    }
}
