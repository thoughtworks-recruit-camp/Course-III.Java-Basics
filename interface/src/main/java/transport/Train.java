package transport;

public class Train implements Transport {
    @Override
    public double getCost() {
        return 400;
    }

    @Override
    public String getFriendlyName() {
        return "火车";
    }
}
