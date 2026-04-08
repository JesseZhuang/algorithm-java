package array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class KthSmallestMatrixTest {

    @Test
    void binarySearchSolution() {
        KthSmallestMatrix solution = new KthSmallestMatrix();
        assertEquals(13, solution.kthSmallest(new int[][]{
                {1, 5, 9},
                {10, 11, 13},
                {12, 13, 15}
        }, 8));
        assertEquals(-5, solution.kthSmallest(new int[][]{
                {-5}
        }, 1));
        assertEquals(1, solution.kthSmallest(new int[][]{
                {1, 2},
                {1, 3}
        }, 2));
        assertEquals(-3, solution.kthSmallest(new int[][]{
                {-10, -5, 0},
                {-8, -3, 2},
                {-6, 1, 4}
        }, 5));
    }

    @Test
    void heapSolution() {
        KthSmallestMatrix solution = new KthSmallestMatrix();
        assertEquals(13, solution.kthSmallestHeap(new int[][]{
                {1, 5, 9},
                {10, 11, 13},
                {12, 13, 15}
        }, 8));
        assertEquals(-5, solution.kthSmallestHeap(new int[][]{
                {-5}
        }, 1));
        assertEquals(5, solution.kthSmallestHeap(new int[][]{
                {1, 2, 3},
                {2, 3, 4},
                {3, 4, 5}
        }, 9));
        assertEquals(1, solution.kthSmallestHeap(new int[][]{
                {1, 2, 3},
                {2, 3, 4},
                {3, 4, 5}
        }, 1));
    }
}
