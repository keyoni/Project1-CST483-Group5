package com.example.project1_cst483_group5;

import androidx.room.ColumnInfo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * The type Animal results.
 */
public class AnimalResults {
    /**
     * The Animals.
     */
    @SerializedName("animals")
    List<Animal> animals;

    /**
     * Instantiates a new Animal results.
     *
     * @param animals the animals
     */
    public AnimalResults(List<Animal> animals) {
        this.animals = animals;
    }

    /**
     * Gets animals.
     *
     * @return the animals
     */
    public List<Animal> getAnimals() {
        return animals;
    }

    /**
     * Sets animals.
     *
     * @param animals the animals
     */
    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }
}
