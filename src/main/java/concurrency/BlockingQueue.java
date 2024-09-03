package concurrency;

public class BlockingQueue {
    private static final Object MONITOR = new Object();
    private static final String A = "A";
    private static final String B = "B";
    private static final String C = "C";
    private static String nextLetter = "A";

    public static void main(String[] args) {
        Runnable taskA = () -> {
            synchronized (MONITOR) {
                for (int i = 0; i < 5; i++) {
                    try {
                        while (!nextLetter.equals(A)) {
                            MONITOR.wait();
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.print(A);
                    nextLetter = B;
                    MONITOR.notifyAll();
                }
            }
        };

        Runnable taskB = () -> {
            synchronized (MONITOR) {
                for (int i = 0; i < 5; i++) {
                    try {
                        while (!nextLetter.equals(B)) {
                            MONITOR.wait();
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.print(B);
                    nextLetter = C;
                    MONITOR.notifyAll();
                }
            }
        };

        Runnable taskC = () -> {
            synchronized (MONITOR) {
                for (int i = 0; i < 5; i++) {
                    try {
                        while (!nextLetter.equals(C)) {
                            MONITOR.wait();
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.print(C);
                    nextLetter = A;
                    MONITOR.notifyAll();
                }
            }
        };

        new Thread(taskA).start();
        new Thread(taskB).start();
        new Thread(taskC).start();

    }

}
