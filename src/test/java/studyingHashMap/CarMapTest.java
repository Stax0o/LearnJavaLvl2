package studyingHashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import studyingArrayList.Car;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CarMapTest {

    private CarMap carMap;

    //todo Проинициализаровать CarMap
    @BeforeEach
    void setUp() {
        for (int i = 0; i < 35; i++){
            CarOwner carOwner = new CarOwner(i, "Name " + i, "LastName " + i);
            Car car = new Car("Brand " + i, i);
            carMap.put(carOwner, car);
        }
    }

    @Test
    void put() {
        assertEquals(35, carMap.size());
        CarOwner carOwner = new CarOwner(36, "Иван", "Иванов");
        Car car = new Car("Mazda", 3);
        carMap.put(carOwner, car);
        assertEquals(36, carMap.size());
    }

    @Test
    void get() {
        CarOwner carOwner = new CarOwner(10, "Name 10", "LastName 10");
        Car car = new Car("Brand 10", 10);
        assertEquals(car, carMap.get(carOwner));
    }

    @Test
    public void testPutAndGet() {
        CarOwner owner1 = new CarOwner(35, "John", "Doe");
        Car car1 = new Car("Toyota", 12345);

        carMap.put(owner1, car1);

        assertEquals(car1, carMap.get(owner1));
    }

    @Test
    public void testGetNonExistentKey() {
        CarOwner owner1 = new CarOwner(36, "John", "Doe");
        assertNull(carMap.get(owner1));
    }

    @Test
    public void testKeySet() {
        Set<CarOwner> expectedKeys = new HashSet<>();
        for (int i = 0; i < 35; i++) {
            expectedKeys.add(new CarOwner(i, "Name " + i, "LastName " + i));
        }
        assertEquals(expectedKeys, carMap.keySet());
    }

    @Test
    public void testValues() {
        List<Car> expectedValues = new ArrayList<>();
        for (int i = 0; i < 35; i++) {
            expectedValues.add(new Car("Brand " + i, i));
        }
        assertEquals(expectedValues, carMap.values());
    }

    @Test
    public void testRemove() {
        CarOwner owner1 = new CarOwner(0, "Name 0", "LastName 0");
        assertTrue(carMap.remove(owner1));
        assertNull(carMap.get(owner1));
    }

    @Test
    public void testSize() {
        assertEquals(35, carMap.size());
    }

    @Test
    public void testClear() {
        carMap.clear();
        assertEquals(0, carMap.size());
        assertTrue(carMap.keySet().isEmpty());
        assertTrue(carMap.values().isEmpty());
    }
}