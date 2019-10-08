package princeton.jsl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import util.FileUtils;

import java.util.Arrays;

class AVLTreeTest {
    static AVLTreeST<String, Integer> tbt;

    @BeforeAll
    static void setup() {
        tbt = new AVLTreeST<>();
    }

    @Test
    void testTinyST() {
        String[] keys = FileUtils.readClassPathFileToString("/tree/tinyST.txt").split("\\s");
        System.out.println(Arrays.toString(keys));
        for (int i = 0; i < keys.length; i++) tbt.put(keys[i], i);
        for (String s : tbt.keysInOrder()) System.out.println(s + " " + tbt.get(s));
    }
}
