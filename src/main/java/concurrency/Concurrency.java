package concurrency;

public class Concurrency {
    public static void main(String[] args) {
        System.out.println("Один поток: " + withoutConcurrency());
        System.out.println("Два потока: " + withConcurrency());
    }

    private static long withoutConcurrency() {
        long start = System.currentTimeMillis();
        float[] array = new float[10_000_000];

        for (int i = 0; i < array.length; i++) {
            array[i] = 1f;
        }

        for (int i = 0; i < array.length; i++) {
            array[i] = (float) (array[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }

        return System.currentTimeMillis() - start;
    }

    private static long withConcurrency() {
        long start = System.currentTimeMillis();
        float[] array = new float[10_000_000];

        for (int i = 0; i < array.length; i++) {
            array[i] = 1f;
        }

        // Первая половина массива
        Thread firstThread = new Thread(new Runnable() {
            @Override
            public void run() {
                float[] arrayFirstHalf = new float[5_000_000];
                System.arraycopy(array, 0, arrayFirstHalf, 0, array.length / 2);
                for (int i = 0; i < arrayFirstHalf.length; i++) {
                    arrayFirstHalf[i] = (float) (arrayFirstHalf[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
                System.arraycopy(arrayFirstHalf, 0, array, 0, arrayFirstHalf.length);
            }
        });

        // Вторая половина массива
        Thread secondThread = new Thread(new Runnable() {
            @Override
            public void run() {
                float[] arraySecondHalf = new float[5_000_000];
                System.arraycopy(array, 0, arraySecondHalf, 0, array.length / 2);
                for (int i = 0; i < arraySecondHalf.length; i++) {
                    arraySecondHalf[i] = (float) (arraySecondHalf[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
                System.arraycopy(arraySecondHalf, 0, array, 4_999_999, arraySecondHalf.length);
            }
        });

        firstThread.start();
        secondThread.start();

        try {
            firstThread.join();
            secondThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return System.currentTimeMillis() - start;
    }
}
