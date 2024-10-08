package studyingArrayList;

import java.util.Arrays;
import java.util.Iterator;

public class CarArrayList<T> implements CarList<T> {
    private Object[] cars = new Object[10];
    private int size = 0;

    @Override
    public T get(int index) {
        checkIndex(index);
        return (T) cars[index];
    }

    @Override
    public boolean add(T car, int index) {
        increaseArray();
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        System.arraycopy(cars, index, cars, index + 1, size - index);
        cars[index] = car;
        size++;
        return true;
    }

    @Override
    public boolean contains(T car) {
        return findElement(car) != -1;
    }

    @Override
    public boolean add(T car) {
        increaseArray();
        cars[size] = car;
        size++;
        return true;
    }

    @Override
    public boolean remove(T car) {
        int index = findElement(car);
        if (index != -1) {
            removeAt(index);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeAt(int index) {
        checkIndex(index);
        System.arraycopy(cars, index + 1, cars, index, size - 1 - index);
        size--;
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        cars = new Car[10];
        size = 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            int index = 0;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public T next() {
                return (T) cars[index++];
            }
        };
    }

    private int findElement(T car) {
        for (int i = 0; i < size; i++) {
            if (car.equals(cars[i])) {
                return i;
            }
        }
        return -1;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void increaseArray() {
        if (size >= cars.length) {
            cars = Arrays.copyOf(cars, cars.length * 2);
        }
    }
}
