package string;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class OptimalPartitionOfStringTest {

    private void assertPartitions(int expected, String s) {
        assertAll(
                () -> assertEquals(expected, OptimalPartitionOfString.partitionString(s), "Hash set"),
                () -> assertEquals(expected, OptimalPartitionOfString.partitionStringBitmask(s), "Bitmask"));
    }

    @Test
    void startsNewPartitionsAtRepeats() {
        assertPartitions(4, "abacaba");
    }

    @Test
    void putsEachRepeatedLetterInItsOwnPartition() {
        assertPartitions(6, "ssssss");
    }

    @Test
    void countsSingleCharacterAsOnePartition() {
        assertPartitions(1, "s");
    }

    @Test
    void keepsAllDistinctLettersTogether() {
        assertPartitions(1, "abcdefghijklmnopqrstuvwxyz");
    }

    @Test
    void clearsPreviouslySeenLettersAfterSplit() {
        assertPartitions(2, "abcdefghijklmnopqrstuvwxyzabc");
    }

    @Test
    void handlesMaximumLength() {
        assertPartitions(100_000, "a".repeat(100_000));
    }
}
