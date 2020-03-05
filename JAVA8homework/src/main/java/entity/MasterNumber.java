package entity;

public class MasterNumber {
    private String number;

    public MasterNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return number;
    }
}