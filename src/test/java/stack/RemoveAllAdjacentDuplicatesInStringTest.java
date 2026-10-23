package stack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RemoveAllAdjacentDuplicatesInStringTest {

    @Test
    void exampleWithCascadingRemoval() {
        assertEquals("ca", RemoveAllAdjacentDuplicatesInString.removeDuplicates("abbaca"));
    }

    @Test
    void exampleWithMiddlePair() {
        assertEquals("ay", RemoveAllAdjacentDuplicatesInString.removeDuplicates("azxxzy"));
    }

    @Test
    void cascadingRemovalEmptiesString() {
        assertEquals("", RemoveAllAdjacentDuplicatesInString.removeDuplicates("abba"));
    }

    @Test
    void singleCharacterRemains() {
        assertEquals("a", RemoveAllAdjacentDuplicatesInString.removeDuplicates("a"));
    }

    @Test
    void noAdjacentDuplicatesRemainUnchanged() {
        assertEquals("abcd", RemoveAllAdjacentDuplicatesInString.removeDuplicates("abcd"));
    }

    @Test
    void repeatedChainsLeaveUnpairedCharacters() {
        assertEquals("abd", RemoveAllAdjacentDuplicatesInString.removeDuplicates("aaabccddd"));
    }

    @Test
    void maximumLengthWithNoRemovals() {
        String input = "ab".repeat(50_000);
        assertEquals(input, RemoveAllAdjacentDuplicatesInString.removeDuplicates(input));
    }

    @Test
    void maximumLengthWithRepeatedRemovals() {
        assertEquals("", RemoveAllAdjacentDuplicatesInString.removeDuplicates("a".repeat(100_000)));
    }
}
