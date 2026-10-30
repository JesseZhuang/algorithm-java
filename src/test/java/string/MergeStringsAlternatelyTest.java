package string;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MergeStringsAlternatelyTest {

    @Test void alternatesEqualLengthWordsStartingWithFirstWord() {
        assertEquals("apbqcr", MergeStringsAlternately.mergeAlternately("abc", "pqr"));
    }

    @Test void appendsRemainingCharactersFromSecondWord() {
        assertEquals("apbqrs", MergeStringsAlternately.mergeAlternately("ab", "pqrs"));
    }

    @Test void appendsRemainingCharactersFromFirstWord() {
        assertEquals("apbqcd", MergeStringsAlternately.mergeAlternately("abcd", "pq"));
    }

    @Test void handlesEmptyWords() {
        assertEquals("abc", MergeStringsAlternately.mergeAlternately("abc", ""));
        assertEquals("pqr", MergeStringsAlternately.mergeAlternately("", "pqr"));
        assertEquals("", MergeStringsAlternately.mergeAlternately("", ""));
    }
}
