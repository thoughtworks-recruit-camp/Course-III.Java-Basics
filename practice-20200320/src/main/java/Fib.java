public class Fib {
    // 1, 1, 2, 3, 5, 8, 13
    public static void main(String[] args) {
        System.out.println(fib(1));
        System.out.println(fib(2));
        System.out.println(fib(5));
        System.out.println(fib(7));
        System.out.println(fib(20));  // 6765
    }

    private static int fib(int n) {
        if (n >= 3) {
            return fib(n - 1) + fib(n - 2);
        } else if (n > 0) {
            return 1;
        } else {
            throw new IllegalArgumentException();
        }
    }
}
