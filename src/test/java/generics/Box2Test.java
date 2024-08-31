package generics;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Box2Test {

    @Test
    void transfer() {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            list1.add(i);
        }

        Box2.transfer(list1, list2);
        assertEquals(0, list1.size());
        assertEquals(10, list2.size());

    }
}