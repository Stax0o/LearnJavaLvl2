package studyingCollection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import studyingArrayList.Car;
import studyingHashSet.CarHashSet;

import static org.junit.jupiter.api.Assertions.*;

class CarCollectionTest {

    private CarCollection<Car> cars;

    @BeforeEach
    void setUp() {
        cars = new CarHashSet<Car>();
        for (int i = 0; i < 35; i++) {
            cars.add(new Car("Brand " + i, i));
        }
    }

    @Test
    void contains() {
        assertTrue(cars.contains(new Car("Brand 1", 1)));
        assertFalse(cars.contains(new Car("Brand 09", 1)));
    }

    @Test
    void testForeach() {
        int count = 0;
        for (Car car : cars) {
            count++;
        }
        assertEquals(35, count);
    }
}