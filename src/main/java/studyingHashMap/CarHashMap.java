package studyingHashMap;

import studyingArrayList.Car;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CarHashMap implements CarMap {

    private static final int DEFAULT_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private Entry[] array = new Entry[DEFAULT_CAPACITY];
    private int size = 0;

    @Override
    public void put(CarOwner key, Car value) {
        if (size >= (array.length * DEFAULT_LOAD_FACTOR)) {
            increaseCapacity();
        }
        if (put(key, value, array)) {
            size++;
        }
    }

    private boolean put(CarOwner key, Car value, Entry[] dst) {
        int position = getElementPosition(key, dst.length);
        Entry existedEntry = dst[position];
        if (existedEntry == null) {
            dst[position] = new Entry(key, value, null);
            return true;
        } else {
            while (true) {
                if (existedEntry.key.equals(key)) {
                    existedEntry.value = value;
                    return false;
                }
                if (existedEntry.next == null) {
                    existedEntry.next = new Entry(key, value, null);
                    return true;
                }
                existedEntry = existedEntry.next;
            }

        }
    }

    @Override
    public Car get(CarOwner key) {
        int position = getElementPosition(key, array.length);
        Entry existedEntry = array[position];
        while (existedEntry != null) {
            if (existedEntry.key.equals(key)) {
                return existedEntry.value;
            }
            existedEntry = existedEntry.next;
        }
        return null;
    }

    @Override
    public Set<CarOwner> keySet() {
        Set<CarOwner> keySet = new HashSet<>();
        for (Entry entry : array) {
            Entry existedEntry = entry;
            while (existedEntry != null) {
                keySet.add(existedEntry.key);
                existedEntry = existedEntry.next;
            }
        }
        return keySet;
    }

    @Override
    public List<Car> values() {
        List<Car> keySet = new ArrayList<>();
        for (Entry entry : array) {
            Entry existedEntry = entry;
            while (existedEntry != null) {
                keySet.add(existedEntry.value);
                existedEntry = existedEntry.next;
            }
        }
        return keySet;
    }

    @Override
    public boolean remove(CarOwner key) {
        int position = getElementPosition(key, array.length);
        Entry existedEntry = array[position];
        if (existedEntry != null && existedEntry.key.equals(key)) {
            array[position] = existedEntry.next;
            size--;
            return true;
        } else {
            while (existedEntry != null) {
                Entry nextElement = existedEntry.next;
                if (nextElement == null) {
                    return false;
                }
                if (nextElement.key.equals(key)) {
                    existedEntry.next = nextElement.next;
                    size--;
                    return true;
                }
                existedEntry = existedEntry.next;
            }
        }

        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        array = new Entry[DEFAULT_CAPACITY];
        size = 0;
    }

    private int getElementPosition(CarOwner key, int arrayLenght) {
        return key.hashCode() % arrayLenght;
    }

    private void increaseCapacity() {
        Entry[] newArray = new Entry[array.length * 2];
        for (Entry entry : array) {
            Entry existedEntry = entry;
            while (existedEntry != null) {
                put(existedEntry.key, existedEntry.value, newArray);
                existedEntry = existedEntry.next;
            }
        }
        array = newArray;
    }

    private static class Entry {
        private CarOwner key;
        private Car value;
        private Entry next;

        public Entry(CarOwner key, Car value, Entry next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
}
