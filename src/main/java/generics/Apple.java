package generics;

public class Apple extends Fruit {
    public Apple() {
        super(1);
    }

    @Override
    public double getWeight() {
        return super.getWeight();
    }
}
