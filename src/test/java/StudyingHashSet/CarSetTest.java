package StudyingHashSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import studyingArrayList.Car;

import static org.junit.jupiter.api.Assertions.*;

class CarSetTest {

    private CarSet carSet;

    @BeforeEach
    void setUp() {
        //Todo Создать коллекцию
        for (int i = 0; i < 35; i++) {
            carSet.add(new Car("Brand " + i, i));
        }
    }

    @Test
    void addNewCarThenReturnTrueAndSizeIncreased() {
        assertEquals(35, carSet.size());
        assertTrue(carSet.add(new Car("BMW", 103)));
        assertEquals(36, carSet.size());
    }

    @Test
    void whetAdd3SimilarCarThenReturnTrueAndSizeIncreasedBy1() {
        assertEquals(35, carSet.size());
        assertTrue(carSet.add(new Car("Toyota", 55)));
        assertFalse(carSet.add(new Car("Toyota", 55)));
        assertFalse(carSet.add(new Car("Toyota", 55)));
        assertEquals(36, carSet.size());
    }

    @Test
    void addDuplicateCarThenReturnFalseAndSizeNotIncreased() {
        assertEquals(35, carSet.size());
        assertFalse(carSet.add(new Car("Brand 1", 1)));
        assertEquals(35, carSet.size());
    }

    @Test
    void removeCarThenReturnTrueAndSizeIncreased() {
        assertEquals(35, carSet.size());
        assertTrue(carSet.remove(new Car("Brand 1", 1)));
        assertEquals(34, carSet.size());
    }

    @Test
    void removeDuplicateCarThenReturnFalseAndSizeNotIncreased() {
        assertEquals(35, carSet.size());
        assertFalse(carSet.remove(new Car("Brand 1", 56)));
        assertEquals(35, carSet.size());
    }

    @Test
    void size() {
        assertEquals(35, carSet.size());
    }

    @Test
    void clear() {
        assertEquals(35, carSet.size());
        carSet.clear();
        assertEquals(0, carSet.size());
    }
}