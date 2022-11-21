package graph;

import java.util.Optional;

/**
 * undirected edge, weight is optional.
 */
public class Edge implements Comparable<Edge> {
    private int v;
    private int w;
    private Optional<Double> weight = Optional.empty();

    //
    public Edge(int v, int w) {
        this.v = v;
        this.w = w;
    }

    // weighted edge
    public Edge(int v, int w, double weight) {
        this(v, w);
        this.weight = Optional.of(weight);
    }

    public int other(int vertex) {
        if (vertex == v) return w;
        else if (vertex == w) return v;
        else throw new IllegalArgumentException("edge does not contain vertex: " + vertex);
    }

    @Override
    public String toString() {
        return "Edge{" +
                "v=" + v +
                ", w=" + w +
                ", weight=" + weight +
                '}';
    }

    public static void main(String[] args) {
        Edge e = new Edge(1, 3);
        System.out.println(e);
        e = new Edge(2, 5, 1.1);
        System.out.println(e);
        System.out.println(new Edge(1, 2, 1.1).compareTo(new Edge(1, 0, 1.2)));
    }

    @Override
    public int compareTo(Edge other) {
        if (this.weight.isPresent() && other.weight.isPresent())
            return Double.compare(this.weight.get(), other.weight.get());
        else throw new IllegalStateException("both edges need to have weight to compare");
    }
}
