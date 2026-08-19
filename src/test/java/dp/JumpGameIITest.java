package dp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JumpGameIITest {
    JumpGameII tbt;

    @BeforeEach
    void setUp() {
        tbt = new JumpGameII();
    }

    @Test
    void testMinJumps() {
        assertEquals(2, tbt.minJumps(new int[]{2, 3, 1, 1, 4}));
        assertEquals(2, tbt.minJumps(new int[]{2, 3, 0, 1, 4}));
        assertEquals(0, tbt.minJumps(new int[]{0}));
        assertEquals(1, tbt.minJumps(new int[]{1, 2}));
        assertEquals(1, tbt.minJumps(new int[]{3, 2, 1}));
        assertEquals(4, tbt.minJumps(new int[]{1, 1, 1, 1, 1}));
        assertEquals(1, tbt.minJumps(new int[]{10, 0, 0, 0, 0}));
        assertEquals(2, tbt.minJumps(new int[]{2, 3, 0, 0, 4}));
        assertEquals(3, tbt.minJumps(new int[]{1, 2, 1, 1, 1}));
    }
}
