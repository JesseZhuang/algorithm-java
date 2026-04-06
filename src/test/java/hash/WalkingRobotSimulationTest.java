package hash;

import junit.converter.IntegerArrayConverter;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;
import util.IntArrayUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WalkingRobotSimulationTest {

    @ParameterizedTest
    @CsvSource({
            "'[4,-1,3]', '', 25",
            "'[4,-1,4,-2,4]', '[[2,4]]', 65",
            "'[6,-1,-1,6]', '[[0,0]]', 36",
            "'[-2,-1,-2,-1]', '', 0",
            "'[1]', '', 1",
            "'[4]', '[[0,1]]', 0",
            "'[4,-1,3,-1,2,-1,1]', '', 25",
            "'[-2,3]', '', 9",
            "'[9,-1,9]', '[[0,3],[3,2]]', 8",
    })
    void test(@ConvertWith(IntegerArrayConverter.class) Integer[] commands,
              String obstaclesStr,
              int expected) {
        int[] cmds = IntArrayUtil.unBoxIntegerArray(commands);
        int[][] obs = parseObstacles(obstaclesStr);
        assertEquals(expected, WalkingRobotSimulation.robotSim(cmds, obs));
    }

    private static int[][] parseObstacles(String s) {
        if (s == null || s.trim().isEmpty() || s.trim().equals("[]")) return new int[0][];
        // e.g. "[[2,4]]" or "[[0,3],[3,2]]"
        s = s.replaceAll("^\\[\\[", "").replaceAll("]]$", "");
        String[] pairs = s.split("],\\s*\\[");
        int[][] result = new int[pairs.length][2];
        for (int i = 0; i < pairs.length; i++) {
            String[] parts = pairs[i].split(",\\s*");
            result[i][0] = Integer.parseInt(parts[0].trim());
            result[i][1] = Integer.parseInt(parts[1].trim());
        }
        return result;
    }
}
