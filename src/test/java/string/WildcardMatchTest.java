package string;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class WildcardMatchTest {


    @Test
    void testMatch2P() {
        //Assertions.assertTrue(WildcardMatch.isMatch2P1("abb", "a*"));
        Assertions.assertTrue(WildcardMatch.isMatch2P1("abb", "a*b"));
    }
}
