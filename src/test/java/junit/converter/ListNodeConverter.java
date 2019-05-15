package junit.converter;

import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import struct.ListNode;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListNodeConverter extends SimpleArgumentConverter {
    @Override
    protected Object convert(Object source, Class<?> targetType) throws ArgumentConversionException {
        assertEquals(targetType, ListNode.class);
        String list = (String) source;
        if (list.equals("NULL")) return null;
        String[] values = list.split("->");
        int[] ints = Arrays.stream(values).mapToInt(Integer::parseInt).toArray();
        return ListNode.createFromArray(ints);
    }
}
