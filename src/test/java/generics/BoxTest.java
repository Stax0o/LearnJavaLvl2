package generics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoxTest {

    private Box<String, Integer, Float> box;
    private Box<String, Integer, Float> box2;

    @BeforeEach
    void setUp() {
        box = new Box<>("Привет", 2, 3.5f);
        box2 = new Box<>("Hello", 5, 6.5f);
    }

    @Test
    void testBox() {
        float result = box.getValue() + box.getElement() + box2.getValue() + box2.getElement();
        assertEquals(17, result);
    }
}