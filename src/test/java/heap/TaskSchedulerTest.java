package heap;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskSchedulerTest {

    @ParameterizedTest
    @CsvSource({
            "'AAABBB', 2, 8",
            "'ACABDB', 1, 6",
            "'AAABBB', 3, 10",
            "'ABC', 0, 3",
            "'A', 2, 1",
            "'AAA', 2, 7",
            "'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 100, 26",
            "'AAB', 3, 5",
    })
    void test(String tasks, int n, int expected) {
        assertEquals(expected, new TaskScheduler().leastInterval(tasks.toCharArray(), n));
    }
}
