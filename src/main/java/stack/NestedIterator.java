package stack;

import java.util.*;

/**
 * LeetCode 341. Medium. Tags: Stack, Design.
 * <p>
 * Given a nested list of integers, implement an iterator to flatten it.
 * <p>
 * Each element is either an integer, or a list -- whose elements may also be integers or other stack.
 * <p>
 * Example 1:
 * <pre>
 * Input: [[1,1],2,[1,1]]
 * Output: [1,1,2,1,1]
 * </pre>
 * Explanation: By calling next repeatedly until hasNext returns false,
 * the order of elements returned by next should be: [1,1,2,1,1].
 * Example 2:
 * <pre>
 * Input: [1,[4,[6]]]
 * Output: [1,4,6]
 * </pre>
 * Explanation: By calling next repeatedly until hasNext returns false,
 * the order of elements returned by next should be: [1,4,6].
 * <p>
 * <ul>
 * <li>Eager, O(n) time, O(n) space, 2 ms 100%, 37.1 MB 100%.
 * <li>Lazy, O(n) time, O(n) space, 7 ms 7.66%, 37.4 MB 100%.
 * </ul>
 */
public class NestedIterator implements Iterator<Integer> {

    Stack<ListIterator<NestedInteger>> stack;

    public NestedIterator(List<NestedInteger> nestedList) {
        stack = new Stack<>();
        stack.push(nestedList.listIterator());
    }

    @Override
    public Integer next() {
        if (!hasNext()) throw new NoSuchElementException("No more elements.");
        return stack.peek().next().getInteger();
    }

    @Override
    public boolean hasNext() {
        while (!stack.empty()) {
            if (!stack.peek().hasNext()) stack.pop();
            else {
                NestedInteger x = stack.peek().next();
                if (x.isInteger()) return stack.peek().previous() == x;
                stack.push(x.getList().listIterator());
            }
        }
        return false;
    }

}

class NestedIteratorEager implements Iterator<Integer> {

    private Iterator<Integer> iterator;

    public NestedIteratorEager(List<NestedInteger> nestedList) {
        List<Integer> result = new ArrayList<>();
        flatten(nestedList, result);
        iterator = result.iterator();
    }

    void flatten(List<NestedInteger> nestedList, List<Integer> flatList) {
        for (NestedInteger ni : nestedList) {
            if (ni.isInteger()) flatList.add(ni.getInteger());
            else flatten(ni.getList(), flatList);
        }
    }

    @Override
    public Integer next() {
        return iterator.next();
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }
}

// This is the interface that allows for creating nested stack.
// You should not implement it, or speculate about its implementation
interface NestedInteger {
    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    boolean isInteger();

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    Integer getInteger();

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return null if this NestedInteger holds a single integer
    List<NestedInteger> getList();
}

class NestedIntegerImpl implements NestedInteger {
    List<NestedInteger> list;
    Integer integer;

    public NestedIntegerImpl(Integer integer) {
        this.integer = integer;
    }

    public NestedIntegerImpl(List<NestedInteger> list) {
        this.list = list;
    }

    @Override
    public boolean isInteger() {
        return integer != null;
    }

    @Override
    public Integer getInteger() {
        if (!isInteger()) throw new UnsupportedOperationException("Not an integer.");
        return integer;
    }

    @Override
    public List<NestedInteger> getList() {
        if (isInteger()) throw new UnsupportedOperationException("This is an integer.");
        return list;
    }

    @Override
    public String toString() {
        String delimiter = ",";
        if (isInteger()) return Integer.toString(integer);
        else {
            if (list.isEmpty()) return "[]";
            StringBuilder sb = new StringBuilder();
            sb.append('[');
            int n = list.size();
            sb.append(list.get(0));
            for (int i = 1; i < n; i++) sb.append(delimiter).append(list.get(i));
            sb.append(']');
            return sb.toString();
        }
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
