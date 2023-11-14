package array;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CountInversionsTest {

    @Test
    void testSolutions() {
        int[][] tests = {
                {5, 2, 6, 1}, // 2,1,1,0
                {-1, -1}, // 0,0
                {1, 2, 7, 8, 5}, // 0,0,1,1,0
                {3, 4, 1, 2}, // 2,2,0,0
                {739, 5635, 3182, 3135, 6241, -4300, 9806, 1576, -4212, -9211, 9687, -9930, -4595, -2460, 327, 6413, -9680, -1423, 9038, -8381, -782, -868, 2634, 303, 3902, 1590, -8316, 9615, -7211, -9639, -7223, 10000, -8241, 4091, -3711, 2344, 9859, 9170, -8523, 2567, -8340, 6027, 7357, 5287, 8987, 4628, 8729, 4242, -6264, -7325, -8753, 1698, 6597, 7190, 9520, 9577, 4170, -394, -1438, -9903, 22, 7089, 8784, 3991, 7011, 6907, -4578, -2589, 7006, 6554, 4422, -6224, 7160, 7156, 5160, 4363, -9279, 8681, -9308, 8490, 1498, -9139, -3227, 8215, 8537, 4804, -5620, -2734, 2215, 170, 8272, 1802, 7566, -3316, 9240, 8915, 3211, -2897, -7283, 5801}
        };
        for (int[] n : tests) {
            List<Integer> res1 = CountInversions.countSmallerBIT(n);
            List<Integer> res2 = CountInversions.countSmallerST(n);
            List<Integer> res3 = CountInversions.countSmallerMS(n);
//            for (int i = 0; i < res1.size(); i++) {
//                if (res1.get(i) != res2.get(i))
//                    System.out.println(String.format("i: %d, res1: %d, res2: %d, n: %d",
//                            i, res1.get(i), res2.get(i), n[i]));
//            }
            assertEquals(res1, res2);
            assertEquals(res1, res3);
            System.out.println(edu.princeton.cs.algs4.Inversions.count(n)); // total of above, 4, 0, 2, 4, 2277
        }
    }

}