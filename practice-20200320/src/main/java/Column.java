public class Column {
    private int count = 0;
    private char name;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Column(char name) {
        this.name = name;
    }

    public void addCount() {
        ++this.count;
    }

    public void reduceCount() {
        --this.count;
    }

    @Override
    public String toString() {
        return String.format("%c", name);
    }
}
