package com.thoughtworks.model;

import com.thoughtworks.annotations.Alias;
import com.thoughtworks.annotations.Limit;

public class Demo {
    public static void main(String[] args) {
        Class<Student> studentClass = Student.class;
        System.out.println(studentClass.getAnnotation(Alias.class).value());
        System.out.println(studentClass.getDeclaredFields()[0].getAnnotation(Limit.class).min());
        System.out.println(studentClass.getDeclaredFields()[0].getAnnotation(Limit.class).max());
    }
}

