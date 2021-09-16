package com.example.project1_cst483_group5;

import androidx.room.ColumnInfo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AnimalResults {
    @SerializedName("animals")
    List<Animal> animals;

    public AnimalResults(List<Animal> animals) {
        this.animals = animals;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }
}
