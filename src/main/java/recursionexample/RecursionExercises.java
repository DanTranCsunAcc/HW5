package recursionexample;

/**
 * Assignment: Recursion mini-tasks.
 *
 * Implement the methods below following the assignment handout.
 * You may add private helper methods if you like.
 */
public class RecursionExercises {

    // Task 1: recursive factorial
    public static long factorialRecursive(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n must be >= 0");
        }
        if (n == 0 || n == 1) {
            return 1;
        }
        return n * factorialRecursive(n - 1);
    }

    // Task 2: iterative factorial
    public static long factorialIterative(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n must be >= 0");
        }
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    // Task 3: recursive Fibonacci
    public static long fibonacciRecursive(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n must be >= 0");
        }
        if (n == 0) return 0;
        if (n == 1) return 1;
        return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
    }

    // Task 4: recursive sum of an array (use the helper below)
    public static long sumArray(int[] data) {
        if (data == null) {
            throw new IllegalArgumentException("data cannot be null");
        }
        if (data.length == 0) {
            return 0;
        }
        return sumArrayFromIndex(data, 0);
    }

    // Helper for Task 4
    private static long sumArrayFromIndex(int[] data, int index) {
        if (index == data.length) {
            return 0;
        }
        return data[index] + sumArrayFromIndex(data, index + 1);
    }

    // Task 5: recursive string reverse
    public static String reverse(String s) {
        if (s == null) return null;
        if (s.length() <= 1) return s;
        return reverse(s.substring(1)) + s.charAt(0);
    }
}
