package studyingArrayList;

import java.util.Arrays;

public class CarArrayList implements CarList {
    private Car[] cars = new Car[10];
    private int size = 0;

    @Override
    public Car get(int index) {
        checkIndex(index);
        return cars[index];
    }

    @Override
    public void add(Car car, int index) {
        increaseArray();
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        System.arraycopy(cars, index, cars, index + 1, size - index);
        cars[index] = car;
        size++;
    }

    @Override
    public void add(Car car) {
        increaseArray();
        cars[size] = car;
        size++;
    }

    @Override
    public boolean remove(Car car) {
        for (int i = 0; i < size; i++) {
            if (cars[i].getBrand().equals(car.getBrand()) &&
                    cars[i].getNumber() == car.getNumber()) {
                return removeAt(i);
            }
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
