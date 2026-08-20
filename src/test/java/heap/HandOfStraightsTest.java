package heap;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HandOfStraightsTest {

    @Test
    void testExample1() {
        int[] hand = {1, 2, 3, 6, 2, 3, 4, 7, 8};
        assertTrue(HandOfStraights.isNStraightHand(hand, 3));
        assertTrue(HandOfStraights.isNStraightHand2(hand.clone(), 3));
    }

    @Test
    void testExample2() {
        int[] hand = {1, 2, 3, 4, 5};
        assertFalse(HandOfStraights.isNStraightHand(hand, 4));
        assertFalse(HandOfStraights.isNStraightHand2(hand.clone(), 4));
    }

    @Test
    void testSingleGroup() {
        int[] hand = {3, 4, 5, 6};
        assertTrue(HandOfStraights.isNStraightHand(hand, 4));
        assertTrue(HandOfStraights.isNStraightHand2(hand.clone(), 4));
    }

    @Test
    void testGroupSizeOne() {
        int[] hand = {5, 3, 1, 9};
        assertTrue(HandOfStraights.isNStraightHand(hand, 1));
        assertTrue(HandOfStraights.isNStraightHand2(hand.clone(), 1));
    }

    @Test
    void testDuplicatesFormingGroups() {
        int[] hand = {1, 1, 2, 2, 3, 3};
        assertTrue(HandOfStraights.isNStraightHand(hand, 3));
        assertTrue(HandOfStraights.isNStraightHand2(hand.clone(), 3));
    }

    @Test
    void testIndivisibleLength() {
        int[] hand = {1, 2, 3, 4, 5};
        assertFalse(HandOfStraights.isNStraightHand(hand, 3));
        assertFalse(HandOfStraights.isNStraightHand2(hand.clone(), 3));
    }

    @Test
    void testGapMakesImpossible() {
        int[] hand = {1, 3, 5, 7};
        assertFalse(HandOfStraights.isNStraightHand(hand, 2));
        assertFalse(HandOfStraights.isNStraightHand2(hand.clone(), 2));
    }

    @Test
    void testEntireHandIsOneGroup() {
        int[] hand = {1, 2, 3, 4, 5, 6};
        assertTrue(HandOfStraights.isNStraightHand(hand, 6));
        assertTrue(HandOfStraights.isNStraightHand2(hand.clone(), 6));
    }
}
