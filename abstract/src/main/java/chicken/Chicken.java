package chicken;

public abstract class Chicken {
    double price;

    public double getPrice() {
        return this.price;
    }

    public String getType() {
        return getClass().getSimpleName();
    }
}
