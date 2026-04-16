package stack;

import org.junit.jupiter.api.Test;

import java.util.function.BiFunction;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class MinStackTest {

    void verify(BiFunction<String[], int[][], int[]> solver) {
        // Example 1
        assertArrayEquals(
                new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, -3, Integer.MIN_VALUE, 0, -2},
                solver.apply(
                        new String[]{"MinStack", "push", "push", "push", "getMin", "pop", "top", "getMin"},
                        new int[][]{{}, {-2}, {0}, {-3}, {}, {}, {}, {}}
                )
        );
        // Single element
        assertArrayEquals(
                new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE, 42, 42},
                solver.apply(
                        new String[]{"MinStack", "push", "top", "getMin"},
                        new int[][]{{}, {42}, {}, {}}
                )
        );
        // Decreasing then pop
        assertArrayEquals(
                new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 1, Integer.MIN_VALUE, 2, Integer.MIN_VALUE, 3},
                solver.apply(
                        new String[]{"MinStack", "push", "push", "push", "getMin", "pop", "getMin", "pop", "getMin"},
                        new int[][]{{}, {3}, {2}, {1}, {}, {}, {}, {}, {}}
                )
        );
        // Large then small
        assertArrayEquals(
                new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, -1000, Integer.MIN_VALUE, 1000},
                solver.apply(
                        new String[]{"MinStack", "push", "push", "getMin", "pop", "getMin"},
                        new int[][]{{}, {1000}, {-1000}, {}, {}, {}}
                )
        );
    }

    @Test
    void twoStacks() {
        verify(MinStack::twoStacks);
    }

    @Test
    void singleStack() {
        verify(MinStack::singleStack);
    }
}
