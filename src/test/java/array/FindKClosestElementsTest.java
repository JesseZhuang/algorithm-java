package array;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FindKClosestElementsTest {
    @Test
    void testFindClosestElements() {
        assertEquals(List.of(1, 2, 3, 4),
                FindKClosestElements.findClosestElements(new int[]{1, 2, 3, 4, 5}, 4, 3));
        assertEquals(List.of(1, 1, 2, 3),
                FindKClosestElements.findClosestElements(new int[]{1, 1, 2, 3, 4, 5}, 4, -1));
    }
}
