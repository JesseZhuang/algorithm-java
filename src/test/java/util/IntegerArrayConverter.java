package util;

import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Converts parameter to Integer Array for Junit.
 */
public class IntegerArrayConverter extends SimpleArgumentConverter {
    @Override
    protected Object convert(Object source, Class<?> targetType) throws ArgumentConversionException {
        assertEquals(targetType, Integer[].class);
        String array = (String) source;
        if (array.equals("{}")) return new Integer[0];

        String[] stringArray = array.split("\\s*,\\s*");
        Integer[] result = new Integer[stringArray.length];
        for(int i = 0; i < stringArray.length; i++)
            result[i] = Integer.parseInt(stringArray[i]);
        return result;
    }

    public static int[] unboxIntegerArray(Integer[] integers) {
        if (integers == null) return new int[0];
        int[] result = new int[integers.length];
        for (int i = 0; i < integers.length; i++)
            result[i] = integers[i];
        return result;
    }
}
