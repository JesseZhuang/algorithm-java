package graph;

import junit.converter.StringArrayConverter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WordLadderTest {
    static WordLadder tbt;

    @BeforeAll
    static void setup() {
        tbt = new WordLadder();
    }

    @ParameterizedTest
    @CsvFileSource(numLinesToSkip = 2, delimiter = ';', resources = {"/WordLadder.csv"})
    void testLadderLength(String beginWord, String endWord,
                          @ConvertWith(StringArrayConverter.class) String[] wordList, int ladderLength) {
        assertEquals(ladderLength, tbt.ladderLengthBFS(beginWord, endWord, Arrays.asList(wordList)));
        assertEquals(ladderLength, tbt.ladderLengthTwoWayBFS(beginWord, endWord, Arrays.asList(wordList)));
        assertEquals(ladderLength, tbt.ladderLengthBFSTrie(beginWord, endWord, Arrays.asList(wordList)));
    }
}
