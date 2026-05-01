package string;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MaxScoreSplitStringTest {
    @Test
    void testMaxScore() {
        assertEquals(5, MaxScoreSplitString.maxScore("011101"));
        assertEquals(5, MaxScoreSplitString.maxScore("00111"));
        assertEquals(3, MaxScoreSplitString.maxScore("1111"));
    }
}
