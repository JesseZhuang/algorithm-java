package string;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class BoggleTest {
    static Boggle tbt;

    @BeforeAll
    static void setup() {
        tbt = new Boggle();
    }

    @ParameterizedTest
    @MethodSource("testCases1")
    void testValidWords(Character[][] characters, String[] dictionary, String[] validWords) {

    }

    Stream<Arguments> testCases1() {
        return Stream.of(
                Arguments.of(new Character[][]{
                        {'A', 'N', 'D'},
                        {'A', 'D', 'Z'},
                        {'B', 'E', 'E'}
                }, new String[]{
                        "BAD", "BEE", "AN", "AD", "AND", "BED", "SAD", "ADD"
                }, new String[] {
                        "BAD", "BEE", "AN", "AD", "AND", "BED"
                })
        );
    }
}
