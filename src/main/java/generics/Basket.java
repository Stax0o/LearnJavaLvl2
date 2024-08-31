package generics;

import java.util.ArrayList;
import java.util.List;

public class Basket<T extends Fruit> {
    private List<T> fruits;

    public Basket() {
        fruits = new ArrayList<>();
    }

    public void add(T fruit) {
        fruits.add(fruit);
    }

    public double getWeight() {
        double result = 0;
        for (T fruit : fruits) {
            result += fruit.getWeight();
        }
        return result;
    }

    public int compare(Basket<?> another) {
        return Double.compare(getWeight(), another.getWeight());
    }

    public static <T extends Fruit> void transfer(Basket<? extends T> src, Basket<? super T> dest) {
        dest.fruits.addAll(src.fruits);
        src.fruits.clear();
    }
}
