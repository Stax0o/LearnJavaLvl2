package studyingHashSet;

import studyingArrayList.Car;
import studyingCollection.CarCollection;

public interface CarSet extends CarCollection {
    boolean add(Car car);

    boolean remove(Car car);

    int size();

    void clear();
}
