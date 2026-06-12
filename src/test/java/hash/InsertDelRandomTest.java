package hash;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class InsertDelRandomTest {

    private InsertDelRandom.RandomizedSet set;

    @BeforeEach
    void setUp() {
        set = new InsertDelRandom().new RandomizedSet();
    }

    @Test
    void testLeetCodeExample() {
        assertTrue(set.insert(1));
        assertFalse(set.remove(2));
        assertTrue(set.insert(2));
        int r1 = set.getRandom();
        assertTrue(r1 == 1 || r1 == 2);
        assertTrue(set.remove(1));
        assertFalse(set.insert(2));
        assertEquals(2, set.getRandom());
    }

    @Test
    void testInsertDuplicateReturnsFalse() {
        assertTrue(set.insert(5));
        assertFalse(set.insert(5));
    }

    @Test
    void testRemoveNonexistentReturnsFalse() {
        assertFalse(set.remove(99));
    }

    @Test
    void testInsertRemoveReinsert() {
        assertTrue(set.insert(10));
        assertTrue(set.remove(10));
        assertTrue(set.insert(10));
        assertEquals(10, set.getRandom());
    }

    @Test
    void testSingleElementGetRandom() {
        set.insert(42);
        for (int i = 0; i < 100; i++) {
            assertEquals(42, set.getRandom());
        }
    }

    @Test
    void testRemoveFirstElementVerifyRemainingRandom() {
        set.insert(1);
        set.insert(2);
        set.insert(3);
        // remove first inserted element
        assertTrue(set.remove(1));
        Set<Integer> seen = new HashSet<>();
        for (int i = 0; i < 200; i++) {
            seen.add(set.getRandom());
        }
        assertEquals(Set.of(2, 3), seen);
    }

    @Test
    void testRemoveMiddleElementVerifyRemainingRandom() {
        set.insert(1);
        set.insert(2);
        set.insert(3);
        // remove middle element
        assertTrue(set.remove(2));
        Set<Integer> seen = new HashSet<>();
        for (int i = 0; i < 200; i++) {
            seen.add(set.getRandom());
        }
        assertEquals(Set.of(1, 3), seen);
    }
}
