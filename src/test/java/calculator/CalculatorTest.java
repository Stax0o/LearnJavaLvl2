package calculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void addSimple() {
        int expected = 15;
        int result = Calculator.add(10, 5);
        assertEquals(expected, result);
    }

    @Test
    void addString() {
        assertThrows(Exception.class, () -> Calculator.add("24", "fe"));
    }

    @Test
    void addAnotherSimple() {
        int expected = 48;
        int result = Calculator.add(39, 9);
        assertEquals(expected, result);
    }

    @Test
    void subtractSimple() {
        int expected = 44;
        int result = Calculator.subtract(108, 64);
        assertEquals(expected, result);
    }

    @Test
    void multiplySimple() {
        int expected = 125;
        int result = Calculator.multiply(25, 5);
        assertEquals(expected, result);
    }

    @Test
    void divideSimple() {
        double expected = 5.5625;
        double result = Calculator.divide(89, 16);
        assertEquals(expected, result, 0.001);
    }
}