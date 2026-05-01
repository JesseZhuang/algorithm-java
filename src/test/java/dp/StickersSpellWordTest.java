package dp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StickersSpellWordTest {

    @Test void example1() {
        assertEquals(3, StickersSpellWord.minStickers(
                new String[]{"with", "example", "science"}, "thehat"));
    }

    @Test void example2() {
        assertEquals(-1, StickersSpellWord.minStickers(
                new String[]{"notice", "possible"}, "basicbasic"));
    }

    @Test void example3() {
        assertEquals(5, StickersSpellWord.minStickers(
                new String[]{"aa", "b"}, "aaabbb"));
    }
}
