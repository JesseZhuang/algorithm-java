package hash;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CheckIfNDoubleExistTest {
    @Test
    void testCheckIfExist() {
        assertTrue(CheckIfNDoubleExist.checkIfExist(new int[]{10, 2, 5, 3}));
        assertFalse(CheckIfNDoubleExist.checkIfExist(new int[]{3, 1, 7, 11}));
    }
}
