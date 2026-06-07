package hash;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContiguousArrayTest {

    @Test void example1() {
        assertEquals(2, ContiguousArray.findMaxLength(new int[]{0, 1}));
    }

    @Test void example2() {
        assertEquals(2, ContiguousArray.findMaxLength(new int[]{0, 1, 0}));
    }

    @Test void allZeros() {
        assertEquals(0, ContiguousArray.findMaxLength(new int[]{0, 0, 0}));
    }

    @Test void allOnes() {
        assertEquals(0, ContiguousArray.findMaxLength(new int[]{1, 1, 1}));
    }

    @Test void entireArray() {
        assertEquals(4, ContiguousArray.findMaxLength(new int[]{0, 1, 1, 0}));
    }

    @Test void longerArray() {
        assertEquals(6, ContiguousArray.findMaxLength(new int[]{0, 0, 1, 0, 0, 0, 1, 1}));
    }

    @Test void singleElement() {
        assertEquals(0, ContiguousArray.findMaxLength(new int[]{0}));
        assertEquals(0, ContiguousArray.findMaxLength(new int[]{1}));
    }

    @Test void alternating() {
        assertEquals(6, ContiguousArray.findMaxLength(new int[]{0, 1, 0, 1, 0, 1}));
    }

    @Test void maxAtEnd() {
        assertEquals(8, ContiguousArray.findMaxLength(new int[]{1, 1, 0, 1, 1, 0, 0, 0}));
    }
}
