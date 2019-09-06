package string;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * LeetCode 28. Easy. Tags: Two Pointer, String.
 * <p>
 * Returns the index of the first occurrence of needle in haystack, or -1 if
 * needle is not part of haystack.
 * <p>
 * Tags: Two pointers, String.
 * <p>
 * <b>Summary:</b>
 * <p>
 * <ul>
 * <li>brute force, worst case O(MN) time, O(1) space, in practice often M + N,
 * 5 ms.
 * <li>KMP-DFA, O(MR+N) 2N time, O(MR) space, 49 ms DFA calculation expensive.
 * <li>KMP O(M+N) 3N or 2N time, O(M) space, still slower than brute force,
 * build table expensive, 9 ms.
 * <li><b>Boyer-Moore, mismatch heuristic only, time O(R+M+MN) worst case, O(R)
 * space, 4 ms.</b>
 * <li>Boyer-Moore full algorithm, time O(M+R+N) worst case, O(R+2M) space, 6
 * ms.
 * <li>Boyer-Moore bad character rule v2, time O(M+2R+MN) worst case, O(R+M)
 * space, 8 ms.
 * <li>Boyer-Moore bad character rule 2D array, time O(MR+MN) worst case, O(RM)
 * space, time limit exceeded.
 * <li>Rabin-Karp Monte Carlo version 7N, hash calculations expensive 46 ms.
 * </ul>
 */
public class ImplementStrStr {

  /** 46 ms */
  public int RabinKarpMC(String haystack, String needle) {
    if (needle.length() == 0) return 0;
    // without line below fail for "", "a" and "aaa", "aaaa"
    if (haystack.length() < needle.length()) return -1;
    int R = 128, m = needle.length();
    // 31 bit long prime number
    long Q = BigInteger.probablePrime(31, new Random()).longValue();
    long RM = 1; // R^(m-1)%Q
    for (int i = 1; i < m; i++) {
      RM = RM * R % Q;
    }
    long needleHash = hash(needle, m, R, Q);
    long haystackHash = hash(haystack, m, R, Q);

    for (int i = m; i < haystack.length(); i++) {
      if (haystackHash == needleHash) return i - m;
      else {
        haystackHash = (haystackHash + Q - haystack.charAt(i - m) * RM % Q) % Q;
        haystackHash = (haystackHash * R + haystack.charAt(i)) % Q;
      }
    }
    if (haystackHash == needleHash) return haystack.length() - m;
    return -1;
  }

  private long hash(String key, int m, int R, long Q) {
    long res = 0;
    for (int i = 0; i < m; i++)
      res = (res * R + key.charAt(i)) % Q;
    return res;
  }

  /** 6 ms */
  public int BMFULL(String haystack, String needle) {
    int m = needle.length();
    if (m == 0) return 0;
    int[] right = new int[128];
    Arrays.fill(right, -1); // O(R)
    // good suffix rule never allow skip < 0 so mismatch heuristic enough
    for (int j = 0; j < m; j++)
      right[needle.charAt(j)] = j; // O(m)
    int[] L = new int[m];
    int[] H = new int[m];
    int start = m - 1;
    /*
     * the skip is going to be goodSuffix[i], which is m-H[i] or m-L[i] below.
     * H[i] should only be used if L[i] is 0 or a match has been found. If
     * mismatch when j at m-1, good suffix rule should propose a skip of 0 and
     * let bad character rule take charge. So the default value should be m.
     */
    /*
     * adapted from wiki, H[i} denote the length of the largest suffix of
     * needle[(i+1)..(m-1)] that is also a prefix of needle. H[i] be zero if
     * none such suffix exists. H[i] should only be used if L[i] is 0 or a match
     * has been found.
     */
    /*
     * adapted from wiki, L[i]-1 is the largest position less than m-1 such that
     * needle[(i+1)..(m-1)] matches a suffix of needle[0..(L[i]-1)] and such
     * that the character preceding that suffix is not equal to needle[i]. L[i]
     * is 0 if no position satisfying the condition. Build the restart table
     * backwards to help. We need to have some matches followed by a mismatch
     * while building this table.
     */
    int[] backwardsRestart = new int[m]; // O(m)
    for (int i = 1, j = 0; i < m;) {
      if (needle.charAt(start - i) == needle.charAt(start - j)) {
        backwardsRestart[start - i] = ++j;
        ++i;
        if (i == m) {
          // for (int k = 0; k < m - j; k++)
          // H[k] = j; H[start-k] = Math.min(k,j);
          /*
           * backwardsRestart[i] > 0 denotes repeating pattern, fill the
           * H[i..m-len-1] with this repeating pattern's length.
           */
          int k = 0;
          while (k < m && backwardsRestart[k] > 0) {
            int len = backwardsRestart[k];
            while (k < m - len) {
              H[k++] = len;
            }
          }
        }
      } else if (j == 0) i++;
      else {
        // need a mismatch
        for (int k = j; k != 0; k = backwardsRestart[m - k]) {
          if (L[start - k] == 0) L[start - k] = m - i + k;
        }
        j = backwardsRestart[m - j];
      }
    }
    // System.out.println(
    // needle + Arrays.stream(H).boxed().collect(Collectors.toList()));
    /*
     * if mismatch at first j(m-1), goodsuffix rule should propose a shift of 0.
     */
    L[start] = H[start] = m;
    // search, worst case O(M)
    int skip, galil = 0, i = 0, j = start;
    /*
     * Galil rule: If after a mismatch, H[i] is used to shift the needle,
     * needle[0..H[i]] must match a suffix of needle[(i+1)..(m-1)], which also
     * matches the same suffix already matched in haystack. Thus the comparison
     * only has to go down to H[i].
     */
    while (i < haystack.length() - start && j >= galil) {
      if (haystack.charAt(i + j) != needle.charAt(j)) {
        skip = j - right[haystack.charAt(i + j)];
        int goodSuffix = L[j] == 0 ? m - H[j] : m - L[j];
        if (goodSuffix >= skip) {
          skip = goodSuffix;
          if (L[j] == 0) {
            galil = H[j];
          }
        }
        if (L[j] != 0 && galil != 0) galil = 0;
        i += skip;
        j = start;
      } else j--;
    }
    if (i >= haystack.length() - start) return -1;
    else return i;
  }

  /** bad character rule version 2, 8 ms. O(M+2R+MN) */
  public int strStrBMBC2(String haystack, String needle) {
    if (needle.length() == 0) return 0;
    int R = 128; // assume only ascii
    int[] nextRight = new int[needle.length()];
    // right most table
    int[] right = new int[R];
    Arrays.fill(right, -1); // O(R)
    int[] position = new int[R];
    Arrays.fill(position, -1); // O(R)
    /*
     * O(M) to construct nextRight table, but will be slow if many repeating
     * characters on the right of the mismatched j index during searching.
     */
    for (int j = 0; j < needle.length(); j++) {
      right[needle.charAt(j)] = j;
      nextRight[j] = position[needle.charAt(j)];
      position[needle.charAt(j)] = j;
    }
    position = null;
    // search, worst case O(MN)
    int skip;
    for (int i = 0; i < haystack.length() - needle.length() + 1; i += skip) {
      skip = 0;
      for (int j = needle.length() - 1; j >= 0; j--)
        if (haystack.charAt(i + j) != needle.charAt(j)) {
          int rmp = right[haystack.charAt(i + j)];
          skip = j - rmp;
          while (skip < 1) {
            rmp = nextRight[rmp];
            skip = j - rmp;
          }
          break;
        }
      if (skip == 0) return i;
    }
    return -1;
  }

  /**
   * bad character rule, time limit exceeded for 32316 a for hasystack, 32315a1b
   * for needle.
   */
  public int strStrBMBC1(String haystack, String needle) {
    if (needle.length() == 0) return 0;
    int R = 128; // assume only ascii
    /*
     * use a 2D array, first index each character in the alphabet, second index
     * position in the needle. O(MR) time and O(MR) space to construct. For
     * large M, this is very expensive but provides constant time look up.
     */
    int[][] right = new int[needle.length()][R];
    for (int i = 0; i < needle.length(); i++) {
      Arrays.fill(right[i], -1);// syntactic sugar of for loop
      for (int j = 0; j <= i; j++)
        right[i][needle.charAt(j)] = j;
    }
    // search, worst case O(MN)
    int skip;
    for (int i = 0; i < haystack.length() - needle.length() + 1; i += skip) {
      skip = 0;
      for (int j = needle.length() - 1; j >= 0; j--)
        if (haystack.charAt(i + j) != needle.charAt(j)) {
          skip = j - right[j][haystack.charAt(i + j)];
          break;
        }
      if (skip == 0) return i;
    }
    return -1;
  }

  /** 4 ms mismatch character heuristic */
  public int strStrBMMH(String haystack, String needle) {
    if (needle.length() == 0) return 0;
    // assuming ascii only R = 128
    int R = 128;
    int[] right = new int[R];
    Arrays.fill(right, -1); // O(R)
    for (int j = 0; j < needle.length(); j++)
      right[needle.charAt(j)] = j; // O(M)
    // search, worst case O(MN)
    int skip;
    for (int i = 0; i < haystack.length() - needle.length() + 1; i += skip) {
      skip = 0;
      for (int j = needle.length() - 1; j >= 0; j--)
        if (needle.charAt(j) != haystack.charAt(i + j)) {
          skip = j - right[haystack.charAt(i + j)];
          if (skip < 1) skip = 1;
          break;
        }
      if (skip == 0) return i;
    }
    return -1;
  }

  /** 9 ms should be equivalent to wikipedia version */
  public int strStrKMP(String haystack, String needle) {
    // if (needle.length() == 0) return 0; no need this line
    int[] restart = restartTable(needle);
    int i, j;
    for (i = 0, j = 0; i < haystack.length() && j < needle.length();) {
      if (haystack.charAt(i) == needle.charAt(j)) {
        i++;
        j++;
      } else if (j == 0) i++;
      // mismatch, back up j and compare with i again
      else j = restart[j - 1];
    }
    if (j == needle.length()) return i - j;
    return -1;
  }

  /** 49 ms */
  public int strStrKMPDFA(String haystack, String needle) {
    // assuming only ASCII characters
    // if (needle.length() == 0) return -1; wrong "" "" expected 0
    if (needle.length() == 0) return 0;// "a" "" expected 0
    // might be a very sparse 2D matrix
    int[][] dfa = new int[128][needle.length()];
    // this line causes index out of range exception if needle length 0
    dfa[needle.charAt(0)][0] = 1;
    for (int x = 0, j = 1; j < needle.length(); j++) {
      for (int i = 0; i < 128; i++)
        dfa[i][j] = dfa[i][x];
      dfa[needle.charAt(j)][j] = j + 1;
      x = dfa[needle.charAt(j)][x];
    }
    int i, j;
    for (i = 0, j = 0; i < haystack.length() && j < needle.length(); i++)
      j = dfa[haystack.charAt(i)][j];
    if (j == needle.length()) return i - j;
    return -1;
  }

  /** 5 ms */
  public int strStrBF(String haystack, String needle) {
    for (int i = 0; i < haystack.length() - needle.length() + 1; i++) {
      int j = 0;
      for (; j < needle.length(); j++) {
        if (haystack.charAt(i + j) != needle.charAt(j)) break;
      }
      if (j == needle.length()) return i;
    }
    return -1;
  }

  /** O(M) time (at most 2M loops), O(M) space */
  @SuppressWarnings("unused")
  private int[] restartTable2(String needle) {
    int[] restart = new int[needle.length()];
    for (int i = 1, j = 0; i < needle.length();) {
      if (needle.charAt(i) == needle.charAt(j)) {
        restart[i] = ++j;
        i++;
      } else if (j == 0) i++;// nowhere to back up
      else j = restart[j - 1];
    }
    return restart;
  }

  /** O(M) time (at most 2M loops), O(M) space */
  private int[] restartTable(String needle) {
    int[] restart = new int[needle.length()];
    for (int i = 1, j = 0; i < needle.length();) {
      if (needle.charAt(i) == needle.charAt(j)) {
        ++j;
        i++;
        // restart[m-1] is never used in KMP
        // if (i == needle.length() - 1) restart[i] = j;
      } else if (j == 0) i++;// nowhere to back up
      else {
        // no if version passed leetcode
        // only has to fallback if there is a mismatch
        if (restart[i - 1] == 0) restart[i - 1] = j;
        j = restart[j - 1];
      }
    }
    return restart;
  }

  /**
   * can be used for checking whether needle[p..end] is a prefix of needle but
   * for needle "aaaa" takes O(M^2) time, since every p would take m-1-p
   * compares and then return true.
   */
  @SuppressWarnings("unused")
  private boolean isPrefix(char[] needle, int p) {
    for (int i = p, j = 0; i < needle.length; ++i, ++j) {
      if (needle[i] != needle[j]) {
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {

    ImplementStrStr i1 = new ImplementStrStr();

    Object[][] inputs = { { "aaaaaacccaaaaaccaaa", "aaaccaaa" },
      { "bbababaaaababbaabbbabbbaaabbbaaababbabaabbaaaaabbaaabbbbaaabaabbaababbbaabaaababbaaabbbbbbaabbbbbaaabbababaaaaabaabbbababbaababaabbaa",
        "bbabba" },
      { "aaabbbaabbabaaabbabbaabbaabbbaabaababbabaaabbbaabbbbbaabbbaaaaaaababaaaabbbaababababbaaababbabaaaaaabaaaba",
        "bbaaaba" },
      { "a", "" }, { "", "" }, { "abc", "b" }, { "xxxxnabcmabc", "nabcmabc" },
      { "xxxxbaabaabaaabbaaaa", "baaaa" }, { "aaaa", "baaa" },
      { "mississippi", "issi" }, { "aabaabbbaabbbbabaaab", "abaa" } };
    Integer[] expected = { 11, -1, 83, 0, 0, 1, 4, 15, -1, 1, 1 };

    // int[] old = { 1, 2, 3, 4, 5 };

    Integer[] restable = IntStream.of(i1.restartTable("aabaab")).boxed()
        .toArray(Integer[]::new);

    System.out.println(Arrays.asList(restable));

    System.out.println(Arrays.stream(i1.restartTable("AABAAC")).boxed()
        .collect(Collectors.toList()));

    System.out.println("start real testing");

//    Test.assertMPEqual("easy.ImplementStrStr", "BMFULL", inputs, expected);
//    Test.assertMPEqual("easy.ImplementStrStr", "strStrBMBC1", inputs, expected);
//    Test.assertMPEqual("easy.ImplementStrStr", "strStrBMBC2", inputs, expected);
//    Test.assertMPEqual("easy.ImplementStrStr", "strStrKMP", inputs, expected);
//    Test.assertMPEqual("easy.ImplementStrStr", "RabinKarpMC", inputs, expected);
  }
}
