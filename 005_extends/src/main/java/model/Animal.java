package model;

public abstract class Animal {
    private int age;
    private int weight;

    public Animal() {
    }

    public Animal(int age, int weight) {
        this.age = age;
        this.weight = weight;
    }

    public void eat(String food) {
        System.out.printf("A %s is eating %s.\n", this.getName(), food);
    }

    public void eat(Animal food) {
        System.out.printf("A %s is eating a %s.\n", this.getName(), food.getName());
    }

    public void sleep() {
        System.out.printf("A %s is sleeping.\n", this.getName());
    }

    public abstract String getName();
}
