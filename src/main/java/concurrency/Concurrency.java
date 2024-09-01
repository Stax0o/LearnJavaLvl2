package concurrency;

public class Concurrency {

    // Array size
    private static final int SIZE = 50_000_000;

    // Half of the array size, used to split the work between threads
    private static final int HALF_SIZE = SIZE / 2;

    public static void main(String[] args) {
        // Call the method using concurrency
        System.out.println("withConcurrency: " + withConcurrency());

        // Call the method without using concurrency
        System.out.println("withoutConcurrency: " + withoutConcurrency());
    }

    private static long withoutConcurrency() {
        // Record the start time
        long start = System.currentTimeMillis();

        // Create an array of size SIZE and fill it with ones
        float[] array = new float[SIZE];
        for (int i = 0; i < array.length; i++) {
            array[i] = 1f;
        }

        // Perform calculations on each element of the array
        for (int i = 0; i < array.length; i++) {
            array[i] = (float) (array[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }

        // Return the execution time
        return System.currentTimeMillis() - start;
    }

    private static long withConcurrency() {
        // Record the start time
        long start = System.currentTimeMillis();

        // Create an array of size SIZE and fill it with ones
        float[] array = new float[SIZE];
        for (int i = 0; i < array.length; i++) {
            array[i] = 1f;
        }

        // Create a thread to process the first half of the array
        Thread Thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                // Copy the first half of the array into a separate array
                float[] arrayFirstHalf = new float[HALF_SIZE];
                System.arraycopy(array, 0, arrayFirstHalf, 0, HALF_SIZE);

                // Perform calculations on each element of the first half of the array
                for (int i = 0; i < arrayFirstHalf.length; i++) {
                    float f = (float) i;
                    arrayFirstHalf[i] = (float) (arrayFirstHalf[i] * Math.sin(0.2f + f / 5) * Math.cos(0.2f + f / 5) * Math.cos(0.4f + f / 2));
                }

                // Copy the processed data back into the original array
                System.arraycopy(arrayFirstHalf, 0, array, 0, arrayFirstHalf.length);
            }
        });

        // Create a thread to process the second half of the array
        Thread Thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                // Copy the second half of the array into a separate array
                float[] arraySecondHalf = new float[HALF_SIZE];
                System.arraycopy(array, HALF_SIZE, arraySecondHalf, 0, HALF_SIZE);

                // Perform calculations on each element of the second half of the array
                for (int i = 0; i < arraySecondHalf.length; i++) {
                    float f = (float) i;
                    arraySecondHalf[i] = (float) (arraySecondHalf[i] * Math.sin(0.2f + f / 5) * Math.cos(0.2f + f / 5) * Math.cos(0.4f + f / 2));
                }

                // Copy the processed data back into the original array
                System.arraycopy(arraySecondHalf, 0, array, HALF_SIZE, arraySecondHalf.length);
            }
        });

        // Start both threads
        Thread1.start();
        Thread2.start();

        // Wait for both threads to complete
        try {
            Thread1.join();
            Thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Return the execution time
        return System.currentTimeMillis() - start;
    }
}
