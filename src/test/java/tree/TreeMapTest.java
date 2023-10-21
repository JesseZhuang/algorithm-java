package tree;

import org.junit.jupiter.api.Test;
import princeton.jsl.RedBlackBST;

import java.util.TreeMap;

public class TreeMapTest {
    @Test
    void testRangeQuery() {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        RedBlackBST<Integer, Integer> rbMap = new RedBlackBST<>();
        for (int i = 0; i < 20; i++) {
            map.put(i, i);
            rbMap.put(i, i);
        }

        System.out.println(map.subMap(2, true, 6, true));
        System.out.println(rbMap.keys(2, 6));
    }
}
