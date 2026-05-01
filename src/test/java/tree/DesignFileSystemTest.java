package tree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DesignFileSystemTest {

    @Test void example1() {
        DesignFileSystem fs = new DesignFileSystem();
        assertTrue(fs.createPath("/a", 1));
        assertEquals(1, fs.get("/a"));
    }

    @Test void example2() {
        DesignFileSystem fs = new DesignFileSystem();
        assertTrue(fs.createPath("/leet", 1));
        assertTrue(fs.createPath("/leet/code", 2));
        assertEquals(2, fs.get("/leet/code"));
        assertFalse(fs.createPath("/c/d", 1));
        assertEquals(-1, fs.get("/c"));
    }
}
