package list;

import java.util.List;

@SuppressWarnings("unused")
public class NestedListWeightSum {

    // This is the interface that allows for creating nested lists.
    // You should not implement it, or speculate about its implementation
    interface NestedInteger {
        // @return true if this NestedInteger holds a single integer,
        // rather than a nested list.
        boolean isInteger();

        // @return the single integer that this NestedInteger holds,
        // if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        Integer getInteger();

        // @return the nested list that this NestedInteger holds,
        // if it holds a nested list
        // Return null if this NestedInteger holds a single integer
        List<NestedInteger> getList();
    }


    static class Solution {
        int depthSum(List<NestedInteger> nil) {
            return dfs(nil, 1);
        }

        int dfs(List<NestedInteger> nil, int d) {
            int res = 0;
            for (NestedInteger i : nil) {
                if (i.isInteger()) res += i.getInteger() * d;
                else res += dfs(i.getList(), d + 1);
            }
            return res;
        }
    }
}
