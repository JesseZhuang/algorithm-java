package dp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.IntStream;

/**
 * LeetCode 638, medium. Tags: dynamic programming, depth-first search, memoization, backtracking.
 */
public class ShoppingOffers {

    static List<List<Integer>> filterSpecial(List<Integer> price, List<List<Integer>> special) {
        int n = price.size();
        List<List<Integer>> filtered = new ArrayList<>();
        for (List<Integer> s : special) {
            int sum = 0;
            for (int i = 0; i < n; i++) sum += s.get(i) * price.get(i);
            if (sum > s.get(n)) filtered.add(s);
        }
        return filtered;
    }

    static int individualCost(List<Integer> price, List<Integer> needs) {
        int res = 0;
        for (int i = 0; i < price.size(); i++) res += price.get(i) * needs.get(i);
        return res;
    }
}

class Solution1 extends ShoppingOffers {
    /** DFS + memoization. O(n*k*m^n) time, O(n*m^n) space. */
    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        List<List<Integer>> filtered = filterSpecial(price, special);
        HashMap<List<Integer>, Integer> memo = new HashMap<>();
        return dfs(price, filtered, needs, memo);
    }

    private int dfs(List<Integer> price, List<List<Integer>> special,
                    List<Integer> needs, HashMap<List<Integer>, Integer> memo) {
        if (memo.containsKey(needs)) return memo.get(needs);
        int n = price.size();
        int res = individualCost(price, needs);
        for (List<Integer> offer : special) {
            List<Integer> updated = IntStream.range(0, n)
                    .mapToObj(i -> needs.get(i) - offer.get(i)).toList();
            if (updated.stream().allMatch(v -> v >= 0))
                res = Math.min(res, offer.get(n) + dfs(price, special, updated, memo));
        }
        memo.put(needs, res);
        return res;
    }
}

class Solution2 extends ShoppingOffers {
    /** Backtracking without memoization. O(n*(m+1)^k) time, O(k*n) space. */
    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        List<List<Integer>> filtered = filterSpecial(price, special);
        return dfs(0, price, filtered, needs);
    }

    private int dfs(int idx, List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        int n = price.size();
        if (idx == special.size()) return individualCost(price, needs);
        int res = dfs(idx + 1, price, special, needs);
        List<Integer> updated = new ArrayList<>(needs);
        int times = 0;
        while (true) {
            boolean neg = false;
            for (int i = 0; i < n; i++) {
                int v = updated.get(i) - special.get(idx).get(i);
                updated.set(i, v);
                if (v < 0) neg = true;
            }
            if (neg) break;
            times++;
            res = Math.min(res, special.get(idx).get(n) * times + dfs(idx + 1, price, special, updated));
        }
        return res;
    }
}
