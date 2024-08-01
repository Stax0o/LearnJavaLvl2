package studyingLinkedList;

import studyingArrayList.Car;
import studyingArrayList.CarList;

public class CarLinkedList implements CarList {
    private Node first = null;
    private Node last = null;
    private int size = 0;

    @Override
    public Car get(int index) {
        return getNode(index).value;
    }

    @Override
    public void add(Car car) {
        if (first == null) {
            first = new Node(null, car, null);
            last = first;
        } else {
            Node secondNode = last;
            last = new Node(secondNode, car, null);
            secondNode.next = last;
        }
        size++;
    }

    @Override
    public void add(Car car, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == size) {
            add(car);
            return;
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
    }

    @Override
    public boolean remove(Car car) {
        Node node = first;
        for (int i = 0; i < size; i++) {
            if (node.value.equals(car)) {
                return removeAt(i);
            }
            node = node.next;
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

    private static class Node {
        private Car value;
        private Node next;
        private Node prev;

        public Node(Node prev, Car car, Node next) {
            this.value = car;
            this.next = next;
            this.prev = prev;
        }
    }
}
