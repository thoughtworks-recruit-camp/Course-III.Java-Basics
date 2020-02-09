package transport;

public class Coach implements Transport {
    @Override
    public double getCost() {
        return 300;
    }

    @Override
    public String getFriendlyName() {
        return "大巴车";
    }
}
