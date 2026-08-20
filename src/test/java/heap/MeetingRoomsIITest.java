package heap;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MeetingRoomsIITest {
    private final MeetingRoomsII sol = new MeetingRoomsII();

    @Test
    void testHeapExample1() {
        assertEquals(2, sol.minMeetingRoomsHeap(new int[][]{{0, 30}, {5, 10}, {15, 20}}));
    }

    @Test
    void testHeapExample2() {
        assertEquals(1, sol.minMeetingRoomsHeap(new int[][]{{7, 10}, {2, 4}}));
    }

    @Test
    void testTreeMapAllOverlap() {
        assertEquals(3, sol.minMeetingRoomsTreeMap(new int[][]{{1, 10}, {2, 7}, {3, 19}}));
    }

    @Test
    void testSortBackToBack() {
        assertEquals(1, sol.minMeetingRoomsSort(new int[][]{{1, 5}, {5, 10}, {10, 15}}));
    }

    @Test
    void testHeapAllSameTime() {
        assertEquals(4, sol.minMeetingRoomsHeap(new int[][]{{1, 2}, {1, 2}, {1, 2}, {1, 2}}));
    }

    @Test
    void testSortNoOverlap() {
        assertEquals(1, sol.minMeetingRoomsSort(new int[][]{{1, 2}, {3, 4}, {5, 6}}));
    }

    @Test
    void testTreeMapSingle() {
        assertEquals(1, sol.minMeetingRoomsTreeMap(new int[][]{{1, 5}}));
    }
}
