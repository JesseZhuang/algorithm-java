package string;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LargestSubstringBetweenTwoEqualCharactersTest {

    @Test
    void findsTheLargestSubstringInTheOfficialExamples() {
        assertEquals(2, LargestSubstringBetweenTwoEqualCharacters.maxLengthBetweenEqualCharacters("abca"));
        assertEquals(0, LargestSubstringBetweenTwoEqualCharacters.maxLengthBetweenEqualCharacters("aa"));
    }

    @Test
    void keepsTheFirstOccurrenceToMaximizeTheDistance() {
        assertEquals(4, LargestSubstringBetweenTwoEqualCharacters.maxLengthBetweenEqualCharacters("cabbac"));
        assertEquals(3, LargestSubstringBetweenTwoEqualCharacters.maxLengthBetweenEqualCharacters("aabca"));
    }

    @Test
    void returnsMinusOneWhenNoCharacterRepeats() {
        assertEquals(-1, LargestSubstringBetweenTwoEqualCharacters.maxLengthBetweenEqualCharacters("cbzxy"));
    }
}
