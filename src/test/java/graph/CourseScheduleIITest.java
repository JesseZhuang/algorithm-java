package graph;

import junit.converter.IntegerArrayConverter;
import junit.converter.TwoDIntegerArrayConverter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class CourseScheduleIITest {
    static CourseScheduleII tbt;

    @BeforeAll
    static void setup() {
        tbt = new CourseScheduleII();
    }

    @ParameterizedTest
    @CsvFileSource(resources = {"/CourseScheduleII.csv"}, delimiter = ';', numLinesToSkip = 2)
    void testFindOrder(int numCourses,
                       @ConvertWith(TwoDIntegerArrayConverter.class) Integer[][] prerequisites,
                       @ConvertWith(IntegerArrayConverter.class) Integer[] order) {
        assertArrayEquals(order, tbt.findOrder(numCourses, prerequisites));
    }

}
