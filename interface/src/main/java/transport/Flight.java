package transport;

public class Flight implements Transport {
    @Override
    public double getCost() {
        return 1000;
    }

    @Override
    public String getFriendlyName() {
        return "飞机";
    }
}
