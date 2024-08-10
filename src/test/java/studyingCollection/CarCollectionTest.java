package studyingCollection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import studyingArrayList.Car;
import studyingArrayList.CarArrayList;
import studyingHashSet.CarHashSet;
import studyingLinkedList.CarLinkedList;

import static org.junit.jupiter.api.Assertions.*;
class CarCollectionTest {

    private CarCollection cars;

    @BeforeEach
    void setUp() {
        cars = new CarHashSet();
        for (int i = 0; i < 35; i++) {
            cars.add(new Car("Brand " + i, i));
        }
    }

    @Test
    void contains() {
        assertTrue(cars.contains(new Car("Brand 1", 1)));
        assertFalse(cars.contains(new Car("Brand 09", 1)));
    }
}