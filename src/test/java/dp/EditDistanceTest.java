package dp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EditDistanceTest {
    @Test
    void test1D() {
        assertEquals(3, EditDistance.minDistance("horse", "ros"));
        assertEquals(5, EditDistance.minDistance("intention", "execution"));
        assertEquals(0, EditDistance.minDistance("", ""));
        assertEquals(3, EditDistance.minDistance("", "abc"));
        assertEquals(3, EditDistance.minDistance("abc", ""));
        assertEquals(0, EditDistance.minDistance("abc", "abc"));
        assertEquals(1, EditDistance.minDistance("a", "b"));
        assertEquals(3, EditDistance.minDistance("kitten", "sitting"));
        assertEquals(3, EditDistance.minDistance("sunday", "saturday"));
    }

    @Test
    void test2D() {
        assertEquals(3, EditDistance.minDistance2D("horse", "ros"));
        assertEquals(5, EditDistance.minDistance2D("intention", "execution"));
        assertEquals(0, EditDistance.minDistance2D("", ""));
        assertEquals(3, EditDistance.minDistance2D("", "abc"));
        assertEquals(3, EditDistance.minDistance2D("abc", ""));
        assertEquals(0, EditDistance.minDistance2D("abc", "abc"));
        assertEquals(1, EditDistance.minDistance2D("a", "b"));
        assertEquals(3, EditDistance.minDistance2D("kitten", "sitting"));
        assertEquals(3, EditDistance.minDistance2D("sunday", "saturday"));
    }
}
