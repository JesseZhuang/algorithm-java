package util;

/**
 * Ideas seen in cpp LeetCode or competitive programming codes.
 */
@SuppressWarnings("unused")
public class CP {
    public static <T extends Comparable<T>> Pair<T, Boolean> chmin(T a, T b) {
        if (b.compareTo(a) < 0)
            return new Pair<>(b, true);
        else return new Pair<>(a, false);
    }

    public static <T extends Comparable<T>> Pair<T, Boolean> chmax(T a, T b) {
        if (b.compareTo(a) > 0)
            return new Pair<>(b, true);
        else return new Pair<>(a, false);
    }
}
