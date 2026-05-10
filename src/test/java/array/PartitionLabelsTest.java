package array;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PartitionLabelsTest {

    static Stream<Arguments> testCases() {
        return Stream.of(
                Arguments.of("ababcbacadefegdehijhklij", List.of(9, 7, 8)),
                Arguments.of("eccbbbbdec", List.of(10)),
                Arguments.of("a", List.of(1)),
                Arguments.of("aaaaa", List.of(5)),
                Arguments.of("abcd", List.of(1, 1, 1, 1)),
                Arguments.of("aabb", List.of(2, 2)),
                Arguments.of("abcbad", List.of(5, 1)),
                Arguments.of("abcdefghijklmnopqrstuvwxyza", List.of(27))
        );
    }

    @ParameterizedTest
    @MethodSource("testCases")
    void testSolution(String s, List<Integer> expected) {
        assertEquals(expected, new PartitionLabels.Solution().partitionLabels(s));
    }

    @ParameterizedTest
    @MethodSource("testCases")
    void testSolution2(String s, List<Integer> expected) {
        assertEquals(expected, new PartitionLabels.Solution2().partitionLabels(s));
    }
}
