package hash;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class SortCharactersByFrequencyTest {

    private void assertFrequencySorted(String original, String result) {
        // same char counts
        Map<Character, Integer> origCount = new HashMap<>(), resCount = new HashMap<>();
        for (char c : original.toCharArray()) origCount.merge(c, 1, Integer::sum);
        for (char c : result.toCharArray()) resCount.merge(c, 1, Integer::sum);
        assertEquals(origCount, resCount);
        // frequency non-increasing
        int prevFreq = Integer.MAX_VALUE, i = 0;
        while (i < result.length()) {
            char c = result.charAt(i);
            int runLen = 0;
            while (i < result.length() && result.charAt(i) == c) { runLen++; i++; }
            assertEquals((int) origCount.get(c), runLen);
            assertTrue(runLen <= prevFreq);
            prevFreq = runLen;
        }
    }

    private void verify(String s) {
        assertFrequencySorted(s, SortCharactersByFrequency.frequencySortSort(s));
        assertFrequencySorted(s, SortCharactersByFrequency.frequencySortBucket(s));
    }

    @Test void example1() { verify("tree"); }
    @Test void example2() { verify("cccaaa"); }
    @Test void example3() { verify("Aabb"); }
    @Test void singleChar() { verify("z"); }
    @Test void allSame() { verify("aaaa"); }
    @Test void allUnique() { verify("abc"); }
    @Test void digitsAndLetters() { verify("2a554442f544asfasssffffasss"); }
    @Test void caseSensitive() { verify("aAaA"); }
    @Test void twoChars() { verify("ab"); }
    @Test void longRepeated() { verify("bbbcccaaa"); }
}
