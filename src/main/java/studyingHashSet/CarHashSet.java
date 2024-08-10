package studyingHashSet;

import studyingArrayList.Car;

public class CarHashSet implements CarSet {

    private static final int DEFAULT_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private int size = 0;
    private Entry[] array = new Entry[DEFAULT_CAPACITY];

    @Override
    public boolean contains(Car car) {
        int position = getElementPosition(car, array.length);
        if (array[position] == null) {
            return false;
        }
        Entry secondLast = array[position];
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
    public boolean add(Car car) {
        if (size >= (array.length * DEFAULT_LOAD_FACTOR)) {
            increaseArray();
        }
        boolean added = add(car, array);
        if (added) {
            size++;
        }
        return added;
    }

    private boolean add(Car car, Entry[] dst) {
        int position = getElementPosition(car, dst.length);
        if (dst[position] == null) {
            Entry entry = new Entry(car, null);
            dst[position] = entry;
            return true;
        } else {
            Entry existenceElement = dst[position];
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
    public boolean remove(Car car) {
        int position = getElementPosition(car, array.length);
        if (array[position] == null) {
            return false;
        }
        Entry secondLast = array[position];
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
        array = new Entry[DEFAULT_CAPACITY];
        size = 0;
    }

    private void increaseArray() {
        Entry[] newArray = new Entry[array.length * 2];
        for (Entry entry : array) {
            Entry existenceElement = entry;
            while (existenceElement != null) {
                add(existenceElement.value, newArray);
                existenceElement = existenceElement.next;
            }
        }
        array = newArray;
    }

    private static class Entry {
        private final Car value;
        private Entry next;

        public Entry(Car value, Entry next) {
            this.value = value;
            this.next = next;
        }
    }

    private int getElementPosition(Car car, int arrayLength) {
        return car.hashCode() % arrayLength;
    }
}
