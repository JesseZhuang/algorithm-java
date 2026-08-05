package stack;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RemoveDuplicateLettersTest {

    @Test
    void basicCase() {
        assertEquals("abc", RemoveDuplicateLetters.removeDuplicateLetters("bcabc"));
    }

    @Test
    void keepOrder() {
        assertEquals("acdb", RemoveDuplicateLetters.removeDuplicateLetters("cbacdcbc"));
    }

    @Test
    void singleChar() {
        assertEquals("a", RemoveDuplicateLetters.removeDuplicateLetters("a"));
    }

    @Test
    void allSame() {
        assertEquals("a", RemoveDuplicateLetters.removeDuplicateLetters("aaaa"));
    }

    @Test
    void alreadySorted() {
        assertEquals("abc", RemoveDuplicateLetters.removeDuplicateLetters("abc"));
    }

    @Test
    void reverseSorted() {
        assertEquals("cba", RemoveDuplicateLetters.removeDuplicateLetters("cba"));
    }
}
