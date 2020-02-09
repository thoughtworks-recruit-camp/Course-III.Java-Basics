import transport.*;

public class Demo {
    public static void main(String[] args) {
        double BUDGET = 300d;
        Transport[] transports = new Transport[]{new SelfDrive(), new Coach(), new Train(), new Flight()};
        for (Transport transport : transports) {
            if (transport.getCost() <= BUDGET) {
                System.out.printf("小明可以选择%s，花费%d元", transport.getFriendlyName(), (int) transport.getCost());
            }
        }
    }
}
