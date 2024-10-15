package tree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BTFromIOPOTest {

    BTFromIOPO.Solution tbt;

    @BeforeEach
    void setUp() {
        tbt = new BTFromIOPO.Solution();
    }

    @Test
    void test() {
        int[] inorder = {9, 3, 15, 20, 7}, postorder = {9, 15, 7, 20, 3};
        assertEquals("3,9,20,#,#,15,7,#,#,#,#", tbt.buildTree(inorder, postorder).toString());
    }
}