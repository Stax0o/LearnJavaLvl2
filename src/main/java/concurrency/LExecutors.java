package concurrency;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LExecutors {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        CountDownLatch countDownLatch = new CountDownLatch(3);

        //Timer
        long start = System.currentTimeMillis();

        executorService.execute(() -> {
            System.out.println("Задача 1: Начало");
            long result = 0;
            for (int i = 0; i < 1_000_000; i += 2) {
                result += i;
            }
            System.out.println("Задача 1: Конец - " + result);
            countDownLatch.countDown();
        });

        executorService.execute(() -> {
            System.out.println("Задача 2: Начало");
            long result = 0;
            for (int i = 0; i < 1_000_000; i += 7) {
                result += i;
            }
            System.out.println("Задача 2: Конец - " + result);
            countDownLatch.countDown();
        });

        executorService.execute(() -> {
            System.out.println("Задача 3: Начало");
            int[] array = new int[1_000];
            Random random = new Random();
            int count = 0;
            for (int i = 0; i < 1_000; i++) {
                array[i] = random.nextInt(1_000);
            }
            for (int i : array) {
                if (i % 2 == 0) {
                    count++;
                }
            }
            System.out.println("Задача 3: Конец - " + count);
            countDownLatch.countDown();
        });
        executorService.shutdown();

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        long end = System.currentTimeMillis();
        System.out.println("\nВремя выполнения программы: " + (end - start) + "ms");

    }
}
