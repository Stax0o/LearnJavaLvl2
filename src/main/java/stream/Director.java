package stream;

public class Director {
    public String force(Worker worker, int count) {
        return worker.work(count);
    }
}
