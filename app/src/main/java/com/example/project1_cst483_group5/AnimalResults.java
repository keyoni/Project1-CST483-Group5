package com.example.project1_cst483_group5;

import androidx.room.ColumnInfo;

import java.util.List;

public class AnimalResults {
    @ColumnInfo(name = "animals")
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
