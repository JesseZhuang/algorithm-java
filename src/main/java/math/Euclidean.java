package math;

/**
 * https://en.wikipedia.org/wiki/Euclidean_algorithm
 * <p>
 * Greatest common divisor efficient calculation.
 * <p>
 * The number of steps required for completion is never more than five times the number h of base-10 digits of the
 * smaller number b. Complexity is O(h^2). With strassen's integer multiplication, leads to quasilinear algorithms.
 */
public class Euclidean {
    public static int gcdIterative(int a, int b) {
        if (a < b) return gcdIterative(b, a);
        while (b != 0) {
            int t = b;
            b = a % b;
            a = t;
        }
        return a;
    }

    public static int gcd(int a, int b) {
        if (b == 0) return a;
        if (a < b) return gcd(b, a);
        return gcd(b, a % b);
    }
}
