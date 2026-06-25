package graph;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class KeysAndRoomsTest {

    // Example 1: [[1],[2],[3],[]] → true
    @Test
    void example1() {
        List<List<Integer>> rooms = Arrays.asList(
                Arrays.asList(1), Arrays.asList(2), Arrays.asList(3), Collections.emptyList());
        assertTrue(KeysAndRooms.canVisitAllRooms(rooms));
        assertTrue(KeysAndRooms.canVisitAllRoomsBFS(rooms));
    }

    // Example 2: [[1,3],[3,0,1],[2],[0]] → false
    @Test
    void example2() {
        List<List<Integer>> rooms = Arrays.asList(
                Arrays.asList(1, 3), Arrays.asList(3, 0, 1), Arrays.asList(2), Arrays.asList(0));
        assertFalse(KeysAndRooms.canVisitAllRooms(rooms));
        assertFalse(KeysAndRooms.canVisitAllRoomsBFS(rooms));
    }

    // Single room [[]] → true
    @Test
    void singleRoom() {
        List<List<Integer>> rooms = Arrays.asList(Collections.emptyList());
        assertTrue(KeysAndRooms.canVisitAllRooms(rooms));
        assertTrue(KeysAndRooms.canVisitAllRoomsBFS(rooms));
    }

    // All keys in first room [[1,2,3],[],[],[]] → true
    @Test
    void allKeysInFirstRoom() {
        List<List<Integer>> rooms = Arrays.asList(
                Arrays.asList(1, 2, 3), Collections.emptyList(), Collections.emptyList(), Collections.emptyList());
        assertTrue(KeysAndRooms.canVisitAllRooms(rooms));
        assertTrue(KeysAndRooms.canVisitAllRoomsBFS(rooms));
    }

    // Chain [[1],[2],[3],[4],[]] → true
    @Test
    void chain() {
        List<List<Integer>> rooms = Arrays.asList(
                Arrays.asList(1), Arrays.asList(2), Arrays.asList(3), Arrays.asList(4), Collections.emptyList());
        assertTrue(KeysAndRooms.canVisitAllRooms(rooms));
        assertTrue(KeysAndRooms.canVisitAllRoomsBFS(rooms));
    }

    // Isolated room [[1],[2],[],[]] → false
    @Test
    void isolatedRoom() {
        List<List<Integer>> rooms = Arrays.asList(
                Arrays.asList(1), Arrays.asList(2), Collections.emptyList(), Collections.emptyList());
        assertFalse(KeysAndRooms.canVisitAllRooms(rooms));
        assertFalse(KeysAndRooms.canVisitAllRoomsBFS(rooms));
    }

    // Duplicate keys [[1,1,1],[2],[3],[]] → true
    @Test
    void duplicateKeys() {
        List<List<Integer>> rooms = Arrays.asList(
                Arrays.asList(1, 1, 1), Arrays.asList(2), Arrays.asList(3), Collections.emptyList());
        assertTrue(KeysAndRooms.canVisitAllRooms(rooms));
        assertTrue(KeysAndRooms.canVisitAllRoomsBFS(rooms));
    }

    // Cycle without full coverage [[1],[0],[3],[2]] → false
    @Test
    void cycleWithoutFullCoverage() {
        List<List<Integer>> rooms = Arrays.asList(
                Arrays.asList(1), Arrays.asList(0), Arrays.asList(3), Arrays.asList(2));
        assertFalse(KeysAndRooms.canVisitAllRooms(rooms));
        assertFalse(KeysAndRooms.canVisitAllRoomsBFS(rooms));
    }
}
