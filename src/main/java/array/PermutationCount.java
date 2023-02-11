package array;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Input: list of integers of value in [1,9] inclusive. Can assume no invalid numbers in the list.
 * Expected output: count of possible permutations.
 * Example test cases:
 * <pre>
 * 1,2 -> 2 ([1,2],[2,1])
 * 2,2 -> 1 ([2,2])
 * 3,1,1,1 -> 4([3,1,1,1],[1,3,1,1],[1,1,3,1],[1,1,1,3])
 * </pre>
 */
public class PermutationCount {

    /**
     * Calculates factorial.
     *
     * @param n input number.
     * @return the factorial.
     */
    private int factorial(int n) {
        if (n < 1) throw new IllegalArgumentException("invalid integer to calculate factorial");
        int result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    /**
     * Count of permutation is n!/(n1!n2!...nk!), k in [1,9] nk is the count of k in the list.
     *
     * @param numbers input list.
     * @return count of possible permutations.
     */
    private int permutationCount(List<Integer> numbers) {
        int n = numbers.size();
        if (n == 0) return 1;
        int[] counts = new int[10];
        for (int number : numbers) {
            counts[number]++;
        }
        int denominator = 1;
        for (int i = 1; i <= 9; i++) {
            if (counts[i] > 1) {
                denominator *= factorial(counts[i]);
            }
        }
        return factorial(n) / denominator;
    }

    public int permutationCount(Integer[] numbers) {
        return permutationCount(Arrays.asList(numbers));
    }

    /**
     * unit tests.
     */
    public static void main(String[] args) {
        PermutationCount tbt = new PermutationCount();
        List<List<Integer>> cases = new ArrayList<>();
        cases.add(Arrays.asList(1, 2));
        cases.add(Arrays.asList(2, 2));
        cases.add(Arrays.asList(3, 1, 1, 1));
        cases.add(Arrays.asList(1, 1, 2, 3));
        cases.add(new ArrayList<>());

        for (List<Integer> list : cases) {
            System.out.println(tbt.permutationCount(list));
        }
    }
}
