package graph;

import graph.SettleTransactions.Edge;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class SettleTransactionsTest {

    @Test
    void minimize() {
        List<Edge> owe = new ArrayList<>();
        owe.add(new Edge("A", "B", 5));
        owe.add(new Edge("B", "C", 5));
        owe.add(new Edge("B", "A", 5));
        // cycle, should minimize to no edges
    }
}