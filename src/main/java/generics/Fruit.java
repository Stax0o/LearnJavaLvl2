package generics;

public abstract class Fruit {
    private double weight;

    Fruit(double weight) {
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }
}
