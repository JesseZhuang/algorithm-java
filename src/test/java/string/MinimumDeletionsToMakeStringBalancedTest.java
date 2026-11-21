package string;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MinimumDeletionsToMakeStringBalancedTest {
    @Test
    void testExamplesAndBoundaryCases() {
        assertBothSolutions("aababbab", 2);
        assertBothSolutions("bbaaaaabb", 2);
        assertBothSolutions("a", 0);
        assertBothSolutions("b", 0);
        assertBothSolutions("aaaa", 0);
        assertBothSolutions("bbbb", 0);
    }

    @Test
    void testAlreadyBalancedAndOutOfOrderCharacters() {
        assertBothSolutions("aaabbb", 0);
        assertBothSolutions("aabbab", 1);
        assertBothSolutions("abba", 1);
        assertBothSolutions("bbaa", 2);
    }

    @Test
    void testMaximumConstraintLength() {
        String s = "b".repeat(50_000) + "a".repeat(50_000);
        assertBothSolutions(s, 50_000);
    }

    private void assertBothSolutions(String s, int expected) {
        assertEquals(expected, MinimumDeletionsToMakeStringBalanced.minimumDeletions(s), "DP: " + s);
        assertEquals(expected, MinimumDeletionsToMakeStringBalanced.minimumDeletionsBySplit(s), "split: " + s);
    }
}
