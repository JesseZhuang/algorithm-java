package sliding;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FruitIntoBasketsTest {

    // --- Solution 1: sliding window + HashMap ---

    @Test
    void testHashMapExample1() {
        assertEquals(3, FruitIntoBaskets.totalFruit(new int[]{1, 2, 1}));
    }

    @Test
    void testHashMapExample2() {
        assertEquals(3, FruitIntoBaskets.totalFruit(new int[]{0, 1, 2, 2}));
    }

    @Test
    void testHashMapExample3() {
        assertEquals(4, FruitIntoBaskets.totalFruit(new int[]{1, 2, 3, 2, 2}));
    }

    @Test
    void testHashMapSingleElement() {
        assertEquals(1, FruitIntoBaskets.totalFruit(new int[]{5}));
    }

    @Test
    void testHashMapAllSame() {
        assertEquals(5, FruitIntoBaskets.totalFruit(new int[]{3, 3, 3, 3, 3}));
    }

    @Test
    void testHashMapTwoTypesInterleaved() {
        assertEquals(6, FruitIntoBaskets.totalFruit(new int[]{1, 2, 1, 2, 1, 2}));
    }

    @Test
    void testHashMapAlternatingThreeTypes() {
        assertEquals(2, FruitIntoBaskets.totalFruit(new int[]{1, 2, 3, 1, 2, 3}));
    }

    // --- Solution 2: track last two types ---

    @Test
    void testTwoTypesExample1() {
        assertEquals(3, FruitIntoBaskets.totalFruit2(new int[]{1, 2, 1}));
    }

    @Test
    void testTwoTypesExample2() {
        assertEquals(3, FruitIntoBaskets.totalFruit2(new int[]{0, 1, 2, 2}));
    }

    @Test
    void testTwoTypesExample3() {
        assertEquals(4, FruitIntoBaskets.totalFruit2(new int[]{1, 2, 3, 2, 2}));
    }

    @Test
    void testTwoTypesSingleElement() {
        assertEquals(1, FruitIntoBaskets.totalFruit2(new int[]{5}));
    }

    @Test
    void testTwoTypesAllSame() {
        assertEquals(5, FruitIntoBaskets.totalFruit2(new int[]{3, 3, 3, 3, 3}));
    }

    @Test
    void testTwoTypesTwoTypesInterleaved() {
        assertEquals(6, FruitIntoBaskets.totalFruit2(new int[]{1, 2, 1, 2, 1, 2}));
    }

    @Test
    void testTwoTypesAlternatingThreeTypes() {
        assertEquals(2, FruitIntoBaskets.totalFruit2(new int[]{1, 2, 3, 1, 2, 3}));
    }
}
