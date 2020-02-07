package model;

public class Student {
    public enum Gender {Male, Female, Other}

    private String name;
    private int age;
    private Gender gender;

    public Student(String name, int age, Gender gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public void study() {
        System.out.printf("%s 正在学习\n", this.name);
    }

    public void relax() {
        System.out.printf("%s 正在休息\n", this.name);
    }

    private String getGender() {
        switch (this.gender) {
            case Male:
                return "男";
            case Female:
                return "女";
            case Other:
                return "其他";
            default:
                return "未知";
        }
    }

    public void printInfo() {
        System.out.printf("姓名：%s  年龄：%d  性别：%s\n", this.name, this.age, this.getGender());
    }
}
