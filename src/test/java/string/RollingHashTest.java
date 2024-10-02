package string;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RollingHashTest {

    @Test
    void groupDuplicates() {
        List<String> strings = List.of("a", "b", "apple", "pear", "apple", "pear", "pear", "a", "b");
        List<List<String>> expected = List.of(
                List.of("a", "a"),
                List.of("apple", "apple"),
                List.of("b", "b"),
                List.of("pear", "pear", "pear")
        );
        List<List<String>> actual = RollingHash.groupDuplicates(strings);
        actual.sort(Comparator.comparing(List::getFirst));
        assertEquals(expected, actual);
    }
}
