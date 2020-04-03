package com.thoughtworks;

import com.thoughtworks.repository.PrimaryKey;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    @PrimaryKey
    private String id;
    private String name;
    private String gender;

    public void setId(Object id) {
        this.id = (String) id;
    }

    public void setName(Object name) {
        this.name = (String) name;
    }

    public void setGender(Object gender) {
        this.gender = (String) gender;
    }
}
