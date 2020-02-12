package person;

public enum Gender {
    Female("Female"),
    Male("Male");

    public final String genderStr;

    Gender(String genderStr) {
        this.genderStr = genderStr;
    }

    @Override
    public String toString() {
        return this.genderStr;
    }
}
