package stack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CheckIfWordIsValidAfterSubstitutionsTest {

    @Test
    void acceptsSingleInsertion() {
        assertTrue(CheckIfWordIsValidAfterSubstitutions.isValid("abc"));
    }

    @Test
    void acceptsNestedInsertions() {
        assertTrue(CheckIfWordIsValidAfterSubstitutions.isValid("aabcbc"));
        assertTrue(CheckIfWordIsValidAfterSubstitutions.isValid("aabcbabcc"));
    }

    @Test
    void acceptsConcatenatedAndNestedInsertions() {
        assertTrue(CheckIfWordIsValidAfterSubstitutions.isValid("abcabcababcc"));
    }

    @Test
    void rejectsInvalidOrderWithEqualCharacterCounts() {
        assertFalse(CheckIfWordIsValidAfterSubstitutions.isValid("abccba"));
        assertFalse(CheckIfWordIsValidAfterSubstitutions.isValid("cababc"));
        assertFalse(CheckIfWordIsValidAfterSubstitutions.isValid("bac"));
    }

    @Test
    void rejectsClosingCharacterWithoutMatchingPrefix() {
        assertFalse(CheckIfWordIsValidAfterSubstitutions.isValid("c"));
        assertFalse(CheckIfWordIsValidAfterSubstitutions.isValid("ac"));
        assertFalse(CheckIfWordIsValidAfterSubstitutions.isValid("abbc"));
    }

    @Test
    void rejectsLeftoverCharacters() {
        assertFalse(CheckIfWordIsValidAfterSubstitutions.isValid("a"));
        assertFalse(CheckIfWordIsValidAfterSubstitutions.isValid("ab"));
        assertFalse(CheckIfWordIsValidAfterSubstitutions.isValid("abca"));
        assertFalse(CheckIfWordIsValidAfterSubstitutions.isValid("aabcbcab"));
    }

    @Test
    void acceptsDeeplyNestedInsertions() {
        assertTrue(CheckIfWordIsValidAfterSubstitutions.isValid("a".repeat(6000) + "bc".repeat(6000)));
    }
}
