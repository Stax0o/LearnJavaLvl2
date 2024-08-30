package generics;

public class Box<K, V, T> {
    private K key;
    private V value;
    private T element;

    public Box(K key, V value, T element) {
        this.key = key;
        this.value = value;
        this.element = element;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }
}
