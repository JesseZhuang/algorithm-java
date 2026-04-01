package graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * LeetCode 721. Medium. Tags: Union Find, DFS, Graph.
 * <p>
 * Given a list of accounts where each element is {@code [name, email1, email2, ...]}, merge accounts that share
 * at least one email. Each merged row is {@code [name, sorted unique emails...]}.
 */
public final class AccountsMerge {

    private AccountsMerge() {
    }

    /**
     * Union-find with union by rank and path halving
     * ({@code parent.put(x, parent.get(parent.get(x)))} on the find path).
     * <p>
     * Time O(nk·α(nk) + nk·log(nk)), space O(nk).
     */
    public static List<List<String>> accountsMergeUF(List<List<String>> accounts) {
        HashMap<String, String> parent = new HashMap<>();
        HashMap<String, Integer> rank = new HashMap<>();
        HashMap<String, String> emailToName = new HashMap<>();

        for (List<String> account : accounts) {
            String name = account.get(0);
            for (int i = 1; i < account.size(); i++) {
                String email = account.get(i);
                emailToName.putIfAbsent(email, name);
                parent.putIfAbsent(email, email);
                rank.putIfAbsent(email, 0);
            }
        }

        for (List<String> account : accounts) {
            if (account.size() <= 2) {
                continue;
            }
            String first = account.get(1);
            for (int i = 2; i < account.size(); i++) {
                union(parent, rank, first, account.get(i));
            }
        }

        HashMap<String, List<String>> rootToEmails = new HashMap<>();
        for (String email : parent.keySet()) {
            String root = find(parent, email);
            rootToEmails.computeIfAbsent(root, k -> new ArrayList<>()).add(email);
        }

        List<List<String>> res = new ArrayList<>();
        for (List<String> emails : rootToEmails.values()) {
            Collections.sort(emails);
            String name = emailToName.get(emails.get(0));
            List<String> row = new ArrayList<>();
            row.add(name);
            row.addAll(emails);
            res.add(row);
        }
        res.sort(Comparator.comparing(List::toString));
        return res;
    }

    private static String find(HashMap<String, String> parent, String x) {
        while (!parent.get(x).equals(x)) {
            parent.put(x, parent.get(parent.get(x)));
            x = parent.get(x);
        }
        return x;
    }

    private static void union(HashMap<String, String> parent, HashMap<String, Integer> rank, String a, String b) {
        String ra = find(parent, a);
        String rb = find(parent, b);
        if (ra.equals(rb)) {
            return;
        }
        int cra = rank.get(ra);
        int crb = rank.get(rb);
        if (cra < crb) {
            parent.put(ra, rb);
        } else if (cra > crb) {
            parent.put(rb, ra);
        } else {
            parent.put(rb, ra);
            rank.put(ra, cra + 1);
        }
    }

    /**
     * DFS on an undirected graph: for each account, connect every email to the first email in that account.
     * <p>
     * Time O(nk·log(nk)), space O(nk).
     */
    public static List<List<String>> accountsMergeDFS(List<List<String>> accounts) {
        HashMap<String, String> emailToName = new HashMap<>();
        HashMap<String, Set<String>> graph = new HashMap<>();

        for (List<String> account : accounts) {
            String name = account.get(0);
            String first = account.get(1);
            emailToName.putIfAbsent(first, name);
            graph.computeIfAbsent(first, k -> new HashSet<>());
            for (int i = 1; i < account.size(); i++) {
                String email = account.get(i);
                emailToName.putIfAbsent(email, name);
                graph.computeIfAbsent(email, k -> new HashSet<>());
                if (!email.equals(first)) {
                    graph.get(first).add(email);
                    graph.get(email).add(first);
                }
            }
        }

        Set<String> visited = new HashSet<>();
        List<List<String>> res = new ArrayList<>();

        for (String email : new TreeSet<>(graph.keySet())) {
            if (visited.contains(email)) {
                continue;
            }
            List<String> comp = new ArrayList<>();
            dfs(graph, visited, email, comp);
            Collections.sort(comp);
            String name = emailToName.get(comp.get(0));
            List<String> row = new ArrayList<>();
            row.add(name);
            row.addAll(comp);
            res.add(row);
        }
        res.sort(Comparator.comparing(List::toString));
        return res;
    }

    private static void dfs(
            Map<String, Set<String>> graph,
            Set<String> visited,
            String email,
            List<String> comp) {
        visited.add(email);
        comp.add(email);
        for (String nei : graph.getOrDefault(email, Collections.emptySet())) {
            if (!visited.contains(nei)) {
                dfs(graph, visited, nei, comp);
            }
        }
    }
}
