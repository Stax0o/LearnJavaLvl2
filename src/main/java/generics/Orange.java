package generics;

public class Orange extends Fruit {
    public Orange() {
        super(1.5);
    }

    @Override
    public double getWeight() {
        return super.getWeight();
    }
}
