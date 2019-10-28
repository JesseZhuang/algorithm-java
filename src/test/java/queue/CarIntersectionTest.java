package queue;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static queue.Car.Direction.*;

public class CarIntersectionTest {
    CarIntersection tbt;

    @BeforeEach
    void setup() {
        tbt = new CarIntersection();
    }

    @Test
    void testGo() {
        Car a = new Car(SOUTH, "a"), b = new Car(EAST, "b"), c = new Car(NORTH, "c"),
                d = new Car(WEST, "d"), e = new Car(EAST, "e"), f = new Car(SOUTH, "f"),
                g = new Car(NORTH, "g");
        List<Car> cars = ImmutableList.of(a, b, c, d, e, f, g);
        tbt.arrive(cars);
        List<List<Car>> carsToGo = tbt.go();
        assertEquals(Arrays.asList(Arrays.asList(a)), ImmutableList.of(ImmutableList.of(a)));
        assertEquals(ImmutableList.of(ImmutableList.of(a, c), ImmutableList.of(d, b),
                ImmutableList.of(g, f), ImmutableList.of(e)), carsToGo);
        assertEquals(Collections.emptyList(), tbt.go());
    }

}
