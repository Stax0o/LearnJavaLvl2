package stream;

public class Stream {
    public static void main(String[] args) {
        Director director = new Director();
        String result = director.force((int n) -> {
            for (int i = 0; i < n; i++) {
                System.out.println("Hello World");
            }
            return "Success";
        }, 5);

        System.out.println(result);
    }
}
