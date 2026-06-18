package dp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JumpGameTest {

    private final JumpGame tbt = new JumpGame();

    @Test
    void testCanJumpDP1() {
        assertTrue(tbt.canJumpDP1(new int[]{2, 3, 1, 1, 4}));
        assertFalse(tbt.canJumpDP1(new int[]{3, 2, 1, 0, 4}));
        assertTrue(tbt.canJumpDP1(new int[]{0}));
        assertTrue(tbt.canJumpDP1(new int[]{1, 0}));
        assertFalse(tbt.canJumpDP1(new int[]{0, 1}));
        assertFalse(tbt.canJumpDP1(new int[]{1, 0, 0, 1}));
        assertTrue(tbt.canJumpDP1(new int[]{10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}));
    }

    @Test
    void testCanJumpDP2() {
        assertTrue(tbt.canJumpDP2(new int[]{2, 3, 1, 1, 4}));
        assertFalse(tbt.canJumpDP2(new int[]{3, 2, 1, 0, 4}));
        assertTrue(tbt.canJumpDP2(new int[]{0}));
        assertTrue(tbt.canJumpDP2(new int[]{1, 0}));
        assertFalse(tbt.canJumpDP2(new int[]{0, 1}));
        assertFalse(tbt.canJumpDP2(new int[]{1, 0, 0, 1}));
        assertTrue(tbt.canJumpDP2(new int[]{10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}));
    }
}
