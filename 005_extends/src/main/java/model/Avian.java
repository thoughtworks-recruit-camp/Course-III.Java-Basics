package model;

public abstract class Avian extends Animal {
    private double flightSpeed;

    public Avian() {
    }

    public Avian(int age, int weight) {
        super(age, weight);
    }

//    public Avian(int age, int weight, double flightSpeed) {
//        super(age, weight);
//        this.flightSpeed = flightSpeed;
//    }

    public void fly() {
        System.out.printf("A %s is flying.\n", this.getName());
    }

//    public void fly(double speed) {
//        System.out.printf("A %s is flying at %.2fm/s.\n", this.getClass().toString(), speed);
//    }

}
