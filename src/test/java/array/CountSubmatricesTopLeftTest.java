package array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CountSubmatricesTopLeftTest {

    @Test
    void countSubmatricesBasic() {
        int[][] grid = {
                {7, 6, 3},
                {6, 6, 1}
        };
        int k = 18;
        assertEquals(4, new CountSubmatricesTopLeft.Solution().countSubmatrices(grid, k));
    }

    @Test
    void countSubmatricesSingleHit() {
        int[][] grid = {
                {1, 2},
                {3, 4}
        };
        int k = 2;
        assertEquals(1, new CountSubmatricesTopLeft.Solution().countSubmatrices(grid, k));
    }

    @Test
    void countSubmatricesAll() {
        int[][] grid = {
                {0, 1},
                {1, 1}
        };
        int k = 10;
        assertEquals(4, new CountSubmatricesTopLeft.Solution().countSubmatrices(grid, k));
    }
}
