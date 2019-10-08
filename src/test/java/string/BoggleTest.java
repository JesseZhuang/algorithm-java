package string;

import com.google.common.collect.Sets;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BoggleTest {
    Boggle tbt;

    @ParameterizedTest
    @MethodSource("testCases")
    void testValidWords(Character[][] characters, String[] dictionary, String[] validWords) {
        tbt = new Boggle(characters, dictionary);
        Set<String> expected = Sets.newHashSet(validWords);
        System.out.println(tbt.resultList);

        assertTrue(expected.containsAll(tbt.resultSet));
        assertTrue(tbt.resultSet.containsAll(expected));
    }

    static Stream<Arguments> testCases() {
        return Stream.of(
                Arguments.of(new Character[][]{
                        {'A', 'N', 'D'},
                        {'A', 'D', 'Z'},
                        {'B', 'E', 'E'}
                }, new String[]{
                        "BAD", "BEE", "AN", "AD", "AND", "BED", "SAD", "ADD", "BE", "MAD"
                }, new String[]{
                        "BAD", "BEE", "AN", "AD", "AND", "BED", "BE", "ADD"
                })
        );
    }
}
