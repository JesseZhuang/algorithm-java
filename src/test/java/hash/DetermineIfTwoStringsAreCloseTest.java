package hash;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DetermineIfTwoStringsAreCloseTest {
    @Test
    void officialExamples() {
        assertTrue(DetermineIfTwoStringsAreClose.closeStrings("abc", "bca"));
        assertFalse(DetermineIfTwoStringsAreClose.closeStrings("a", "aa"));
        assertTrue(DetermineIfTwoStringsAreClose.closeStrings("cabbba", "abbccc"));
    }

    @Test
    void differentCharacterSupportIsNotClose() {
        assertFalse(DetermineIfTwoStringsAreClose.closeStrings("ab", "cd"));
        assertFalse(DetermineIfTwoStringsAreClose.closeStrings("aaabbb", "bbbccc"));
    }

    @Test
    void sameSupportWithDifferentFrequencyMultisetsIsNotClose() {
        assertFalse(DetermineIfTwoStringsAreClose.closeStrings("aaabbc", "aabbcc"));
    }

    @Test
    void swappedCharacterFrequenciesAreClose() {
        assertTrue(DetermineIfTwoStringsAreClose.closeStrings("aaabbc", "aabbbc"));
    }

    @Test
    void singleCharacterAndIdenticalStrings() {
        assertTrue(DetermineIfTwoStringsAreClose.closeStrings("a", "a"));
        assertFalse(DetermineIfTwoStringsAreClose.closeStrings("a", "z"));
        assertTrue(DetermineIfTwoStringsAreClose.closeStrings("zzxy", "zzxy"));
    }

    @Test
    void handlesMaximumLengthAndUnequalFrequencies() {
        String manyAsAndOneB = "a".repeat(99_999) + "b";
        assertTrue(DetermineIfTwoStringsAreClose.closeStrings(manyAsAndOneB, "a" + "b".repeat(99_999)));
        assertFalse(DetermineIfTwoStringsAreClose.closeStrings(manyAsAndOneB, "a".repeat(99_998) + "bb"));
    }
}
