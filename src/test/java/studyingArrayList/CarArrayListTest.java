package studyingArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarArrayListTest {

    private CarList carList;

    @BeforeEach
    void setUp() {
        carList = new CarArrayList();
        for (int i = 0; i < 35; i++) {
            carList.add(new Car("Brand " + i, i));
        }
    }

    @Test
    void whenGetCarNumberOneThenReturnCarNumberOne() {
        assertEquals("Brand 1", carList.get(1).getBrand());
        assertEquals(1, carList.get(1).getNumber());
    }

    @Test
    void whenAddCarThenSizeChangeByOne() {
        assertEquals(35, carList.size());
        carList.add(new Car("Toyota", 21));
        assertEquals(36, carList.size());
    }

    @Test
    void whenRemoveCarThenSizeChangeByOneAndReturnTrue() {
        assertEquals(35, carList.size());
        carList.remove(carList.get(6));
        assertEquals(34, carList.size());
    }

    @Test
    void whenRemoveNonexistentCarThenSizeNotChangeAndReturnFalse() {
        assertEquals(35, carList.size());
        carList.remove(new Car("BMW", 10));
        assertEquals(35, carList.size());
    }

    @Test
    void whenRemoveAtOneCarThenSizeChangeByOneAndReturnTrue() {
        assertEquals(35, carList.size());
        carList.removeAt(1);
        assertEquals(34, carList.size());
    }

    @Test
    void whenRemoveAtNonexistentCarThenSizeNotChangeAndReturnFalse() {
        assertEquals(35, carList.size());
        assertThrows(IndexOutOfBoundsException.class, () -> carList.removeAt(1000));
        assertEquals(35, carList.size());
    }

    @Test
    void whenRemoveAtNonexistentCarThrowAnIndexOutOfBoundsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> carList.removeAt(1000));
    }

    @Test
    void whenGetNonExistentCarThrowAnIndexOutOfBoundsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> carList.get(1000));
    }

    @Test
    void whenSizeThenReturn35() {
        assertEquals(35, carList.size());
    }

    @Test
    void whenClearThenCarListSizeIsZero() {
        carList.clear();
        assertEquals(0, carList.size());
    }

    @Test
    public void whenElementRemovedThenSizeMustBeDecreased() {
        Car car = new Car("Toyota", 15);
        carList.add(car);
        assertEquals(36, carList.size());
        assertTrue(carList.remove(car));
        assertEquals(35, carList.size());
    }

    @Test
    public void whenNonExistentElementRemovedThenReturnFalse() {
        Car car = new Car("Toyota", 15);
        assertFalse(carList.remove(car));
        assertEquals(35, carList.size());
    }

    @Test
    public void addingAnItemByIndex5() {
        assertEquals("Brand 5", carList.get(5).getBrand());
        Car car = new Car("BMW", 15);
        carList.add(car, 5);
        assertEquals("BMW", carList.get(5).getBrand());
        assertEquals("Brand 5", carList.get(6).getBrand());
    }

    @Test
    public void addsElementBeginningList() {
        assertEquals("Brand 0", carList.get(0).getBrand());
        Car car = new Car("BMW", 15);
        carList.add(car, 0);
        assertEquals("BMW", carList.get(0).getBrand());
        assertEquals("Brand 0", carList.get(1).getBrand());
    }

    @Test
    public void addElementElementEndList() {
        assertEquals("Brand 34", carList.get(34).getBrand());
        Car car = new Car("JT", 99);
        carList.add(car, 34);
        assertEquals("JT", carList.get(34).getBrand());
        assertEquals("Brand 34", carList.get(35).getBrand());
    }

    @Test
    public void removeElementBeginningList() {
        assertTrue(carList.removeAt(34));
        assertEquals(34, carList.size());
    }

    @Test
    public void removeElementElementEndList() {
        assertEquals("Brand 0", carList.get(0).getBrand());
        assertTrue(carList.removeAt(0));
        assertEquals("Brand 1", carList.get(0).getBrand());
    }
}