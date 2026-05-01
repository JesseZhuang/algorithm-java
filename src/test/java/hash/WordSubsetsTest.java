package hash;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WordSubsetsTest {

    @Test void example1() {
        assertEquals(List.of("facebook", "google", "leetcode"),
                WordSubsets.wordSubsets(
                        new String[]{"amazon", "apple", "facebook", "google", "leetcode"},
                        new String[]{"e", "o"}));
    }

    @Test void example2() {
        assertEquals(List.of("apple", "google", "leetcode"),
                WordSubsets.wordSubsets(
                        new String[]{"amazon", "apple", "facebook", "google", "leetcode"},
                        new String[]{"l", "e"}));
    }
}
