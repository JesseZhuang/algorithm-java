package array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TwoDArrayTest {
    @Test
    void testRowColumn() {
        int[][] numbers = new int[][]{
                {1, 2, 3},
                {4, 5, 6}
        };
        assertEquals(2, numbers.length);
        assertEquals(3, numbers[0].length);
    }

    @Test
    void testEmptyRowColumn() {
        int[][] numbers = new int[][]{};
        assertEquals(0, numbers.length);
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> System.out.println(numbers[0].length));
    }
}
