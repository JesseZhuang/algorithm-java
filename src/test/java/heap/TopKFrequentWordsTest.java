package heap;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TopKFrequentWordsTest {

    @Test
    void testExample1() {
        String[] words = {"i", "love", "leetcode", "i", "love", "coding"};
        List<String> expected = List.of("i", "love");
        assertEquals(expected, TopKFrequentWords.topKFrequent(words, 2));
        assertEquals(expected, TopKFrequentWords.topKFrequentBucket(words, 2));
    }

    @Test
    void testExample2() {
        String[] words = {"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"};
        List<String> expected = List.of("the", "is", "sunny", "day");
        assertEquals(expected, TopKFrequentWords.topKFrequent(words, 4));
        assertEquals(expected, TopKFrequentWords.topKFrequentBucket(words, 4));
    }

    @Test
    void testSingleWord() {
        String[] words = {"a"};
        List<String> expected = List.of("a");
        assertEquals(expected, TopKFrequentWords.topKFrequent(words, 1));
        assertEquals(expected, TopKFrequentWords.topKFrequentBucket(words, 1));
    }

    @Test
    void testAllSameFrequency() {
        String[] words = {"b", "c", "a"};
        List<String> expected = List.of("a", "b");
        assertEquals(expected, TopKFrequentWords.topKFrequent(words, 2));
        assertEquals(expected, TopKFrequentWords.topKFrequentBucket(words, 2));
    }

    @Test
    void testKEqualsUniqueCount() {
        String[] words = {"a", "a", "b", "b", "c"};
        List<String> expected = List.of("a", "b", "c");
        assertEquals(expected, TopKFrequentWords.topKFrequent(words, 3));
        assertEquals(expected, TopKFrequentWords.topKFrequentBucket(words, 3));
    }
}
