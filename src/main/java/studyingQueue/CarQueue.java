package studyingQueue;

import studyingArrayList.Car;
import studyingCollection.CarCollection;

public interface CarQueue extends CarCollection {
    boolean add(Car car);

    Car peek();

    Car poll();
}
