package junit.converter;

import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Converts parameter to String Array for Junit.
 */
public class StringArrayConverter extends SimpleArgumentConverter {
    @Override
    protected Object convert(Object source, Class<?> targetType) throws ArgumentConversionException {
        assertEquals(targetType, String[].class);
        String array = (String) source;
        array = array.replaceAll("[{}\\[\\]\"]", "");
        if (array.trim().isEmpty()) return new String[0];
        if (array.equals("null")) return null;

        // split array elements with comma and space
        String[] stringArray = array.split("\\s*,\\s*");
        return stringArray;
    }
}
