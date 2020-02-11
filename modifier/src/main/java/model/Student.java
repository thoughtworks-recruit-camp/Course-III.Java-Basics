package model;

import java.math.BigDecimal;

public class Student {

    private final String name;
    private BigDecimal rating;

    public Student(String name, String ratingStr) {
        this.name = name;
        this.rating = new BigDecimal(ratingStr);
    }

    public String getName() {
        return this.name;
    }

    void modifyRating(String offsetStr) {
        this.rating = this.rating.add(new BigDecimal(offsetStr));
    }

    public void printSelfInfo() {
        int intRating = this.rating.intValue();
        if (this.rating.compareTo(new BigDecimal(intRating)) == 0) {
            System.out.printf("学生姓名：%s\t成绩：%d\n", this.name, intRating);
        } else {
            System.out.printf("学生姓名：%s\t成绩：%f\n", this.name, this.rating.floatValue());
        }
    }
}
