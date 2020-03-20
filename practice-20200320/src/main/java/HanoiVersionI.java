public class HanoiVersionI {
    private static int moves = 0;

    public static void main(String[] args) {
        move(5, 'A', 'C', 'B');
        System.out.printf("Total moves: %d", moves);
    }

    private static void move(int n, char src, char dest, char mid) {
        if (n == 1) {
            System.out.printf("%c -> %c\n", src, dest);
            ++moves;
        } else if (n > 1) {
            move(n - 1, src, mid, dest);  // move {1 ~ n-1} from A to B
            move(1, src, dest, mid);  // move {n} from A to C
            move(n - 1, mid, dest, src);  // move {1 ~ n-1} from B to C
        } else {
            throw new IllegalArgumentException();
        }
    }
}
