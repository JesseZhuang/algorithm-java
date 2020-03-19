package princeton.jsl;

import edu.princeton.cs.algs4.StdRandom;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code RandomSeq} class is a client that returns a pseudorandom
 * sequence of real numbers in a given range.
 * <p>
 * For additional documentation, see <a href="https://algs4.cs.princeton.edu/11model">Section 1.1</a> of
 * <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 */
public class RandomSeq {

    // this class should not be instantiated
    private RandomSeq() {
    }

    /**
     * Generate a list of random numbers between 0.0 and 1.0.
     *
     * @param n number of random numbers to generate.
     * @return a list of double random numbers.
     */
    public static List<Double> random(int n) {
        List<Double> result = new ArrayList<>();
        for (int i = 0; i < n; i++) result.add(StdRandom.uniform());
        return result;
    }

    /**
     * Generate a list of random numbers between {@code lo} and {@code hi}.
     *
     * @param n  number of random numbers to generate.
     * @param lo lower bound.
     * @param hi higher bound.
     * @return a list of double random numbers.
     */
    public static List<Double> random(int n, double lo, double hi) {
        List<Double> result = new ArrayList<>();
        for (int i = 0; i < n; i++) result.add(StdRandom.uniform(lo, hi));
        return result;
    }

}
