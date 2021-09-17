package com.example.project1_cst483_group5;

import android.view.View;

import androidx.annotation.NonNull;

/**
 * The type Animal view model.
 */
public class AnimalViewModel {


    private String name;
    private Integer id;
    private String type;
    private String age;
    private String gender;

    /**
     * Instantiates a new Animal view model.
     *
     * @param id     the id
     * @param name   the name
     * @param type   the type
     * @param age    the age
     * @param gender the gender
     */
    public AnimalViewModel(Integer id, String name, String type, String age, String gender) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.age = age;
        this.gender = gender;
    }


    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets age.
     *
     * @return the age
     */
    public String getAge() {
        return age;
    }

    /**
     * Sets age.
     *
     * @param age the age
     */
    public void setAge(String age) {
        this.age = age;
    }

    /**
     * Gets gender.
     *
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * Sets gender.
     *
     * @param gender the gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Integer id) {
        this.id = id;
    }
}
