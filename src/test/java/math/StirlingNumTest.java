package math;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StirlingNumTest {

    @Test
    void testFirst() {
        int[][] inputs = {
                {4, 1},
                {4, 2},
                {5, 2},
                {5, 3},
                {6, 2},
                {6, 3},
                {6, 4},
                {6, 5},
                {6, 6},
                {6, 7},
                {9, 3},
                {9, 4},
                {10, 4},
                {10, 5},
        };
        int[] exp = {
                6,
                11,
                50,
                35,
                274,
                225,
                85,
                15,
                1,
                0,
                118124,
                67284,
                723680,
                269325,
        };
        for (int i = 0; i < inputs.length; i++) {
            assertEquals(exp[i], StirlingNum.First.dp(inputs[i][0], inputs[i][1]));
            assertEquals(exp[i], StirlingNum.First.recur(inputs[i][0], inputs[i][1]));
        }
    }

    @Test
    void testSecond() {
        int[][] inputs = {
                {4, 1},
                {4, 2},
                {5, 2},
                {5, 3},
                {6, 2},
                {6, 3},
                {6, 4},
                {6, 5},
                {6, 6},
                {6, 7},
                {9, 3},
                {9, 4},
                {10, 4},
                {10, 5},
        };
        int[] exp = {
                1,
                7,
                15,
                25,
                31,
                90,
                65,
                15,
                1,
                0,
                3025,
                7770,
                34105,
                42525,
        };
        for (int i = 0; i < inputs.length; i++) {
            assertEquals(exp[i], StirlingNum.Second.dp(inputs[i][0], inputs[i][1]));
            assertEquals(exp[i], StirlingNum.Second.recur(inputs[i][0], inputs[i][1]));
            assertEquals(exp[i], StirlingNum.Second.iter1(inputs[i][0], inputs[i][1]));
            assertEquals(exp[i], StirlingNum.Second.iter2(inputs[i][0], inputs[i][1]));
        }
    }
}