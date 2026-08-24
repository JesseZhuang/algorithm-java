package array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class CapacityShipPackagesTest {

    private CapacityShipPackagesTest() {
    }

    @Test
    void testExample1() {
        assertEquals(15, CapacityShipPackages.shipWithinDays2(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 5));
    }

    @Test
    void testExample2() {
        assertEquals(6, CapacityShipPackages.shipWithinDays2(new int[]{3, 2, 2, 4, 1, 4}, 3));
    }

    @Test
    void testExample3() {
        assertEquals(3, CapacityShipPackages.shipWithinDays2(new int[]{1, 2, 3, 1, 1}, 4));
    }

    @Test
    void testSinglePackage() {
        assertEquals(5, CapacityShipPackages.shipWithinDays2(new int[]{5}, 1));
    }

    @Test
    void testOneDay() {
        assertEquals(15, CapacityShipPackages.shipWithinDays2(new int[]{1, 2, 3, 4, 5}, 1));
    }

    @Test
    void testDaysEqualToPackages() {
        assertEquals(3, CapacityShipPackages.shipWithinDays2(new int[]{1, 2, 3, 1, 1}, 5));
    }

    @Test
    void testEqualWeights() {
        assertEquals(6, CapacityShipPackages.shipWithinDays2(new int[]{3, 3, 3, 3, 3, 3}, 3));
    }

    @Test
    void testHeavyLastPackage() {
        assertEquals(500, CapacityShipPackages.shipWithinDays2(new int[]{1, 1, 1, 500}, 2));
    }
}
