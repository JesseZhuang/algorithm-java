package heap;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GiftRichestPileTest {

    @Test
    void testPickGifts() {
        assertEquals(29, GiftRichestPile.Solution.pickGifts(new int[]{25, 64, 9, 4, 100}, 4));
        assertEquals(4, GiftRichestPile.Solution.pickGifts(new int[]{1, 1, 1, 1}, 4));
    }
}
