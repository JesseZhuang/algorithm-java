package hash;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProgrammerStringsTest {

    static Stream<Arguments> cases() {
        return Stream.of(
                Arguments.of("programmerxxxprozmerqgram", 3),
                Arguments.of("progxrammerrxproxgrammer", 2),
                Arguments.of("xprogxrmaxemrppprommograeiruu", 2),
                Arguments.of("programmerprogrammer", 0)
        );
    }

    @ParameterizedTest
    @MethodSource("cases")
    void programmerStrings(String s, int exp) {
        assertEquals(exp, ProgrammerStrings.programmerStrings(s));
    }
}
