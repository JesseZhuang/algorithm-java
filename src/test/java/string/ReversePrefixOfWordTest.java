package string;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReversePrefixOfWordTest {

    @Test void reversesPrefixAndLeavesSuffixUntouched() {
        assertEquals("dcbaefd", ReversePrefixOfWord.reversePrefix("abcdefd", 'd'));
    }

    @Test void reversesEvenLengthPrefix() {
        assertEquals("zxyabc", ReversePrefixOfWord.reversePrefix("yxzabc", 'z'));
    }

    @Test void usesFirstOccurrenceOnly() {
        assertEquals("bacd", ReversePrefixOfWord.reversePrefix("abcd", 'b'));
        assertEquals("cbaac", ReversePrefixOfWord.reversePrefix("abcac", 'c'));
    }

    @Test void leavesWordUnchangedWhenCharacterAbsent() {
        assertEquals("abcdef", ReversePrefixOfWord.reversePrefix("abcdef", 'z'));
    }

    @Test void leavesWordUnchangedWhenCharacterIsFirst() {
        assertEquals("apple", ReversePrefixOfWord.reversePrefix("apple", 'a'));
    }

    @Test void reversesEntireWordWhenCharacterIsLast() {
        assertEquals("fedcba", ReversePrefixOfWord.reversePrefix("abcdef", 'f'));
    }

    @Test void handlesSingleCharacterWord() {
        assertEquals("a", ReversePrefixOfWord.reversePrefix("a", 'a'));
        assertEquals("a", ReversePrefixOfWord.reversePrefix("a", 'b'));
    }

    @Test void handlesRepeatedCharactersWithinPrefix() {
        assertEquals("abaaxy", ReversePrefixOfWord.reversePrefix("baaaxy", 'a'));
    }

    @Test void handlesMaximumLengthWord() {
        String word = "a".repeat(249) + "z";
        assertEquals("z" + "a".repeat(249), ReversePrefixOfWord.reversePrefix(word, 'z'));
    }
}
