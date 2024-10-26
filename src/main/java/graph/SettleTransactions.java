package graph;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.*;

import static java.util.stream.Collectors.*;

/**
 * Related to LeetCode 465. Companies: pinterest.
 * <p>
 * Example:
 * A paid 4000 for flights for A, B, C, D
 * C paid 2000 for hotel for C, D
 * Calculate how to settle the money.
 * e.g., {payer: A, amount: 1000, payees: [B, C]}
 */
public class SettleTransactions {

    public static void main(String[] args) {
        SettleTransactions tbt = new SettleTransactions();
        List<Transaction> fee = new ArrayList<>();
        fee.add(new Transaction("A", Arrays.asList("A", "B", "C", "D"), 4000));
        fee.add(new Transaction("C", Arrays.asList("C", "D"), 2000));
        fee.add(new Transaction("A", Arrays.asList("C", "A"), 2000));
        System.out.println(tbt.settle2(fee)); // C->A 2000, B->A 1000, D->[A,C] 1000
    }

    List<Transaction> settle2(List<Transaction> fee) {
        List<Transaction> res = new ArrayList<>();
        Map<String, Map<String, Integer>> owe = new HashMap<>(); // {payee owes {payer, amount}}
        for (Transaction t : fee) {
            int amount = t.amount / t.payee.size();
            for (String payee : t.payee)
                if (!payee.equals(t.payer)) {
                    if (!owe.containsKey(payee)) owe.put(payee, new HashMap<>());
                    Map<String, Integer> tmp = owe.get(payee);
                    tmp.put(t.payer, tmp.getOrDefault(t.payer, 0) + amount);
                }
        }
        for (String start : owe.keySet()) {
            // group by owe amount, collect payees to list: amount->[payees]
            Map<Integer, List<String>> amounts = owe.get(start).entrySet().stream().collect(
                    groupingBy(Map.Entry::getValue, mapping(Map.Entry::getKey, toList())));
            for (int a : amounts.keySet())
                res.add(new Transaction(start, amounts.get(a), a));
        }
        return res;
    }

    List<Transaction> settle1(List<Transaction> fee) {
        List<Transaction> res = new ArrayList<>();
        Map<String, List<Edge>> adj = new HashMap<>();
        for (Transaction t : fee) {
            int owe = t.amount / t.payee.size();
            for (String payee : t.payee)
                if (!payee.equals(t.payer)) {
                    if (!adj.containsKey(payee)) adj.put(payee, new ArrayList<>());
                    adj.get(payee).add(new Edge(payee, t.payer, owe));
                }
        }
        for (String start : adj.keySet()) {
            Map<Integer, List<String>> amounts = adj.get(start).stream().collect(
                    groupingBy(e -> e.weight, mapping(e -> e.end, toList())));
            for (int a : amounts.keySet())
                res.add(new Transaction(start, amounts.get(a), a));
        }
        return res;
    }

    List<Edge> minimize(List<Edge> owes) {
        List<Edge> res = new ArrayList<>();
        Map<String, List<Edge>> adj = owes.stream().collect(groupingBy(e -> e.start));
        Set<String> visited = new HashSet<>();
        Stack<String> post = new Stack<>();
        for (String start : adj.keySet()) {
            dfs(adj, start, visited, post);
        }
        // proces parallel edges and cycles, similar to maxflow
        // find bottleneck, max, then some edges can be removed
        visited.clear();
        return res;
    }

    void dfs(Map<String, List<Edge>> adj, String start, Set<String> visited, Stack<String> post) {
        if (visited.contains(start)) return;
        visited.add(start);
        for (Edge e : adj.get(start))
            dfs(adj, e.end, visited, post);
        post.add(start);
    }

    @AllArgsConstructor
    @ToString
    static class Transaction {
        String payer;
        List<String> payee;
        int amount;
    }

    @AllArgsConstructor
    static class Edge {
        String start;
        String end;
        int weight;
    }
}
