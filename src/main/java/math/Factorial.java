package math;

import java.math.BigInteger;
import java.util.ArrayList;

@SuppressWarnings("unused")
public class Factorial {
    ArrayList<Integer> memo;
    ArrayList<BigInteger> memo2;

    public Factorial() {
        memo = new ArrayList<>();
        memo.add(1); // 0!==1
        memo.add(1); // 1!==1
        memo2 = new ArrayList<>();
        memo2.add(BigInteger.ONE);
        memo2.add(BigInteger.ONE);
    }

    // iterative
    public int factorialInt(int n) {
        if (n < 0) throw new IllegalArgumentException("invalid integer for factorial");
        if (n < memo.size()) return memo.get(n);
        while (memo.size() <= n)
            memo.add(memo.size() * memo.getLast());
        return memo.getLast();
    }

    public BigInteger factorialBig(int n) {
        if (n <= 12) return BigInteger.valueOf(factorialInt(n));
        if (n < memo2.size()) return memo2.get(n);
        while (memo2.size() <= n)
            memo2.add(memo2.getLast().multiply(BigInteger.valueOf(memo2.size())));
        return memo2.getLast();
    }
}
