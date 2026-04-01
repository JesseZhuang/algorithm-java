package graph;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountsMergeTest {

    @BeforeAll
    static void setup() {
        new AccountsMergeTest();
    }

    private static List<List<String>> normalize(List<List<String>> lists) {
        List<List<String>> copy = new ArrayList<>();
        for (List<String> row : lists) {
            List<String> inner = new ArrayList<>(row);
            String name = inner.remove(0);
            Collections.sort(inner);
            List<String> nr = new ArrayList<>();
            nr.add(name);
            nr.addAll(inner);
            copy.add(nr);
        }
        copy.sort(Comparator.comparing(List::toString));
        return copy;
    }

    @SafeVarargs
    private static List<List<String>> accounts(List<String>... rows) {
        List<List<String>> acc = new ArrayList<>();
        for (List<String> row : rows) {
            acc.add(new ArrayList<>(row));
        }
        return acc;
    }

    private static void assertBoth(List<List<String>> input, List<List<String>> expected) {
        List<List<String>> exp = normalize(expected);
        assertEquals(exp, normalize(AccountsMerge.accountsMergeUF(input)));
        assertEquals(exp, normalize(AccountsMerge.accountsMergeDFS(input)));
    }

    @Test
    void example1() {
        assertBoth(
                accounts(
                        Arrays.asList("John", "johnsmith@mail.com", "john_newyork@mail.com"),
                        Arrays.asList("John", "johnsmith@mail.com", "john00@mail.com"),
                        Arrays.asList("Mary", "mary@mail.com"),
                        Arrays.asList("John", "johnnybravo@mail.com")),
                accounts(
                        Arrays.asList("John", "john00@mail.com", "john_newyork@mail.com", "johnsmith@mail.com"),
                        Arrays.asList("Mary", "mary@mail.com"),
                        Arrays.asList("John", "johnnybravo@mail.com")));
    }

    @Test
    void example2() {
        assertBoth(
                accounts(
                        Arrays.asList("Gabe", "Gabe0@m.co", "Gabe3@m.co", "Gabe1@m.co"),
                        Arrays.asList("Kevin", "Kevin3@m.co", "Kevin5@m.co", "Kevin0@m.co"),
                        Arrays.asList("Ethan", "Ethan5@m.co", "Ethan4@m.co", "Ethan0@m.co"),
                        Arrays.asList("Hanzo", "Hanzo3@m.co", "Hanzo1@m.co", "Hanzo0@m.co"),
                        Arrays.asList("Fern", "Fern5@m.co", "Fern1@m.co", "Fern0@m.co")),
                accounts(
                        Arrays.asList("Ethan", "Ethan0@m.co", "Ethan4@m.co", "Ethan5@m.co"),
                        Arrays.asList("Fern", "Fern0@m.co", "Fern1@m.co", "Fern5@m.co"),
                        Arrays.asList("Gabe", "Gabe0@m.co", "Gabe1@m.co", "Gabe3@m.co"),
                        Arrays.asList("Hanzo", "Hanzo0@m.co", "Hanzo1@m.co", "Hanzo3@m.co"),
                        Arrays.asList("Kevin", "Kevin0@m.co", "Kevin3@m.co", "Kevin5@m.co")));
    }

    @Test
    void singleAccount() {
        assertBoth(
                accounts(Arrays.asList("Alice", "a@m.co")),
                accounts(Arrays.asList("Alice", "a@m.co")));
    }

    @Test
    void allMerge() {
        assertBoth(
                accounts(
                        Arrays.asList("Bob", "b1@m.co", "b2@m.co"),
                        Arrays.asList("Bob", "b2@m.co", "b3@m.co"),
                        Arrays.asList("Bob", "b3@m.co", "b4@m.co")),
                accounts(Arrays.asList("Bob", "b1@m.co", "b2@m.co", "b3@m.co", "b4@m.co")));
    }

    @Test
    void sameNameNoMerge() {
        assertBoth(
                accounts(
                        Arrays.asList("Tom", "t1@m.co"),
                        Arrays.asList("Tom", "t2@m.co")),
                accounts(
                        Arrays.asList("Tom", "t1@m.co"),
                        Arrays.asList("Tom", "t2@m.co")));
    }

    @Test
    void chainMerge() {
        assertBoth(
                accounts(
                        Arrays.asList("Ann", "a@m.co", "b@m.co"),
                        Arrays.asList("Ann", "b@m.co", "c@m.co"),
                        Arrays.asList("Ann", "c@m.co", "d@m.co")),
                accounts(Arrays.asList("Ann", "a@m.co", "b@m.co", "c@m.co", "d@m.co")));
    }
}
