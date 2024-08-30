package studyingQueue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import studyingArrayList.Car;
import studyingLinkedList.CarLinkedList;

import static org.junit.jupiter.api.Assertions.*;

class CarQueueTest {

    private CarQueue<Car> carQueue;

    @BeforeEach
    void setUp() {
        carQueue = new CarLinkedList();
        for (int i = 0; i < 10; i++) {
            carQueue.add(new Car("Brand " + i, i));
        }
    }

    @Test
    void addCar() {
        assertEquals(10, carQueue.size());
    }

    @Test
    void peek() {
        Car car = carQueue.peek();
        assertEquals("Brand 0", car.getBrand());
        assertEquals(10, carQueue.size());
    }

    @Test
    void poll() {
        Car car = carQueue.poll();
        assertEquals("Brand 0", car.getBrand());
        assertEquals(9, carQueue.size());
    }
}