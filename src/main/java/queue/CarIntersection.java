package queue;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * S. Medium. Tags: Queue.
 * <p>
 * Imagine cars arriving at stop signs in sequence in four directions all going straight, no turning. Please
 * implement APIs for arrive and go.
 * <p>
 * <pre>
 * Cars arriving [a(S), b(E), c(N), d(W), e(E), f(S), g(N)].
 * Cars go [[a,c], [b,d], [f,g], [e]].
 * </pre>
 */
public class CarIntersection {
    Queue<Car> east;
    Queue<Car> west;
    Queue<Car> north;
    Queue<Car> south;
    Car.Direction directionToGo;// current direction where cars can go next

    public CarIntersection() {
        north = new ArrayDeque<>();
        south = new ArrayDeque<>();
        west = new ArrayDeque<>();
        east = new ArrayDeque<>();
    }

    void arrive(List<Car> cars) {
        if (cars.isEmpty()) return;
        cars.stream().forEach(car -> getQueue(car.direction).add(car));
        if (directionToGo == null) directionToGo = cars.get(0).direction;
    }

    List<List<Car>> go() {
        List<List<Car>> result = new ArrayList<>();
        if (noCars()) return result;
        while (!noCars()) {
            result.add(getMax2Cars());
            switchDirection();
        }
        return result;
    }

    Queue<Car> getQueue(Car.Direction direction) {
        return switch (direction) {
            case NORTH -> north;
            case SOUTH -> south;
            case WEST -> west;
            case EAST -> east;
            default -> throw new IllegalStateException("Unexpected direction: " + direction);
        };
    }

    List<Car> getMax2Cars() {
        List<Car> result = new ArrayList<>();
        result.addAll(getCar());
        directionToGo = mirror(directionToGo);
        result.addAll(getCar());
        return result;
    }

    List<Car> getCar() {
        List<Car> result = new ArrayList<>();
        Car car = getQueue(directionToGo).poll();
        if (car != null) result.add(car);
        return result;
    }

    boolean noCars() {
        return east.isEmpty() && west.isEmpty() && north.isEmpty() && south.isEmpty();
    }

    Car.Direction mirror(Car.Direction direction) {
        return switch (direction) {
            case NORTH -> Car.Direction.SOUTH;
            case SOUTH -> Car.Direction.NORTH;
            case WEST -> Car.Direction.EAST;
            case EAST -> Car.Direction.WEST;
            default -> throw new IllegalStateException("Unexpected direction: " + direction);
        };
    }

    void switchDirection() {
        if (directionToGo == null) throw new IllegalStateException("null direction, no need to switch");
        if (directionToGo == Car.Direction.NORTH || directionToGo == Car.Direction.SOUTH)
            directionToGo = Car.Direction.WEST;
        else directionToGo = Car.Direction.NORTH;
    }
}

@Data
@RequiredArgsConstructor
class Car {
    @NonNull
    Direction direction;
    @NonNull
    String name;

    public static void main(String[] args) {
        Car car = new Car(Direction.NORTH, "car1");
        System.out.println("car " + car);
    }

    enum Direction {
        NORTH, SOUTH, WEST, EAST
    }

}
