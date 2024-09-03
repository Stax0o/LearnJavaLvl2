package concurrency;

import java.util.concurrent.*;

public class LExecutors2 {
    public static void main(String[] args) {
        // Используем лямбда-выражение для упрощения создания ThreadFactory
        ThreadFactory daemonThreadFactory = r -> {
            Thread thread = new Thread(r);
            thread.setDaemon(true);
            return thread;
        };

        // Создание ExecutorService с использованием фабрики потоков для демон-потока
        ExecutorService daemonExecutor = Executors.newSingleThreadExecutor(daemonThreadFactory);
        daemonExecutor.execute(() -> {
            try {
                while (true) {
                    System.out.print(".");
                    Thread.sleep(300);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);

            }
        });

        // Создание ExecutorService с фиксированным количеством потоков для задач
        ExecutorService taskExecutor = Executors.newFixedThreadPool(2);
        Future<String> futureName = taskExecutor.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(5000);
                return "John";
            }
        });

        Future<Integer> futureAge = taskExecutor.submit(() -> {
            Thread.sleep(4000);
            return 34;
        });

        daemonExecutor.shutdown();
        taskExecutor.shutdown();

        String name;
        int age;
        try {
            name = futureName.get();
            age = futureAge.get();
            System.out.printf("\n%s %d years old", name, age);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

    }
}
