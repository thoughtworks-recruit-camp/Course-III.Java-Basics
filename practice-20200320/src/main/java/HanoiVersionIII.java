public class HanoiVersionIII {
    private static ColumnAdv columnA = new ColumnAdv('A');
    private static ColumnAdv columnB = new ColumnAdv('B');
    private static ColumnAdv columnC = new ColumnAdv('C');
    private static int moves = 0;

    public static void main(String[] args) {
        columnA.initDisks(5);
        move(5, columnA, columnC, columnB);
        System.out.printf("Total moves: %d", moves);
    }

    private static void move(int n, ColumnAdv src, ColumnAdv dest, ColumnAdv mid) {
        if (n == 1) {
            moveOne(src, dest);
        } else {
            move(n - 1, src, mid, dest);  // move {1 ~ n-1} from A to B
            moveOne(src, dest);  // move {n} from A to C
            move(n - 1, mid, dest, src);  // move {1 ~ n-1} from B to C
        }
    }

    private static void moveOne(ColumnAdv src, ColumnAdv dest) {
        src.moveDiskTo(dest);
        ++moves;
        System.out.printf("%s --%d-> %s\n", src,dest.getDisks().getLast(), dest);
        printStatus();
    }

    private static void printStatus() {
        System.out.printf("A: %20s \t B: %20s \t C: %20s\n", columnA.getDisks(), columnB.getDisks(), columnC.getDisks());
    }
}
