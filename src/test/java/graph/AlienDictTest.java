package graph;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AlienDictTest {

    AlienDict tbt;

    @BeforeEach
    void setUp() {
        tbt = new AlienDict();
    }

    @ParameterizedTest
    @MethodSource("testCases")
    void alienOrder(String[] input, String output) {
        assertEquals(output, tbt.alienOrderBFS(input));
    }

    static Stream<Arguments> testCases() {
        return Stream.of(
                Arguments.of(new String[]{"zy", "zx"}, "yxz"),
                Arguments.of(new String[]{"aaa", "aab", "ac", "d"}, "abcd"),
                Arguments.of(new String[]{"a", "ab"}, "ab"),
                Arguments.of(new String[]{"abc", "ab"}, "")
        );
    }

}