package queue;

import java.util.ArrayList;
import java.util.List;

/**
 * S. Medium. Tags: Queue.
 * <p>
 * Imagine cars arriving at stop signs in sequence in four directions all going straight, no turning. Please
 * implement APIs for arrive and go.
 * <p>
 * <pre>
 * Cars arriving [a(S), b(N), c(E), d(W), e(E), f(S), g(N)].
 * Cars go [[a,b], [c,d], [f,g], [e]].
 * </pre>
 */
public class CarIntersection {
    void arrive(List<Car> cars) {}
    List<Car> go() {
        return new ArrayList<>();
    }
}

class Car {
    Direction direction;

    enum  Direction {
        NORTH, SOUTH, WEST, EAST
    }

}
