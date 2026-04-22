package string;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WordsWithinTwoEditsTest {

    private void verify(String[] queries, String[] dictionary, List<String> expected) {
        assertEquals(expected, WordsWithinTwoEdits.twoEditWordsBrute(queries, dictionary));
        assertEquals(expected, WordsWithinTwoEdits.twoEditWordsTrie(queries, dictionary));
    }

    @Test void example1() {
        verify(new String[]{"word", "note", "ants", "wood"},
                new String[]{"wood", "joke", "moat"},
                List.of("word", "note", "wood"));
    }

    @Test void example2() {
        verify(new String[]{"yes"}, new String[]{"not"}, List.of());
    }

    @Test void exactMatch() {
        verify(new String[]{"abc"}, new String[]{"abc"}, List.of("abc"));
    }

    @Test void oneEdit() {
        verify(new String[]{"abc"}, new String[]{"adc"}, List.of("abc"));
    }

    @Test void twoEditsBoundary() {
        verify(new String[]{"abc"}, new String[]{"axy"}, List.of("abc"));
    }

    @Test void threeEditsRejected() {
        verify(new String[]{"abc"}, new String[]{"xyz"}, List.of());
    }

    @Test void singleCharWords() {
        verify(new String[]{"a", "b"}, new String[]{"a"}, List.of("a", "b"));
    }

    @Test void duplicateQueries() {
        verify(new String[]{"abc", "abc"}, new String[]{"abc"}, List.of("abc", "abc"));
    }

    @Test void multipleDictionaryMatches() {
        verify(new String[]{"abc"}, new String[]{"abd", "xbc"}, List.of("abc"));
    }
}
