package graph;

import edu.princeton.cs.algs4.CC;
import edu.princeton.cs.algs4.Graph;

import java.util.Arrays;

import static java.util.stream.IntStream.range;

/**
 * LeetCode 839. Hard. Tags: DFS, Union Find, Graph.
 * <p>
 * Two strings X and Y are similar if we can swap two letters (in different positions) of X, so that it equals Y.
 * <p>
 * For example, "tars" and "rats" are similar (swapping at positions 0 and 2), and "rats" and "arts" are similar,
 * but "star" is not similar to "tars", "rats", or "arts".
 * <p>
 * Together, these form two connected groups by similarity: {"tars", "rats", "arts"} and {"star"}.
 * Notice that "tars" and "arts" are in the same group even though they are not similar.
 * Formally, each group is such that a word is in the group if and only if it is similar to at least
 * one other word in the group.
 * <p>
 * We are given a list A of strings.  Every string in A is an anagram of every other string in A.
 * How many groups are there?
 * <p>
 * Example 1:
 * <p>
 * Input: ["tars","rats","arts","star"]
 * Output: 2
 * Note:
 * <p>
 * <ul>
 * <li>A.length <= 2000
 * <li>A[i].length <= 1000
 * <li>A.length * A[i].length <= 20000
 * <li>All words in A consist of lowercase letters only.
 * <li>All words in A have the same length and are anagrams of each other.
 * <li>The judging time limit has been increased for this question.
 * <p>
 * <b>Summary</b>:
 * <p> N number of strings, l length of string. worse case aaaa, aaaa need to compare all characters
 * <ul>
 * <li>union find, O((Nl)^2) time, O(N) space. 2910 ms 6.74%, 41.4 MB 28.41%.
 * </ul>
 */
public class SimilarStringGroups {
    public int numSimilarGroupsUF(String[] A) {
        WeightedQuickUnionPathCompressionUF uf = new WeightedQuickUnionPathCompressionUF(A.length);
        for (int i = 1; i < A.length; i++) {
            for (int j = 0; j < i; j++) {
                if (isSimilar(A[i], A[j])) uf.union(i, j);
            }
        }
        return uf.count();
    }

    public int numSimilarGroupsCC(String[] A) {
        Graph g = new Graph(A.length);
        for (int i = 1; i < A.length; i++)
            for (int j = 0; j < i; j++)
                if (isSimilar(A[i], A[j])) g.addEdge(i, j);
        CC cc = new CC(g);
        return cc.count();
    }

    /**
     * wrong. see test cases.
     */
    public int wrong(String[] A) {
        int count = A.length;
        for (int i = 1; i < A.length; i++)
            for(int j = 0; j < i; j++)
                if (isSimilarWrong(A[i], A[j])) count--;
        return count;
    }

    // wrong for dvloivlk, dlvoilvk
    public boolean isSimilarWrong(String s1, String s2) {
        int N = s1.length();
        int count = 0; // swaps needed
        int[] map = new int[26]; // 26 lower case letters
        Arrays.fill(map, -1);
        for(int i = 0; i < N; i++) {
            int c1 = s1.charAt(i) - 'a', c2 = s2.charAt(i) - 'a';
            if (c1 != c2) {
                if(map[c1] == -1) {
                    map[c1] = c2;
                    map[c2] = c1;
                    count++;
                    if (count > 1) return false;
                }
                else if (map[c1] != c2) return false;
            }
        }
        return true;
    }

    public boolean isSimilar(String s1, String s2) {
        return range(0, s1.length()).filter(i -> s1.charAt(i) != s2.charAt(i)).count() <= 2;
    }
}
