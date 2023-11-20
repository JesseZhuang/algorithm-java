package princeton.jsl;

import java.util.Optional;

/**
 * directed edge.
 */
public class DirectedEdge {
    private int v;
    private int w;
    private Optional<Double> weight = Optional.empty();

    public DirectedEdge(int v, int w) {
        this.v = v;
        this.w = w;
    }

    public DirectedEdge(int v, int w, double weight) {
        this(v, w);
        this.weight = Optional.of(weight);
    }

    public int from() {
        return v;
    }

    public int to() {
        return w;
    }

    public int other(int vertex) {
        if (vertex == v) return w;
        else if (vertex == w) return v;
        else throw new IllegalArgumentException("edge does not contain vertex: " + vertex);
    }

    public double weight() {
        return weight.orElseThrow(() -> new IllegalStateException("not a weighted edge"));
    }

    @Override
    public String toString() {
        return "DirectedEdge{" +
                "v=" + v +
                ", w=" + w +
                ", weight=" + weight +
                '}';
    }

    public static void main(String[] args) {
        DirectedEdge edge = new DirectedEdge(1, 3);
        System.out.println(edge);
        edge = new DirectedEdge(1, 5, 1.23);
        System.out.println(edge);
    }
}
