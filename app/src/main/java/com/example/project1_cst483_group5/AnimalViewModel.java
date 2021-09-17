package com.example.project1_cst483_group5;

import android.view.View;

import androidx.annotation.NonNull;

public class AnimalViewModel {


    private String name;
    private Integer id;
    private String type;
    private String age;
    private String gender;

    public AnimalViewModel(Integer id, String name, String type, String age, String gender) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.age = age;
        this.gender = gender;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
