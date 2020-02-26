package com.thoughtworks.model;

import com.thoughtworks.annotations.Alias;
import com.thoughtworks.annotations.Limit;

@Alias("学生")
public class Student {
    @Limit(min = 1, max = 140)
    private int age;
}
