public class HanoiVersionII {
    private static Column columnA = new Column('A');
    private static Column columnB = new Column('B');
    private static Column columnC = new Column('C');
    private static int moves = 0;

    public static void main(String[] args) {
        columnA.setCount(5);
        move(5, columnA, columnC, columnB);
        System.out.printf("Total moves: %d", moves);
    }

    private static void move(int n, Column src, Column dest, Column mid) {
        if (n == 1) {
            moveOne(src, dest);
        } else if (n > 1) {
            move(n - 1, src, mid, dest);  // move {1 ~ n-1} from A to B
            moveOne(src, dest);  // move {n} from A to C
            move(n - 1, mid, dest, src);  // move {1 ~ n-1} from B to C
        } else {
            throw new IllegalArgumentException();
        }
    }

    private static void moveOne(Column src, Column dest) {
        src.reduceCount();
        dest.addCount();
        ++moves;
        System.out.printf("%s -> %s\n", src, dest);
        printStatus();
    }

    private static void printStatus() {
        System.out.printf("%d %d %d\n", columnA.getCount(), columnB.getCount(), columnC.getCount());
    }
}
