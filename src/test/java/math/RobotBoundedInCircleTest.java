package math;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RobotBoundedInCircleTest {

    @Test
    void testIsRobotBounded() {
        assertEquals(true, RobotBoundedInCircle.isRobotBounded("GGLLGG"));
        assertEquals(false, RobotBoundedInCircle.isRobotBounded("GG"));
        assertEquals(true, RobotBoundedInCircle.isRobotBounded("GL"));
    }
}
