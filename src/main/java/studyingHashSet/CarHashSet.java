package studyingHashSet;

import java.util.Iterator;

public class CarHashSet<T> implements CarSet<T> {

    private static final int DEFAULT_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private int size = 0;
    private Object[] array = new Object[DEFAULT_CAPACITY];

    @Override
    public boolean contains(T car) {
        int position = getElementPosition(car, array.length);
        if (array[position] == null) {
            return false;
        }
        Entry secondLast = (Entry) array[position];
        Entry last = secondLast.next;

        if (secondLast.value.equals(car)) {
            return true;
        }

        while (last != null) {
            if (last.value.equals(car)) {
                return true;
            } else {
                last = last.next;
            }
        }
        return false;
    }

    @Override
    public boolean add(T car) {
        if (size >= (array.length * DEFAULT_LOAD_FACTOR)) {
            increaseArray();
        }
        boolean added = add(car, array);
        if (added) {
            size++;
        }
        return added;
    }

    private boolean add(T car, Object[] dst) {
        int position = getElementPosition(car, dst.length);
        if (dst[position] == null) {
            Entry entry = new Entry(car, null);
            dst[position] = entry;
            return true;
        } else {
            Entry existenceElement = (Entry) dst[position];
            while (true) {
                if (existenceElement.value.equals(car)) {
                    return false;
                } else if (existenceElement.next == null) {
                    existenceElement.next = new Entry(car, null);
                    return true;
                } else {
                    existenceElement = existenceElement.next;
                }
            }
        }
    }

    @Override
    public boolean remove(T car) {
        int position = getElementPosition(car, array.length);
        if (array[position] == null) {
            return false;
        }
        Entry secondLast = (Entry) array[position];
        Entry last = secondLast.next;

        if (secondLast.value.equals(car)) {
            array[position] = last;
            size--;
            return true;
        }

        while (last != null) {
            if (last.value.equals(car)) {
                secondLast.next = last.next;
                size--;
                return true;
            } else {
                secondLast = last;
                last = last.next;
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

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            int index = 0;
            int arrayIndex = 0;
            Entry entry;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public T next() {
                while (array[arrayIndex] == null) {
                    arrayIndex++;
                }
                if (entry == null) {
                    entry = (Entry) array[arrayIndex];
                }
                T result = entry.value;
                entry = entry.next;
                if (entry == null) {
                    arrayIndex++;
                }
                index++;
                return result;
            }
        };
    }

    private void increaseArray() {
        Object[] newArray = new Object[array.length * 2];
        for (Object entry : array) {
            Entry existenceElement = (Entry) entry;
            while (existenceElement != null) {
                add(existenceElement.value, newArray);
                existenceElement = existenceElement.next;
            }
        }
        array = newArray;
    }

    private class Entry {
        private final T value;
        private Entry next;

        public Entry(T value, Entry next) {
            this.value = value;
            this.next = next;
        }
    }

    private int getElementPosition(T car, int arrayLength) {
        return car.hashCode() % arrayLength;
    }
}
