package studyingQueue;

import studyingArrayList.Car;
import studyingCollection.CarCollection;

public interface CarQueue<T> extends CarCollection<T> {
    boolean add(T car);

    T peek();

    T poll();
}
