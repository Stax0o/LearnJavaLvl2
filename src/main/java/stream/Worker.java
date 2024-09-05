package stream;

@FunctionalInterface
public interface Worker {
    String work(int count);
}
