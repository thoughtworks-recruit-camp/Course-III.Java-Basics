package transport;

public class SelfDrive implements Transport {
    @Override
    public double getCost() {
        return 500;
    }

    @Override
    public String getFriendlyName() {
        return "自驾";
    }
}
