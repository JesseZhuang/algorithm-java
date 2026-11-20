package string;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CountPrefixAndSuffixPairsITest {

    @Test void countsPairsInExamples() {
        assertEquals(4, CountPrefixAndSuffixPairsI.countPrefixSuffixPairs(
                new String[]{"a", "aba", "ababa", "aa"}));
        assertEquals(2, CountPrefixAndSuffixPairsI.countPrefixSuffixPairs(
                new String[]{"pa", "papa", "ma", "mama"}));
        assertEquals(0, CountPrefixAndSuffixPairsI.countPrefixSuffixPairs(
                new String[]{"abab", "ab"}));
    }

    @Test void singletonHasNoPairs() {
        assertEquals(0, CountPrefixAndSuffixPairsI.countPrefixSuffixPairs(new String[]{"a"}));
    }

    @Test void countsEqualWordsAtDifferentIndices() {
        assertEquals(3, CountPrefixAndSuffixPairsI.countPrefixSuffixPairs(
                new String[]{"abc", "abc", "abc"}));
    }

    @Test void requiresBothPrefixAndSuffix() {
        assertEquals(0, CountPrefixAndSuffixPairsI.countPrefixSuffixPairs(new String[]{"ab", "abc"}));
        assertEquals(0, CountPrefixAndSuffixPairsI.countPrefixSuffixPairs(new String[]{"ab", "cab"}));
    }

    @Test void doesNotReversePairOrderWhenEarlierWordIsLonger() {
        assertEquals(0, CountPrefixAndSuffixPairsI.countPrefixSuffixPairs(new String[]{"aba", "a"}));
    }

    @Test void countsAllPairsAtMaximumArraySize() {
        String[] words = new String[50];
        Arrays.fill(words, "abcdefghij");
        assertEquals(1225, CountPrefixAndSuffixPairsI.countPrefixSuffixPairs(words));
    }
}
