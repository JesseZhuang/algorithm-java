package graph;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OpenTheLockTest {

    @Test
    void example1() {
        String[] deadends = {"0201", "0101", "0102", "1212", "2002"};
        assertEquals(6, OpenTheLock.openLock(deadends, "0202"));
        assertEquals(6, OpenTheLock.openLockBidirectional(deadends, "0202"));
    }

    @Test
    void example2() {
        String[] deadends = {"8888"};
        assertEquals(1, OpenTheLock.openLock(deadends, "0009"));
        assertEquals(1, OpenTheLock.openLockBidirectional(deadends, "0009"));
    }

    @Test
    void unreachable() {
        // All 8 neighbors of "0000" are dead, so "8888" is unreachable
        String[] deadends = {"1000", "0100", "0010", "0001", "9000", "0900", "0090", "0009"};
        assertEquals(-1, OpenTheLock.openLock(deadends, "8888"));
        assertEquals(-1, OpenTheLock.openLockBidirectional(deadends, "8888"));
    }

    @Test
    void startIsDeadend() {
        String[] deadends = {"0000"};
        assertEquals(-1, OpenTheLock.openLock(deadends, "8888"));
        assertEquals(-1, OpenTheLock.openLockBidirectional(deadends, "8888"));
    }

    @Test
    void targetIsStart() {
        String[] deadends = {};
        assertEquals(0, OpenTheLock.openLock(deadends, "0000"));
        assertEquals(0, OpenTheLock.openLockBidirectional(deadends, "0000"));
    }

    @Test
    void singleTurn() {
        String[] deadends = {};
        assertEquals(1, OpenTheLock.openLock(deadends, "1000"));
        assertEquals(1, OpenTheLock.openLockBidirectional(deadends, "1000"));
    }

    @Test
    void wraparound() {
        String[] deadends = {};
        assertEquals(4, OpenTheLock.openLock(deadends, "9999"));
        assertEquals(4, OpenTheLock.openLockBidirectional(deadends, "9999"));
    }
}
