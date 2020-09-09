package stack;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;
import util.CollectionUtil;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JavaStackTest {
    @Test
    void testJavaStackImplementations() {
        Stack<Integer> stack1 = new Stack<>();
        stack1.push(1);
        stack1.push(2);
        System.out.println("Stack; " + stack1);
        Iterable iterable = stack1;
        assertEquals(ImmutableList.of(1, 2), CollectionUtil.toList(iterable));
        // ArrayDeque and LinkedList implements Deque interface
        // all 3 implements Iterable interface
        ArrayDeque<Integer> stack2 = new ArrayDeque();
        LinkedList<Integer> stack3 = new LinkedList<>();
        List<Deque<Integer>> stacks = ImmutableList.of(stack2, stack3);
        List<String> implementations = ImmutableList.of("ArrayDeque", "LinkedList");
        for (int i = 0; i < 2; i++) {
            Deque<Integer> stack = stacks.get(i);
            stack.push(1);
            stack.push(2);
            System.out.println(implementations.get(i) + ": " + stack);
            iterable = stack;
            assertEquals(ImmutableList.of(2, 1), CollectionUtil.toList(iterable));
        }
    }
}
