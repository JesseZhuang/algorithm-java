package array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CountSubmatricesEqualFreqXYTest {

    private static final char[][][] GRIDS = {
            {{'X', 'Y', '.'}, {'Y', '.', '.'}},
            {{'X', 'X'}, {'X', 'Y'}},
            {{'.', '.'}},
            {{'X', 'Y'}},
            {{'X'}, {'Y'}},
            {{'Y', 'X'}},
            {{'X'}},
            {{'Y'}},
            {{'.', 'X', '.', 'Y'}},
            {{'X', '.', 'Y', '.', 'X', 'Y'}},
            {{'X', 'Y'}, {'Y', 'X'}},
            {{'.', '.'}, {'.', '.'}},
            {{'X', 'Y', 'X'}, {'Y', 'X', 'Y'}, {'X', 'Y', 'X'}},
    };

    private static final int[] EXPECTED = {
            3, 0, 0, 1, 1, 1, 0, 0, 1, 3, 3, 0, 5,
    };

    @Test
    void numberOfSubmatricesAllCases() {
        CountSubmatricesEqualFreqXY sol = new CountSubmatricesEqualFreqXY();
        for (int i = 0; i < EXPECTED.length; i++) {
            assertEquals(
                    EXPECTED[i],
                    sol.numberOfSubmatrices(GRIDS[i]),
                    "case index " + i);
        }
    }
}
