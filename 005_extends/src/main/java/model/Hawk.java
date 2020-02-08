package model;

public class Hawk extends Avian {
    public Animal preyTarget;

    public Hawk() {
    }

    public Hawk(int age, int weight, Animal preyTarget) {
        super(age, weight);
        this.preyTarget = preyTarget;
    }

    @Override
    public String getName() {
        return "Hawk";
    }

    public void preyAction() {
        System.out.printf("A %s is preying on a %s.\n", this.getName(), this.preyTarget.getName());
    }
}
