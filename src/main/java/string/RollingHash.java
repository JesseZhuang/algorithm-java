package string;

// cp-algorithms version
@SuppressWarnings("unused")
public class RollingHash {
    static int p = 31, m = (int) 1e9 + 9;
    public long[] h;
    public long[] pow;
    String s;
    int n;

    public RollingHash(String s) {
        this.s = s;
        n = s.length();
        h = new long[n + 1];
        pow = new long[n];
        pow[0] = 1; // s[0]*p^0
        for (int i = 1; i < n; i++) pow[i] = (pow[i - 1] * p) % m; // pow[i]:p^i
        h[0] = 0;
        for (int i = 0; i < n; i++) h[i + 1] = (h[i] + (s.charAt(i) - 'a' + 1) * pow[i]) % m;
    }

    /**
     * s[0]+s[1]*p+s[2]*p^2 ... mod m where p and m are some chosen positive numbers. Sigma(i:[0,n-1])s[i]*p^i mod m
     * is the polynomial rolling hash function.
     *
     * @param s input string.
     * @return the hash of the string.
     */
    public static long hash(String s) {
        long hash = 0, pow = 1;
        for (int i = 0; i < s.length(); i++) {
            hash = (hash + (s.charAt(i) - 'a' + 1) * pow) % m;
            pow = pow * p % m;
        }
        return hash;
    }

    /**
     * Take from the pre-calculated h[] array.
     *
     * @return rolling hash of the string from the constructor.
     */
    public long hash() {
        return h[n];
    }

    /**
     * Fast calculation: with knowing hash of each prefix of string s, we can compute hash of any substring
     * directly by using the formula:
     * hash(s[i,j])*p^i=sigma(k:[i,j])s[k]*p^k mod m = hash(s[0,j])-hash(s[0,i-1]) mod m
     * <p>
     * we can precompute the inverse of every p^i, then the calculation is O(1) time.
     * In practice, if two substrings one multiplied by p^a and the other p^b, assume a<b, then we multiple the
     * first one by p^(b-a) now the two hashes can be compared easily with no need for any division.
     * todo: implement other applications with cp-algorithms
     *
     * @param s input string.
     * @param i starting index, inclusive.
     * @param j ending index, inclusive.
     * @return the rolling hash for the substring s[i,j].
     */
    public long substringHash(String s, int i, int j) {
        long hash = (h[j + 1] + m - h[i]) % m; // +m to avoid negative
        hash = (hash * pow[n - i - 1]) % m; // all normalize to p^(n-1)
        return hash;
    }

}
