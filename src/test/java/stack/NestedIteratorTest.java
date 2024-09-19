package stack;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class NestedIteratorTest {
    static NestedIterator tbt1;
    static NestedIteratorEager tbt2;
    static NestedIteratorLazy tbt3;

    @BeforeAll
    static void setup() {
        List<NestedInteger> list = new ArrayList<>();
        list.add(new NestedIntegerImpl(1));
        list.add(new NestedIntegerImpl(new ArrayList<>()));
        list.add(new NestedIntegerImpl(2));
        NestedInteger list1 = new NestedIntegerImpl(new ArrayList<>());
        NestedInteger nested = new NestedIntegerImpl(Collections.singletonList(list1));
        NestedInteger nested2 = new NestedIntegerImpl(Collections.singletonList(nested));
        list.add(nested);
        list.add(nested2);
        System.out.println(list);

        tbt1 = new NestedIterator(list);
        tbt2 = new NestedIteratorEager(list);
        tbt3 = new NestedIteratorLazy(list);
    }

    @Test
    void testEager() {
        assertEquals(Integer.valueOf(1), tbt1.next());
        assertEquals(Integer.valueOf(2), tbt1.next());
        assertFalse(tbt1.hasNext());
    }

    @Test
    void testLazy() {
        assertEquals(Integer.valueOf(1), tbt2.next());
        assertEquals(Integer.valueOf(2), tbt2.next());
        assertFalse(tbt2.hasNext());
    }

    @Test
    void testLazy2() {
        assertEquals(Integer.valueOf(1), tbt3.next());
        assertTrue(tbt3.hasNext());
        assertEquals(Integer.valueOf(2), tbt3.next());
        assertFalse(tbt3.hasNext());
    }
}
