package tree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class KthLexicoNumTest {

    KthLexicoNum.Solution1 tbt;

    @BeforeEach
    void setUp() {
        tbt = new KthLexicoNum.Solution1();
    }

    @Test
    void testSolution1() {
        assertEquals(10, tbt.findKthNumber(13, 2));
        assertEquals(1, tbt.findKthNumber(1, 1));
    }
}