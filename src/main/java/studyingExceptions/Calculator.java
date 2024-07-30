package studyingExceptions;

public class Calculator {
    public static int add(int a, int b) {
        return a + b;
    }

    public static int add(String a, String b) {
        int numA = Integer.parseInt(a);
        int numB = Integer.parseInt(b);
        return numA + numB;
    }

    public static int subtract(int a, int b) {
        return a - b;
    }

    public static int multiply(int a, int b) {
        return a * b;
    }

    public static double divide(double a, double b) {
        return a / b;
    }
}
