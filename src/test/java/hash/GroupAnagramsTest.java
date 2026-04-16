package hash;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GroupAnagramsTest {

    private List<List<String>> normalize(List<List<String>> groups) {
        return groups.stream()
                .map(g -> g.stream().sorted().collect(Collectors.toList()))
                .sorted(Comparator.comparing(g -> g.get(0)))
                .collect(Collectors.toList());
    }

    private void verify(String[] strs, List<List<String>> expected) {
        assertEquals(normalize(expected), normalize(GroupAnagrams.groupAnagramsSort(strs)));
        assertEquals(normalize(expected), normalize(GroupAnagrams.groupAnagramsCount(strs)));
    }

    @Test void example1() {
        verify(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"},
                List.of(List.of("bat"), List.of("nat", "tan"), List.of("ate", "eat", "tea")));
    }

    @Test void example2() {
        verify(new String[]{""}, List.of(List.of("")));
    }

    @Test void example3() {
        verify(new String[]{"a"}, List.of(List.of("a")));
    }

    @Test void allSame() {
        verify(new String[]{"abc", "bca", "cab"}, List.of(List.of("abc", "bca", "cab")));
    }

    @Test void allDifferent() {
        verify(new String[]{"a", "b", "c"}, List.of(List.of("a"), List.of("b"), List.of("c")));
    }

    @Test void singleCharAnagrams() {
        verify(new String[]{"a", "a", "a"}, List.of(List.of("a", "a", "a")));
    }

    @Test void mixedLengths() {
        verify(new String[]{"ab", "ba", "abc", "cba", "a"},
                List.of(List.of("ab", "ba"), List.of("abc", "cba"), List.of("a")));
    }
}
