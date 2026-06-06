package hash;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FindAllAnagramsTest {

    @Test
    void testExample1() {
        assertEquals(List.of(0, 6), FindAllAnagrams.findAnagrams1("cbaebabacd", "abc"));
        assertEquals(List.of(0, 6), FindAllAnagrams.findAnagrams2("cbaebabacd", "abc"));
    }

    @Test
    void testExample2() {
        assertEquals(List.of(0, 1, 2), FindAllAnagrams.findAnagrams1("abab", "ab"));
        assertEquals(List.of(0, 1, 2), FindAllAnagrams.findAnagrams2("abab", "ab"));
    }

    @Test
    void testNoMatch() {
        assertEquals(List.of(), FindAllAnagrams.findAnagrams1("abcdef", "xyz"));
        assertEquals(List.of(), FindAllAnagrams.findAnagrams2("abcdef", "xyz"));
    }

    @Test
    void testPLongerThanS() {
        assertEquals(List.of(), FindAllAnagrams.findAnagrams1("ab", "abcd"));
        assertEquals(List.of(), FindAllAnagrams.findAnagrams2("ab", "abcd"));
    }

    @Test
    void testSingleCharMatch() {
        assertEquals(List.of(0, 2), FindAllAnagrams.findAnagrams1("aba", "a"));
        assertEquals(List.of(0, 2), FindAllAnagrams.findAnagrams2("aba", "a"));
    }

    @Test
    void testEntireStringIsAnagram() {
        assertEquals(List.of(0), FindAllAnagrams.findAnagrams1("bca", "abc"));
        assertEquals(List.of(0), FindAllAnagrams.findAnagrams2("bca", "abc"));
    }

    @Test
    void testRepeatedPattern() {
        assertEquals(List.of(0, 1, 3, 4, 5), FindAllAnagrams.findAnagrams1("aababaab", "aab"));
        assertEquals(List.of(0, 1, 3, 4, 5), FindAllAnagrams.findAnagrams2("aababaab", "aab"));
    }

    @Test
    void testEmptyS() {
        assertEquals(List.of(), FindAllAnagrams.findAnagrams1("", "abc"));
        assertEquals(List.of(), FindAllAnagrams.findAnagrams2("", "abc"));
    }
}
