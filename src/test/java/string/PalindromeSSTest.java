package string;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PalindromeSSTest {

    PalindromeSS tbt;

    @BeforeEach
    void setup() {
        tbt = new PalindromeSS();
    }

    @Test
    void countSubstrings() {
        assertEquals(3, tbt.countSubstrings("abc"));
        assertEquals(6, tbt.countSubstrings("aaa"));
    }
}
