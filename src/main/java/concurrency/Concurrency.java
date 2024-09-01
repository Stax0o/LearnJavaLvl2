package concurrency;

public class Concurrency {

    private static final int SIZE = 50_000_000;
    private static final int HALF_SIZE = SIZE / 2;

    public static void main(String[] args) {
        System.out.println("withConcurrency: " + withConcurrency());
        System.out.println("withoutConcurrency: " + withoutConcurrency());
    }

    private static long withoutConcurrency() {
        long start = System.currentTimeMillis();
        float[] array = new float[SIZE];

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
        float[] array = new float[SIZE];

        for (int i = 0; i < array.length; i++) {
            array[i] = 1f;
        }

        // Первая половина массива
        Thread Thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                float[] arrayFirstHalf = new float[HALF_SIZE];
                System.arraycopy(array, 0, arrayFirstHalf, 0, HALF_SIZE);
                for (int i = 0; i < arrayFirstHalf.length; i++) {
                    float f = (float) i;
                    arrayFirstHalf[i] = (float) (arrayFirstHalf[i] * Math.sin(0.2f + f / 5) * Math.cos(0.2f + f / 5) * Math.cos(0.4f + f / 2));
                }
                System.arraycopy(arrayFirstHalf, 0, array, 0, arrayFirstHalf.length);
            }
        });

        // Вторая половина массива
        Thread Thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                float[] arraySecondHalf = new float[HALF_SIZE];
                System.arraycopy(array, HALF_SIZE, arraySecondHalf, 0, HALF_SIZE);
                for (int i = 0; i < arraySecondHalf.length; i++) {
                    float f = (float) i;
                    arraySecondHalf[i] = (float) (arraySecondHalf[i] * Math.sin(0.2f + f / 5) * Math.cos(0.2f + f / 5) * Math.cos(0.4f + f / 2));
                }
                System.arraycopy(arraySecondHalf, 0, array, HALF_SIZE, arraySecondHalf.length);
            }
        });

        Thread1.start();
        Thread2.start();

        try {
            Thread1.join();
            Thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return System.currentTimeMillis() - start;
    }
}
