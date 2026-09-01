package dp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TriangleTest {
    Triangle tbt;

    @BeforeEach
    void setUp() {
        tbt = new Triangle();
    }

    @Test
    void testDP() {
        assertEquals(11, tbt.minimumTotal(List.of(List.of(2), List.of(3, 4), List.of(6, 5, 7), List.of(4, 1, 8, 3))));
        assertEquals(-10, tbt.minimumTotal(List.of(List.of(-10))));
        assertEquals(3, tbt.minimumTotal(List.of(List.of(1), List.of(2, 3))));
        assertEquals(-1, tbt.minimumTotal(List.of(List.of(-1), List.of(2, 3), List.of(1, -1, -3))));
        assertEquals(0, tbt.minimumTotal(List.of(List.of(0), List.of(0, 0), List.of(0, 0, 0))));
        assertEquals(-600, tbt.minimumTotal(List.of(List.of(100), List.of(-200, 300), List.of(400, -500, 600))));
        assertEquals(5, tbt.minimumTotal(List.of(List.of(1), List.of(2, 3), List.of(4, 3, 1))));
    }

    @Test
    void testMemo() {
        assertEquals(11, tbt.minimumTotalMemo(List.of(List.of(2), List.of(3, 4), List.of(6, 5, 7), List.of(4, 1, 8, 3))));
        assertEquals(-10, tbt.minimumTotalMemo(List.of(List.of(-10))));
        assertEquals(3, tbt.minimumTotalMemo(List.of(List.of(1), List.of(2, 3))));
        assertEquals(-1, tbt.minimumTotalMemo(List.of(List.of(-1), List.of(2, 3), List.of(1, -1, -3))));
        assertEquals(0, tbt.minimumTotalMemo(List.of(List.of(0), List.of(0, 0), List.of(0, 0, 0))));
        assertEquals(-600, tbt.minimumTotalMemo(List.of(List.of(100), List.of(-200, 300), List.of(400, -500, 600))));
        assertEquals(5, tbt.minimumTotalMemo(List.of(List.of(1), List.of(2, 3), List.of(4, 3, 1))));
    }
}
