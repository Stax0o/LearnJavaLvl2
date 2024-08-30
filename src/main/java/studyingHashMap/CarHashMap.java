package studyingHashMap;

import studyingArrayList.Car;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CarHashMap<K, V> implements CarMap<K, V> {

    private static final int DEFAULT_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private Object[] array = new Object[DEFAULT_CAPACITY];
    private int size = 0;

    @Override
    public void put(K key, V value) {
        if (size >= (array.length * DEFAULT_LOAD_FACTOR)) {
            increaseCapacity();
        }
        if (put(key, value, array)) {
            size++;
        }
    }

    private boolean put(K key, V value, Object[] dst) {
        int position = getElementPosition(key, dst.length);
        Entry existedEntry = (Entry) dst[position];
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
    public V get(K key) {
        int position = getElementPosition(key, array.length);
        Entry existedEntry = (Entry) array[position];
        while (existedEntry != null) {
            if (existedEntry.key.equals(key)) {
                return existedEntry.value;
            }
            existedEntry = existedEntry.next;
        }
        return null;
    }

    @Override
    public Set<K> keySet() {
        Set<K> keySet = new HashSet<>();
        for (Object entry : array) {
            Entry existedEntry = (Entry) entry;
            while (existedEntry != null) {
                keySet.add(existedEntry.key);
                existedEntry = existedEntry.next;
            }
        }
        return keySet;
    }

    @Override
    public List<V> values() {
        List<V> keySet = new ArrayList<>();
        for (Object entry : array) {
            Entry existedEntry = (Entry) entry;
            while (existedEntry != null) {
                keySet.add(existedEntry.value);
                existedEntry = existedEntry.next;
            }
        }
        return keySet;
    }

    @Override
    public boolean remove(K key) {
        int position = getElementPosition(key, array.length);
        Entry existedEntry = (Entry) array[position];
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
        array = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    private int getElementPosition(K key, int arrayLenght) {
        return key.hashCode() % arrayLenght;
    }

    private void increaseCapacity() {
        Object[] newArray = new Object[array.length * 2];
        for (Object entry : array) {
            Entry existedEntry = (Entry) entry;
            while (existedEntry != null) {
                put(existedEntry.key, existedEntry.value, newArray);
                existedEntry = existedEntry.next;
            }
        }
        array = newArray;
    }

    private class Entry {
        private K key;
        private V value;
        private Entry next;

        public Entry(K key, V value, Entry next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
}
