import java.util.Collections;
import java.util.LinkedList;
import java.util.stream.IntStream;

public class ColumnAdv {
    private final LinkedList<Integer> disks = new LinkedList<>();
    private final char name;

    public ColumnAdv(char name) {
        this.name = name;
    }

    public LinkedList<Integer> getDisks() {
        return disks;
    }

    public void initDisks(int n) {
        IntStream.rangeClosed(1, n).boxed().forEach(disks::add);
        Collections.reverse(disks);
    }

    public void addDisk(Integer disk) {
        disks.add(disk);
    }

    public void moveDiskTo(ColumnAdv target) {
        target.addDisk(disks.removeLast());
    }

    @Override
    public String toString() {
        return String.format("%c", name);
    }
}
