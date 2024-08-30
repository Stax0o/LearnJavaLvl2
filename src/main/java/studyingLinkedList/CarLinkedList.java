package studyingLinkedList;

import studyingArrayList.Car;
import studyingArrayList.CarList;
import studyingQueue.CarQueue;

import java.util.Iterator;

public class CarLinkedList<T> implements CarList<T>, CarQueue<T> {
    private Node first = null;
    private Node last = null;
    private int size = 0;

    @Override
    public T get(int index) {
        return getNode(index).value;
    }

    @Override
    public boolean add(T car) {
        if (first == null) {
            first = new Node(null, car, null);
            last = first;
        } else {
            Node secondNode = last;
            last = new Node(secondNode, car, null);
            secondNode.next = last;
        }
        size++;
        return true;
    }

    @Override
    public boolean add(T car, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == size) {
            return add(car);
        }
        Node node = getNode(index);

        Node prevNode = node.prev;
        Node newNode = new Node(prevNode, car, node);
        node.prev = newNode;
        if (prevNode != null) {
            prevNode.next = newNode;
        } else {
            first = newNode;
        }
        size++;
        return true;
    }

    @Override
    public T peek() {
        return size > 0 ? get(0) : null;
    }

    @Override
    public T poll() {
        T car = get(0);
        removeAt(0);
        return car;
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
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        Node node = getNode(index);
        Node prevNode = node.prev;
        Node nextNode = node.next;

        if (nextNode != null) {
            nextNode.prev = prevNode;
        } else {
            last = nextNode;
        }

        if (prevNode != null) {
            prevNode.next = nextNode;
        } else {
            first = nextNode;
        }
        size--;
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        first = null;
        last = null;
        size = 0;
    }

    @Override
    public boolean contains(T car) {
        return findElement(car) != -1;
    }

    @Override
    public Iterator<T> iterator() {

        return new Iterator<T>() {

            Node node = first;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                T car = node.value;
                node = node.next;
                return car;
            }
        };
    }

    private int findElement(T car) {
        Node node = first;
        for (int i = 0; i < size; i++) {
            if (node.value.equals(car)) {
                return i;
            }
            node = node.next;
        }
        return -1;
    }

    private Node getNode(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node node = first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    private class Node {
        private T value;
        private Node next;
        private Node prev;

        public Node(Node prev, T car, Node next) {
            this.value = car;
            this.next = next;
            this.prev = prev;
        }
    }
}
