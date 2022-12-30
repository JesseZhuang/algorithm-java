package graph;

import junit.converter.TwoDIntegerArrayConverter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvFileSource;
import util.IntArrayUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CourseScheduleTest {
    static CourseSchedule tbt;

    @BeforeAll
    static void setup() {
        tbt = new CourseSchedule();
    }

    @ParameterizedTest
    @CsvFileSource(numLinesToSkip = 2, delimiter = ';', resources = {"/CourseSchedule.csv"})
    void testCanFinish(int n, @ConvertWith(TwoDIntegerArrayConverter.class) Integer[][] edges, boolean canFinish) {
        assertEquals(canFinish, tbt.canFinishCycle(n, IntArrayUtil.unbox2DIntegerArray(edges)));
        assertEquals(canFinish, tbt.canFinishBFS(n, IntArrayUtil.unbox2DIntegerArray(edges)));
        assertEquals(canFinish, tbt.canFinishDFS(n, IntArrayUtil.unbox2DIntegerArray(edges)));
    }
}
