package string;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class KthDistinctStringTest {

    @Test
    void officialExample() {
        assertEquals("a", KthDistinctString.kthDistinct(
                new String[]{"d", "b", "c", "b", "c", "a"}, 2));
    }

    @Test
    void returnsFirstAndLastDistinctStrings() {
        String[] arr = {"first", "middle", "middle", "last"};

        assertEquals("first", KthDistinctString.kthDistinct(arr, 1));
        assertEquals("last", KthDistinctString.kthDistinct(arr, 2));
    }

    @Test
    void ignoresRepeatedStrings() {
        assertEquals("unique", KthDistinctString.kthDistinct(
                new String[]{"repeat", "unique", "repeat", "repeat"}, 1));
    }

    @Test
    void returnsEmptyWhenThereIsNoKthDistinctString() {
        assertEquals("", KthDistinctString.kthDistinct(
                new String[]{"a", "b", "a", "b"}, 1));
    }

    @Test
    void handlesSingleElementArray() {
        assertEquals("only", KthDistinctString.kthDistinct(new String[]{"only"}, 1));
    }
}
