package com.example.project1_cst483_group5;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SingleAnimal {
    @SerializedName("animal")
    Animal animal;

    public SingleAnimal(Animal animal) {
        this.animal = animal;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animals) {
        this.animal = animal;
    }
}
