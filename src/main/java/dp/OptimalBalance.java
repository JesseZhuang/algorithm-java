package dp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * LeetCode 465, hard, LintCode 707, tags: bit manipulation, array, dynamic programming, backtracking, bit mask.
 * Companies: pinterest.
 * <p>
 * You are given an array of transactions where transactions[i] = [from_i, toi, amount_i] indicates that
 * the person with ID = from_i gave amount_i $ to the person with ID = toi.
 * <p>
 * Return the minimum number of transactions required to settle the debt.
 * <p>
 * Example 1:
 * <p>
 * Input: transactions = [[0,1,10],[2,0,5]]
 * Output: 2
 * Explanation:
 * Person #0 gave person #1 $10.
 * Person #2 gave person #0 $5.
 * Two transactions are needed. One way to settle the debt is person #1 pays person #0 and #2 $5 each.
 * Example 2:
 * <p>
 * Input: transactions = [[0,1,10],[1,0,1],[1,2,5],[2,0,5]]
 * Output: 1
 * Explanation:
 * Person #0 gave person #1 $10.
 * Person #1 gave person #0 $1.
 * Person #1 gave person #2 $5.
 * Person #2 gave person #0 $5.
 * Therefore, person #1 only need to give person #0 $4, and all debt is settled.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= transactions.length <= 8, e: O(n^2) (no parallel edges)
 * transactions[i].length == 3
 * 0 <= from_i, to_i < 12, n
 * from_i != to_i
 * 1 <= amount_i <= 100
 * <p>
 * LintCode
 * <p>
 * Given a directed graph where each edge is represented by a tuple, such as [u, v, w] represents an edge with a
 * weight w from u to v.
 * You need to calculate at least the need to add the number of edges to ensure that each point of the weight are
 * balancing. That is, the sum of weight of the edge pointing to this point is equal to the sum of weight of the
 * edge of the point that points to the other point.
 * <p>
 * 1 Note that u ≠ v and w > 0
 * 2 Index may not be linear, e.g. we could have the points 0, 1, 2 or we could also have the points 0, 2, 6
 * 3 A directed graph is a pair of directional graphs that is composed of a set of vertices and a set of directed
 * edges, each of which is connected to an ordered pair of vertices. A graph composed entirely of undirected edges
 * is called an undirected graph
 * <p>
 * Example 1
 * <p>
 * Input: [[0,1,10],[2,0,5]]
 * Output: 2
 * Explanation:
 * Two edges are need to added. There are [1,0,5] and [1,2,5]
 * Example 2
 * <p>
 * Input: [[0,1,10],[1,0,1],[1,2,5],[2,0,5]]
 * Output: 1
 * Explanation:
 * Only one edge need to added. There is [1,0,4]
 */
@SuppressWarnings("unused")
public class OptimalBalance {

    // f[i] dp, min transactions to settle debt to 0 for subset i, i in [1, 1<<m)
    // subset i 1 (one person: 0), 11 (two people, 0 and 1)
    // f[i] = {
    //          0, when i==0
    //          inf, when i!=0, sum!=0
    //          min(|i|-1, min(f[j]+f[i-j])) where i!=0,sum==0 i,j non-empty, j is subset of i,
    //          |i| is bit_count or number of people in set i
    //        }
    static class Solution1 {
        // Time: O(e) + O(n * 2^n), n<=12. Space: O(2^n).
        public int minTransfers(int[][] transactions) { // [[0,1,10],[2,0,5]]
            HashMap<Integer, Integer> bal = new HashMap<>(); // person -> balance
            for (int[] t : transactions) { // O(n)
                bal.put(t[0], bal.getOrDefault(t[0], 0) - t[2]);
                bal.put(t[1], bal.getOrDefault(t[1], 0) + t[2]);
            } // [-5,10,-5,0,0...] 0:+5-10==-5; 1:10==10;2:-5==-5
            List<Integer> debts = new ArrayList<>(); // non-zero balances
            for (int x : bal.values()) if (x != 0) debts.add(x); // {-5,10,-5}
            int n = debts.size();
            int[] f = new int[1 << n]; // index: subset of people, 2^n space
            // subsets: 0:no one, 1: only person 0, 10(2): only person 1, 11(3): person 1 and 2, .etc
            Arrays.fill(f, 1 << 29);
            f[0] = 0;
            for (int i = 1; i < 1 << n; i++) { // starting from 1: non empty
                int sum = 0;
                for (int j = 0; j < n; ++j)
                    if ((i >> j & 1) == 1) sum += debts.get(j); // is jth person in subset i?
                if (sum == 0) { // when i==7, 111 -5+10+-5==0, iterate j in [6,1] f[6]+f[1],f[5]+f[2],...
                    f[i] = Integer.bitCount(i) - 1; // init: number of people - 1
                    for (int j = (i - 1) & i; j > 0; j = (j - 1) & i)
                        f[i] = Math.min(f[i], f[j] + f[i ^ j]); // split subset into two parts and minimize
                }
            }
            return f[(1 << n) - 1]; // transactions for set including all non-zero balances
        }
    }

    // n!, n (recursion depth).
    // first layer s:0, second layer s:1 for i in [2,n-1], third layer s:2 for i in [3,n-1]
    // 1 + (n-1) + (n-1)*(n-2) + ...
    static class Solution2 {
        public int minTransfers(int[][] transactions) {
            HashMap<Integer, Integer> balances = new HashMap<>(); // person -> balance
            for (int[] t : transactions) { // O(n)
                balances.put(t[0], balances.getOrDefault(t[0], 0) - t[2]);
                balances.put(t[1], balances.getOrDefault(t[1], 0) + t[2]);
            }
            // Collect non-zero balances (debts)
            // settle larger debts first, reducing the search space
            List<Integer> debts = balances.values().stream().filter(b -> b != 0)
                    .sorted(Comparator.reverseOrder()).collect(toList());
            return dfs(debts, 0);
        }

        private int dfs(List<Integer> debts, int s) {
            while (s < debts.size() && debts.get(s) == 0) s++; // skip 0 balances
            if (s == debts.size()) return 0; // base case, all debts settled
            int res = Integer.MAX_VALUE;
            for (int i = s + 1; i < debts.size(); i++) {
                if (debts.get(i) * debts.get(s) < 0) {
                    debts.set(i, debts.get(i) + debts.get(s)); // try i give s to make s 0
                    res = Math.min(res, 1 + dfs(debts, s + 1));
                    debts.set(i, debts.get(i) - debts.get(s)); // backtrack
                    if (debts.get(i) + debts.get(s) == 0) break; // optimization, exact match found, stop further
                }
            }
            return res;
        }
    }

    // assumption: no parallel edges.
    // todo still failing tests
    static class Solution3 {
        final static int D = 100; // max amount for a transaction

        public int minTransfers(int[][] transactions) {
            Network nw = new Network();
            for (int[] t : transactions) nw.addEdge(new Edge(t[1], t[0], t[2]));
            nw.minimize();
            return nw.cntEdges();
        }

        static class Network {
            Map<Integer, Map<Integer, Edge>> adj; // from -> (to -> edge(from->to))
            Set<Integer> vis; // visited
            Map<Integer, Edge> edgeTo; // node -> edge led to this node during traversal
            Set<Integer> onStack;
            Set<Stack<Edge>> cycles;
            Set<Stack<Edge>> paths;

            Network() {
                adj = new HashMap<>();
            }

            int cntEdges() {
                return adj.values().stream().mapToInt(Map::size).reduce(0, Integer::sum);
            }

            void addEdge(Edge e) {
                adj.computeIfAbsent(e.v, k -> new HashMap<>()).put(e.w, e);
                adj.putIfAbsent(e.w, new HashMap<>());
            }

            void minimize() {
                removeCycles();
                reducePaths();
            }

            // remove the min weight edge in the cycle to settle debt and remove cycle
            void removeCycles() {
                vis = new HashSet<>();
                onStack = new HashSet<>();
                cycles = new HashSet<>();
                edgeTo = new HashMap<>();
                for (int v : adj.keySet()) if (!vis.contains(v)) dfsC(v); // O(n+e)
                for (Stack<Edge> cycle : cycles) {
                    int minW = cycle.stream().min(Comparator.comparingInt(e -> e.weight)).orElseThrow().weight;
                    if (minW == 0) continue;
                    for (Edge e : cycle) {
                        e.weight -= minW;
                        if (e.weight == 0) adj.get(e.v).remove(e.w);
                    }
                }
            }

            // number of edges will only reduce if path >=3 and contains duplicate weights
            // find all paths that can be settled
            // 1) length longer than 2, e.g., 1->2(5),2->3(5),3->4(10).
            // 2) duplicate weights in the path, this duplicate also has to be the bottleneck
            // thought about maxflow, but need more thought
            // wrong, [[0,1,10],[1,2,15],[2,3,10]] -> [2,0,5],[3,0,5],[3,1,5] 3 edges instead of 2
            void reducePaths() {
                Map<Integer, Integer> indegree = new HashMap<>(); // node->indegree
                for (Map.Entry<Integer, Map<Integer, Edge>> e : adj.entrySet()) {
                    indegree.put(e.getKey(), 0); // v
                    for (int w : e.getValue().keySet()) indegree.put(w, indegree.getOrDefault(w, 0) + 1);
                }
                Map<Integer, Map<Integer, Edge>> level1 = new HashMap<>(); // node -> (from->edge led to this node)
                Set<Integer> level0 = indegree.entrySet().stream().filter(e -> e.getValue() == 0)
                        .map(Map.Entry::getKey).collect(Collectors.toSet());
                for (int v : level0)
                    for (int w : adj.get(v).keySet())
                        level1.computeIfAbsent(w, k -> new HashMap<>()).put(v, adj.get(v).get(w));
                while (hasPath(level1)) {
                    Map<Integer, Map<Integer, Edge>> nLevel1 = deepCopy(level1);
                    Map<Integer, Map<Integer, Edge>> nAdj = deepCopy(adj);
                    for (int n : level1.keySet()) {
                        for (Edge e1 : level1.get(n).values()) { // e1 starts from level 0, in level 1
                            int start = e1.v, w1 = e1.weight;
                            // 0->1(10)->2(15) become 0->2(10);1->2(5)
                            // 0->1(10)->2(15);0->2(2) become 0->2(12);1->2(5)
                            for (Edge e2 : adj.get(n).values()) { // e2 starts from level 1, not in level 1
                                int end = e2.w, w2 = e2.weight, w = Math.min(w1, w2);
                                if (w == 0) continue;
                                Edge nE = new Edge(start, end, w);
                                Edge existing = nAdj.get(start).get(end); // 0->2 edge exist?
                                if (existing != null) existing.weight += w;
                                else {
                                    nAdj.get(start).put(end, nE);
                                    nLevel1.computeIfAbsent(end, k -> new HashMap<>()).put(start, nE);
                                }
                                e1.weight -= w;
                                e2.weight -= w;
                                nAdj.get(e1.v).get(e1.w).weight -= w;
                                nLevel1.get(n).get(e1.v).weight -= w;
                                nAdj.get(e2.v).get(e2.w).weight -= w;
                                if (e1.weight == 0) {
//                                    indegree.put(e1.w, indegree.get(e1.w) - 1);
                                    nAdj.get(e1.v).remove(e1.w);
                                    nLevel1.get(e1.w).remove(e1.v);
                                    if (nLevel1.get(e1.w).isEmpty()) nLevel1.remove(e1.w);
                                    if (e2.weight != 0)
                                        nLevel1.computeIfAbsent(end, k -> new HashMap<>()).put(e2.v, e2);
                                }
                                if (e2.weight == 0) {
                                    nAdj.get(e2.v).remove(e2.w);
                                    nLevel1.get(e2.w).remove(e2.v);
                                }
                            }
                        }
                    }
                    level1 = nLevel1;
                    adj = nAdj;
                }
            }

            Map<Integer, Map<Integer, Edge>> deepCopy(Map<Integer, Map<Integer, Edge>> original) {
                Map<Integer, Map<Integer, Edge>> res = new HashMap<>();
                for (int i : original.keySet()) {
                    res.put(i, new HashMap<>());
                    for (Map.Entry<Integer, Edge> en : original.get(i).entrySet())
                        res.get(i).put(en.getKey(), new Edge(en.getValue())); // deep copy edge
                }
                return res;
            }


            // whether any level1 node still has any out degree
            boolean hasPath(Map<Integer, Map<Integer, Edge>> level1) {
                for (Integer n : level1.keySet())
                    if (adj.containsKey(n) && !adj.get(n).isEmpty()) return true;
                return false;
            }

            // find all cycles
            void dfsC(int v) {
                onStack.add(v);
                vis.add(v);
                Stack<Edge> cycle;
                for (Edge e : adj.get(v).values()) {
                    if (!vis.contains(e.w)) {
                        edgeTo.put(e.w, e);
                        dfsC(e.w);
                    } else if (onStack.contains(e.w)) {
                        int w = e.w;
                        cycle = new Stack<>();
                        while (e.v != w) {
                            cycle.push(e);
                            e = edgeTo.get(e.v);
                        }
                        cycle.push(e);
                        cycles.add(cycle);
                    }
                }
                onStack.remove(v);
            }
        }

        @Data
        @AllArgsConstructor
        static class Edge {
            @EqualsAndHashCode.Include
            int v, w;
            @EqualsAndHashCode.Exclude
            int weight;

            public Edge(Edge e) {
                this.v = e.v;
                this.w = e.w;
                this.weight = e.weight;
            }

            public static void main(String[] args) {
                Edge e1 = new Edge(1, 3, 1), e2 = new Edge(1, 3, 2);
                System.out.println(e1.equals(e2));
            }

            int other(int u) {
                return u == v ? w : v;
            }
        }
    }
}
