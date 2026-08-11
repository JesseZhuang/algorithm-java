package graph;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReconstructItineraryTest {

    @Test
    void testLinearPath() {
        List<List<String>> tickets = Arrays.asList(
                Arrays.asList("MUC", "LHR"),
                Arrays.asList("JFK", "MUC"),
                Arrays.asList("SFO", "SJC"),
                Arrays.asList("LHR", "SFO"));
        List<String> expected = Arrays.asList("JFK", "MUC", "LHR", "SFO", "SJC");
        assertEquals(expected, ReconstructItinerary.findItinerary(tickets));
        assertEquals(expected, ReconstructItinerary.findItinerary2(tickets));
    }

    @Test
    void testCycleWithLexOrder() {
        List<List<String>> tickets = Arrays.asList(
                Arrays.asList("JFK", "SFO"),
                Arrays.asList("JFK", "ATL"),
                Arrays.asList("SFO", "ATL"),
                Arrays.asList("ATL", "JFK"),
                Arrays.asList("ATL", "SFO"));
        List<String> expected = Arrays.asList("JFK", "ATL", "JFK", "SFO", "ATL", "SFO");
        assertEquals(expected, ReconstructItinerary.findItinerary(tickets));
        assertEquals(expected, ReconstructItinerary.findItinerary2(tickets));
    }

    @Test
    void testSingleTicket() {
        List<List<String>> tickets = Arrays.asList(
                Arrays.asList("JFK", "A"));
        List<String> expected = Arrays.asList("JFK", "A");
        assertEquals(expected, ReconstructItinerary.findItinerary(tickets));
        assertEquals(expected, ReconstructItinerary.findItinerary2(tickets));
    }

    @Test
    void testMultipleFromJFK() {
        List<List<String>> tickets = Arrays.asList(
                Arrays.asList("JFK", "KUL"),
                Arrays.asList("JFK", "NRT"),
                Arrays.asList("NRT", "JFK"));
        List<String> expected = Arrays.asList("JFK", "NRT", "JFK", "KUL");
        assertEquals(expected, ReconstructItinerary.findItinerary(tickets));
        assertEquals(expected, ReconstructItinerary.findItinerary2(tickets));
    }

    @Test
    void testDuplicateTickets() {
        List<List<String>> tickets = Arrays.asList(
                Arrays.asList("JFK", "A"),
                Arrays.asList("A", "JFK"),
                Arrays.asList("JFK", "A"));
        List<String> expected = Arrays.asList("JFK", "A", "JFK", "A");
        assertEquals(expected, ReconstructItinerary.findItinerary(tickets));
        assertEquals(expected, ReconstructItinerary.findItinerary2(tickets));
    }
}
